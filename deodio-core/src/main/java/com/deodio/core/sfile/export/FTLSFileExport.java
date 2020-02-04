package com.deodio.core.sfile.export;

import java.io.IOException;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.deodio.core.sfile.context.SFileContext;
import com.deodio.core.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import freemarker.template.Configuration;

public class FTLSFileExport implements ISFileExport {

    private FreeMarkerConfigurer freeMarkerEngine;

    @Override
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        // TODO Auto-generated method stub
    }

    @Override
    public Boolean export(Boolean is2Template, String toFile, String mTemplate, Object obj, Boolean isConfig) {
        if (is2Template) {
            return this.export2Template(toFile, mTemplate, obj, isConfig);
        } else {
            return this.export2Json(toFile, mTemplate, obj);
        }
    }

    /**
     * 
     * @param toFile
     *            : generate file path
     * @param mTemplate
     *            : invoke jpson method
     * @param obj
     *            : params will transfer to front end.
     */
    private Boolean export2Json(String toFile, String mTemplate, Object obj) {
//        Gson gson = new Gson();
//        StringBuffer dataJson = new StringBuffer();
//        String json = gson.toJson(obj).toString();
//        if (StringUtils.isNotBlank(mTemplate)) {
//            dataJson = dataJson.append(mTemplate).append("(").append(json).append(")");
//        } else {
//            dataJson = dataJson.append(json);
//        }
    	GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.disableHtmlEscaping();
		Gson gson = gsonBuilder.create();
		StringBuffer dataJson = new StringBuffer();
		String json=gson.toJson(obj).toString();
		dataJson = dataJson.append("news").append("(").append(json).append(")");
        return FileUtils.write2Json(toFile, dataJson.toString());
    }

    /**
     * 
     * @param toFile
     *            : generate file path
     * @param mTemplate
     *            : template file path
     * @param obj
     *            : params will transfer to front end.
     */
    public Boolean export2Template(String toFile, String mTemplate, Object obj, Boolean isConfig) {
        if (isConfig) {
            return export2ConfigurationTemplate(toFile, mTemplate, obj);
        } else {
            return export2DefaultTemplate(toFile, mTemplate, obj);
        }
    }

    /**
     * 
     * @param toFile
     *            : generate file path
     * @param mTemplate
     *            : template file path
     * @param obj
     *            : params will transfer to front end.
     */
    public Boolean export2DefaultTemplate(String toFile, String mTemplate, Object obj) {
        Configuration config = freeMarkerEngine.getConfiguration();
        Map<String, Object> params = (Map<String, Object>) obj;
        return FileUtils.write2Template(toFile, mTemplate, config, params);
    }

    public Boolean export2ConfigurationTemplate(String toFile, String mTemplate, Object object) {
        try {
            // String templatePath = isDefaultPath ?
            // GeneratorUtils.getSfileLoadTemplatePath() :
            // FileUtils.getPath(mTemplate, Boolean.FALSE);
            // String templateName = isDefaultPath ? mTemplate :
            // FileUtils.getName(mTemplate, Boolean.TRUE);
            Configuration config = SFileContext.initConfiguration(FileUtils.getPath(mTemplate, Boolean.FALSE));
            Map<String, Object> params = (Map<String, Object>) object;
            return FileUtils.write2Template(toFile, FileUtils.getName(mTemplate, Boolean.TRUE), config, params);
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    @Override
    public void setFreeMarkerEngine(FreeMarkerConfigurer freeMarkerEngine) {
        this.freeMarkerEngine = freeMarkerEngine;
    }

}
