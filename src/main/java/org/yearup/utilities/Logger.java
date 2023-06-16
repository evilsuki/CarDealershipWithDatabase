package org.yearup.utilities;

import org.apache.logging.log4j.LogManager;

public class Logger
{
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    public static void logError(Exception e)
    {
        logger.error(e.getMessage());
    }
}
