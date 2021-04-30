package com.epam.ems.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditLogger {
    private final Logger logger = LoggerFactory.getLogger(AuditLogger.class);

    public void doLog(String message) {
        logger.info(message);
    }
}
