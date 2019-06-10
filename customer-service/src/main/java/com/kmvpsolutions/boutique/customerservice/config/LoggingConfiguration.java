package com.kmvpsolutions.boutique.customerservice.config;

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

        LogstashTcpSocketAppender logstashTcpSocketAppender = new LogstashTcpSocketAppender();
        logstashTcpSocketAppender.setName(LOG_STASH_APPENDER_NAME);
        logstashTcpSocketAppender.setContext(context);
        logstashTcpSocketAppender.addDestinations(
                new InetSocketAddress(this.host, this.port)
        );

        LogstashEncoder logstashEncoder = new LogstashEncoder();
        logstashEncoder.setCustomFields("{\"servicename\":\"" + this.appName + "\"}");
        logstashTcpSocketAppender.setEncoder(logstashEncoder);

        ShortenedThrowableConverter shortenedThrowableConverter = new ShortenedThrowableConverter();
        shortenedThrowableConverter.setRootCauseFirst(true);
        logstashEncoder.setThrowableConverter(shortenedThrowableConverter);

        logstashEncoder.start();

        AsyncAppender asyncAppender = new AsyncAppender();
        asyncAppender.setContext(context);
        asyncAppender.setName(ASYNC_LOG_STASH_APPENDER_NAME);
        asyncAppender.setQueueSize(this.queueSize);
        asyncAppender.addAppender(logstashTcpSocketAppender);

        asyncAppender.start();

        context.getLogger("ROOT").addAppender(asyncAppender);
    }
}
