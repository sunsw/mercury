package com.sunsw.mercury.utils;

import com.sunsw.mercury.entry.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sunsw on 2015/9/7.
 */
public class LogUtils {

    private static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static void info(String message) {
        logger.info(new LogEntry("INFO", message).toString());
    }

    public static void info(String message, boolean alarmSwitch, boolean monitorSwitch) {
        logger.info(new LogEntry("INFO", message, alarmSwitch, monitorSwitch).toString());
    }

    public static void error(String message) {
        logger.error(new LogEntry("ERROR", message).toString());
    }

    public static void error(String message, boolean alarmSwitch, boolean monitorSwitch) {
        logger.error(new LogEntry("ERROR", message, alarmSwitch, monitorSwitch).toString());
    }

}
