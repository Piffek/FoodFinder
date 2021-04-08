package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Constranits;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.EmailService;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import javax.mail.Address;
import javax.mail.MessagingException;
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

/**
 * Project: FoodFinder
 *
 * Created on: 01.04.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Service
public class EmailServiceImpl implements EmailService {

  private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
  private final String TEMPLATE_PATH = "/email-templates/";

  private final FreeMarkerConfigurer freeMarkerConfigurer;
  private final JavaMailSender javaMailSender;

  public EmailServiceImpl(final FreeMarkerConfigurer freeMarkerConfigurer,
      final JavaMailSender javaMailSender) {
    this.freeMarkerConfigurer = freeMarkerConfigurer;
    this.javaMailSender = javaMailSender;
  }

  @Override
  public void sendEmail(final List<String> recipients, final Map<String, Object> dataModel,
      final String templateName, final Locale locale) {
    Properties properties = new Properties();
    final String localeString = locale != null ? locale.toString() : Constranits.DEFAULT_LOCALE;
    final String templatePath = TEMPLATE_PATH + "" + templateName + "_" + localeString + ".properties";
    final String defaultEmailProperty =
        TEMPLATE_PATH + "" + Constranits.DEFAULT_EMAIL_PROPERTY_FILE + "_" + localeString + ".properties";

    try (final InputStream inputStream = getClass().getResourceAsStream(templatePath);
        final InputStream defaultEmailPropertyFile = getClass().getResourceAsStream(defaultEmailProperty)) {
      properties.load(inputStream);
      properties.load(defaultEmailPropertyFile);
    } catch (IOException e) {
      logger.error("Error while loading email template properties: {}", templatePath, e);
    }

    final LinkedHashMap<Object, Object> finalDataModel = new LinkedHashMap<>(properties);
    if (dataModel != null) {
      finalDataModel.putAll(dataModel);
    }

    try {
      final Template template = freeMarkerConfigurer.getConfiguration()
          .getTemplate(templateName + ".ftl");
      final StringWriter stringWriter = new StringWriter();
      template.process(finalDataModel, stringWriter);

      final String mailText = stringWriter.toString();

      final MimeMessage mime = javaMailSender.createMimeMessage();
      mime.setFrom(new InternetAddress("do-not-replay@foodfinder.pl", "Piwko"));
      mime.setSubject(properties.getProperty("subject"), "UTF-8");
      mime.setText(mailText, template.getOutputEncoding(), "html");
      mime.setRecipients(Message.RecipientType.TO, setEmails(recipients));

      javaMailSender.send(mime);
    } catch (TemplateException | MessagingException | MalformedTemplateNameException e) {
      logger.error("template exception", e);
    } catch (UnsupportedEncodingException e) {
      logger.error("encoding exception", e);
    } catch (ParseException e) {
      logger.error("parse exception", e);
    } catch (TemplateNotFoundException e) {
      logger.error("template not found exception", e);
    } catch (IOException e) {
      e.printStackTrace();
    }

    logger.debug("email sending to {}", recipients);
  }


  @Override
  public void sendEmail(final String recipient, final Map<String, Object> message,
      final String templateName, final Locale locale) {
    sendEmail(Collections.singletonList(recipient), message, templateName, locale);
  }

  private Address[] setEmails(final List<String> emails) {

    return emails.stream()
        .map(this::internetAddress)
        .filter(Objects::nonNull)
        .toArray(Address[]::new);
  }

  private Address internetAddress(final String email) {
    final String personal = email.substring(0, email.indexOf("@"));
    try {
      return new InternetAddress(email, personal);
    } catch (final UnsupportedEncodingException e) {
      logger.error("encoding exception for adress {}", email, e);
    }
    return null;
  }
}
