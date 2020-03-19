package com.piwkosoft.foodFinder.Conf;

import freemarker.cache.ClassTemplateLoader;
import freemarker.core.HTMLOutputFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreemarkerConf {

    @Bean
    public freemarker.template.Configuration freemarkerConfiguration() {
        freemarker.template.Configuration configuration =
                new freemarker.template.Configuration();
        configuration.setOutputFormat(HTMLOutputFormat.INSTANCE);
        configuration.setOutputEncoding("UTF-8");
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }
}
