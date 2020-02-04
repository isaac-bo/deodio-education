package com.deodio.core.sfile.export;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.deodio.core.utils.FileUtils;
import com.google.gson.Gson;

public class VMSFileExport implements ISFileExport {

    private VelocityEngine velocityEngine;

    @Override
    public Boolean export(Boolean is2Template, String toFile, String mTemplate, Object obj, Boolean isConfig) {

        if (is2Template) {
            return this.export2Template(toFile, mTemplate, obj);
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

        Gson gson = new Gson();
        StringBuffer dataJson = new StringBuffer();
        String json = gson.toJson(obj).toString();
        if (StringUtils.isNotBlank(mTemplate)) {
            dataJson = dataJson.append(mTemplate).append("(").append(json).append(")");
        } else {
            dataJson = dataJson.append(json);
        }

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
    private Boolean export2Template(String toFile, String mTemplate, Object obj) {

        VelocityContext context = new VelocityContext();
        Map<String, Object> params = (Map<String, Object>) obj;
        Set<String> key = params.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
            String mapKey = (String) it.next();
            context.put(mapKey, params.get(mapKey));
        }
        return FileUtils.write2Template(toFile, mTemplate, velocityEngine, context);
    }

    @Override
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;

    }

    @Override
    public void setFreeMarkerEngine(FreeMarkerConfigurer freeMarkerEngine) {
        // TODO Auto-generated method stub

    }
}
