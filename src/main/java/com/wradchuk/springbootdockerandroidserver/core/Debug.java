package com.wradchuk.springbootdockerandroidserver.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Debug {
    private static final Logger LOGGER = LogManager.getLogger();
    public static String TAG = "WR_DEBUG - ANDROID";


    public static void logInfo(String _msg) {
        LOGGER.info(TAG+": " + _msg);
    }
    public static void logDebug(String _msg) {
        LOGGER.debug(TAG+": " + _msg);
    }
}
