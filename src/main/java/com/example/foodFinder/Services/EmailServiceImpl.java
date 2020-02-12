package com.example.foodFinder.Services;

import com.example.foodFinder.Services.Interfaces.EmailService;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final String TEMPLATE_PATH = "/email-templates/";

    private final FreeMarkerConfigurer freeMarkerConfigurer;
    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(FreeMarkerConfigurer freeMarkerConfigurer,
                            JavaMailSender javaMailSender) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(final List<String> toList, final Map<String, Object> dataModel, final String templateName,
                          final String recipients) {
        Properties properties = new Properties();

        try(InputStream inputStream = getClass().getResourceAsStream(TEMPLATE_PATH+""+templateName+".properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Error while loading email template properties: {}", templateName, e);
        }

        LinkedHashMap<Object, Object> finalDataModel = new LinkedHashMap<>(properties);
        if(dataModel != null) {
            finalDataModel.putAll(dataModel);
        }

        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName+".ftl");
            StringWriter stringWriter = new StringWriter();
            template.process(finalDataModel, stringWriter);

            String mailText = stringWriter.toString();

            MimeMessage mime = javaMailSender.createMimeMessage();
            mime.setFrom(new InternetAddress("do-not-replay@foodfinder.pl", "Piwko"));
            mime.setSubject(properties.getProperty("subject"), "UTF-8");
            mime.setText(mailText, template.getOutputEncoding(), "html");
            mime.setRecipients(Message.RecipientType.TO, recipients);

            javaMailSender.send(mime);
        } catch (Exception e) {

        }
    }
}
