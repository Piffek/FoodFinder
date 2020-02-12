package com.example.foodFinder.Conf;

import freemarker.cache.ClassTemplateLoader;
import freemarker.core.HTMLOutputFormat;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class FreemarkerConf {
    private final String TEMPLATE_PATH = "/email-templates/";


    public FreeMarkerConfigurer freemarkerConfiguration() {
        FreeMarkerConfigurer configuration =
                new FreeMarkerConfigurer();
        configuration.getConfiguration().setOutputFormat(HTMLOutputFormat.INSTANCE);
        configuration.getConfiguration().setOutputEncoding("UTF-8");
        configuration.setDefaultEncoding("UTF-8");
        configuration.getConfiguration().setTemplateLoader(new ClassTemplateLoader(getClass(), TEMPLATE_PATH));

        return configuration;
    }
}
