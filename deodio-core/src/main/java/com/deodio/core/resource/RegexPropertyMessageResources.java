package com.deodio.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class RegexPropertyMessageResources extends AbstractMessageResources {

	protected static final Logger LOGGER = LoggerFactory.getLogger(RegexPropertyMessageResources.class);

	public static String PROPERTY_POSTFIX = ".properties";
	private ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
	private PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();

	public RegexPropertyMessageResources() {

	}

	public RegexPropertyMessageResources(String baseName) {
		setBaseNames(new String[] { baseName });
	}

	public RegexPropertyMessageResources(String[] baseNames) {
		setBaseNames(baseNames);
	}

	public void setBaseName(String baseName) {
		setBaseNames(new String[] { baseName });
	}

	public void setBaseNames(String[] baseNames) {
		List<String> baseNameList = new ArrayList<String>();
		try {
			for (String baseName : baseNames) {
				Resource[] resources = patternResolver.getResources(baseName); 
				for (Resource resource : resources) {
					String fileName = "properties/"+resource.getFilename();
					baseNameList.add(fileName.substring(0,
							fileName.indexOf(PROPERTY_POSTFIX)));
					if (LOGGER.isInfoEnabled()) {
						LOGGER.info("Add properties file: [" + resource.getDescription() + "]");
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.resourceBundleMessageSource.setBasenames(baseNameList
				.toArray(new String[baseNameList.size()]));
	}

	@Override
	public String getMessage(String key, Object[] params,
			String defaultMessage, Locale locale) {
		return this.resourceBundleMessageSource.getMessage(key, params,
				defaultMessage, locale);
	}
}
