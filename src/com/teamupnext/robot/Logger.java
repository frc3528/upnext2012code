package com.teamupnext.robot;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Up Next!
 */
public class Logger {

    public static class LogLevel {

        private static final int INFO_VAL = 0;
        private static final int DEBUG_VAL = 1;
        private static final int WARN_VAL = 2;
        private static final int CRITICAL_VAL = 3;
        private static final String[] levels = {"INFO", "DEBUG", "WARN", "CRITICAL"};
        public final int value;
        public final String name;
        public static final LogLevel INFO = new LogLevel(INFO_VAL);
        public static final LogLevel DEBUG = new LogLevel(DEBUG_VAL);
        public static final LogLevel WARN = new LogLevel(WARN_VAL);
        public static final LogLevel CRITICAL = new LogLevel(CRITICAL_VAL);

        private LogLevel(int val) {
            value = val;
            name = levels[val];
        }
    }

    public static class LogLocation {

        private static final int SYS_OUT_VAL = 0;
        private static final int DSTATION_VAL = 1;
        public final int value;
        public static final LogLocation SYS_OUT = new LogLocation(SYS_OUT_VAL);
        public static final LogLocation DSTATION = new LogLocation(DSTATION_VAL);

        private LogLocation(int val) {
            value = val;
        }
    }
    private static String m_stamp = "DFTBA";
    private static String m_constructedMessage;
    private static LogLevel m_fence = LogLevel.INFO;
    private static int m_counter = 5;
    private static final double m_iterationTime = .02;//the scheduler runs the code throught at 20ms increments
    private static double lastPrint;

    public static void logMessage(LogLevel level, String message)//prints to both
    {
        if (level.value >= m_fence.value && ((Timer.getFPGATimestamp() - lastPrint) >= m_iterationTime * m_counter)) {
            //m_stamp = "" + lastPrint;
            m_constructedMessage = m_constructedMessage = constructString(level, message);

            Utils.printToDriverStation(m_constructedMessage);
            System.out.println(m_constructedMessage);

            lastPrint = Timer.getFPGATimestamp();
        }

    }

    public static void logMessage(LogLevel level, LogLocation outLocation, String message) {
        if (level.value >= m_fence.value && ((Timer.getFPGATimestamp() - lastPrint) >= m_iterationTime * m_counter)) {
            m_stamp = "" + lastPrint;
            m_constructedMessage = constructString(level, message);

            if (outLocation.value == LogLocation.SYS_OUT.value) {
                System.out.println(m_constructedMessage);
            } else {
                Utils.printToDriverStation(m_constructedMessage);
            }

            lastPrint = Timer.getFPGATimestamp();

        }

    }

    public static void setFence(LogLevel fence) {
        m_fence = fence;
    }

    public static void setPrintCounter(int counter) {
        m_counter = counter;
    }

    public static LogLevel getFence() {
        return m_fence;
    }

    public static int getPrintCounter() {
        return m_counter;
    }

    private static String constructString(LogLevel level, String message) {
        return "[" + m_stamp + "]" + "[" + level.name + "]: " + message;
    }
}
