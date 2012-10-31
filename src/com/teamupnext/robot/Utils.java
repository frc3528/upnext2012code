package com.teamupnext.robot;

import edu.wpi.first.wpilibj.DriverStationLCD;
import java.util.Vector;

/**
 *
 * @author Up Next!
 */
public class Utils {

    public double PIDDrive(double input, double actualVelocity) {
        //Add normalize velocity
        double normalVelocity = actualVelocity / RobotMap.MAX_FLAT_VELOCITY;
        //the max value on blocks is 432.4090576171875

        //setting a deadband of -.1 to .1 for the input and only correcting if the actual velocity is changing fastre than .1
        if ((input > -.1 && input < .1) && (actualVelocity < -.1 || actualVelocity > .1)) {
            return -actualVelocity;
        }

        return input;
    }

    //A method used for smooth driving
    public static double rampSpeed(double input, double sensitivity) {

        // dead band of -.1 to .1
        if (input > -.1 && input < .1) {
            return 0;
        }

        //formula for ramping: f(x) = ax^3 + (1-a)x where a is the sensitivity and x is the input
        return (sensitivity * input * input * input + (1 - sensitivity) * input);
    }

    public static double rampSpeed(double input) {
        //auto set sensitivity to .5
        return rampSpeed(input, .5);
    }

    //Filters the input using a low pass filter
    public static double lowPassFilter(double input, double prev) {
        //needs to be between 0 and 1 where 1 is no filter and 0 is previous value
        double alpha = .85;

        return prev + (alpha * (input - prev));
    }

    public static void printToDriverStation(String in) {
        v.addElement(in);
        String clear = "                      ";

        if (v.size() > lcdsize) {
            v.removeElementAt(0);
        } else {
            int add = lcdsize - v.size();

            for (int i = 0; i < add; i++) {
                v.addElement("");
            }
        }   

        dslcd.println(DriverStationLCD.Line.kUser1, 1, (String) v.elementAt(5) + clear);
        dslcd.println(DriverStationLCD.Line.kUser2, 1, (String) v.elementAt(4) + clear);
        dslcd.println(DriverStationLCD.Line.kUser3, 1, (String) v.elementAt(3) + clear);
        dslcd.println(DriverStationLCD.Line.kUser4, 1, (String) v.elementAt(2) + clear);
        dslcd.println(DriverStationLCD.Line.kUser5, 1, (String) v.elementAt(1) + clear);
        dslcd.println(DriverStationLCD.Line.kUser6, 1, (String) v.elementAt(0) + clear);

        dslcd.updateLCD();
    }

    public static void clearDriverStation() {
        printToDriverStation("");
        printToDriverStation("");
        printToDriverStation("");
        printToDriverStation("");
        printToDriverStation("");
        printToDriverStation("");
    }
    private static DriverStationLCD dslcd = DriverStationLCD.getInstance();
    private static Vector v = new Vector();
    private static int lcdsize = 6;
}