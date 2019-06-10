package com.kmvpsolutions.boutique.productservice.config;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.LoggerContext;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import net.logstash.logback.stacktrace.ShortenedThrowableConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class LoggingConfiguration {

    private static final String LOG_STASH_APPENDER_NAME = "LOGSTASH";
    private static final String ASYNC_LOG_STASH_APPENDER_NAME = "ASYNC_LOGSTASH";

    private final Logger LOG = LoggerFactory.getLogger(LoggingConfiguration.class);
    private final LoggerContext CONTEXT = (LoggerContext) LoggerFactory.getILoggerFactory();

    private final String appName;
    private final String host;
    private final int port;
    private final int queueSize;

    public LoggingConfiguration(
            @Value("${spring.application.name}") String appName,
            @Value("${logstash.host}") String host,
            @Value("${logstash.port}") int port,
            @Value("${logstash.queue-size}") int queueSize
    ) {
        this.appName = appName;
        this.host = host;
        this.port = port;
        this.queueSize = queueSize;

        this.addLogStashAppender(CONTEXT);
    }

    private void addLogStashAppender(LoggerContext context) {
        LOG.info("Initializing log stash appending");

        LogstashTcpSocketAppender logstashAppender = new LogstashTcpSocketAppender();
        logstashAppender.setName(LOG_STASH_APPENDER_NAME);
        logstashAppender.setContext(context);
        String customFields = "{\"servicename\":\"" + this.appName + "\"}";

        // More documentation is available at: https://github.com/logstash/logstash-logback-encoder
        LogstashEncoder logstashEncoder = new LogstashEncoder();
        // Set the Logstash appender config
        logstashEncoder.setCustomFields(customFields);
        // Set the Logstash appender config
        logstashAppender.addDestinations(
                new InetSocketAddress(this.host, this.port)
        );

        ShortenedThrowableConverter throwableConverter = new ShortenedThrowableConverter();
        throwableConverter.setRootCauseFirst(true);
        logstashEncoder.setThrowableConverter(throwableConverter);
        logstashEncoder.setCustomFields(customFields);

        logstashAppender.setEncoder(logstashEncoder);
        logstashAppender.start();

        // Wrap the appender in an Async appender for performance
        AsyncAppender asyncLogstashAppender = new AsyncAppender();
        asyncLogstashAppender.setContext(context);
        asyncLogstashAppender.setName(ASYNC_LOG_STASH_APPENDER_NAME);
        asyncLogstashAppender.setQueueSize(this.queueSize);
        asyncLogstashAppender.addAppender(logstashAppender);
        asyncLogstashAppender.start();

        context.getLogger("ROOT").addAppender(asyncLogstashAppender);
    }
}
