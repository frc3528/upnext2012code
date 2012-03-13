package com.teamupnext.robot.subsystems;

import com.teamupnext.robot.Logger;
import com.teamupnext.robot.Logger.LogLevel;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.teamupnext.robot.commands.DriveWithJoystick;
import com.teamupnext.robot.RobotMap;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.*;
import com.teamupnext.robot.Utils;

/**
 *
 * @author Up Next!
 */
public class DriveTrain extends Subsystem {

    private RobotDrive drive;
    private CANJaguar jagFrontLeft;
    private CANJaguar jagFrontRight;
    private CANJaguar jagBackLeft;
    private CANJaguar jagBackRight;
    private AnalogChannel rangeFinder;
    private Gyro m_gyro;
    private static final double P = 10;
    private static final double I = .002;
    private static final double D = 0.0;

    //private Gyro m_gyro;
    public DriveTrain() {
        super();

        try {
            jagFrontRight = new CANJaguar(RobotMap.FRONT_RIGHT_MOTOR);
            jagFrontLeft = new CANJaguar(RobotMap.FRONT_LEFT_MOTOR);
            jagBackLeft = new CANJaguar(RobotMap.BACK_LEFT_MOTOR);
            jagBackRight = new CANJaguar(RobotMap.BACK_RIGHT_MOTOR);

        } catch (CANTimeoutException e) {
            System.out.println(e.getMessage());
        }
        
        drive = new RobotDrive(jagFrontLeft, jagBackLeft, jagFrontRight, jagBackRight);
        //Inverts the right side of the robot
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);//
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drive.setSafetyEnabled(false);

        rangeFinder = new AnalogChannel(RobotMap.RANGEFINDER_PORT);

        try {
            //jagBackLeft.changeControlMode(CANJaguar.ControlMode.kPosition);
            jagBackLeft.enableControl();
            jagBackLeft.configEncoderCodesPerRev(360);
            jagBackLeft.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            jagBackLeft.setExpiration(.5);
            //jagBackLeft.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);

            //jagBackRight.changeControlMode(CANJaguar.ControlMode.kPosition);
            jagBackRight.enableControl();
            jagBackRight.configEncoderCodesPerRev(360);
            jagBackRight.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            jagBackRight.setExpiration(.5);
            //jagBackRight.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);

            //jagFrontLeft.changeControlMode(CANJaguar.ControlMode.kPosition);
            jagFrontLeft.enableControl();
            jagFrontLeft.configEncoderCodesPerRev(360);
            jagFrontLeft.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            jagFrontLeft.setExpiration(.5);
            //jagFrontLeft.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);

            //jagFrontRight.changeControlMode(CANJaguar.ControlMode.kPosition);
            jagFrontRight.enableControl();
            jagFrontRight.configEncoderCodesPerRev(360);
            jagFrontRight.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            jagFrontRight.setExpiration(.5);
            //jagFrontRight.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);

            //jagBackLeft.setPID(10, .002, 0);
        } catch (Exception e) {
            System.out.println("Error enabling closed control on Jag " + e.getMessage());
        }

        m_gyro = new Gyro(RobotMap.GYRO_PORT);
        m_gyro.setSensitivity(.007);
        m_gyro.reset();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoystick());
    }

    /*
    double prev = 0;
    double current = 0;
     */
    public void driveWithJoystick(Joystick joystick) {
        //drive.mecanumDrive_Cartesian(Utils.rampSpeed(joystick.getX(), RobotMap.SENSITIVITY), Utils.rampSpeed(joystick.getY(), RobotMap.SENSITIVITY), Utils.rampSpeed(-1 * joystick.getZ(), RobotMap.SENSITIVITY ), 0);
        driveWithJoystick(joystick.getX(), joystick.getY(), joystick.getZ(), 0);
        //System.out.println("joystick reading: " + joystick.getY());

        /*int rfVal = rangeFinder.getValue();
        double rfVolts = rangeFinder.getVoltage();
        double rfAvgVolts = rangeFinder.getAverageVoltage();
        double vcc = 5.0;
        double vi = vcc / 512;
        
        double inches = rfAvgVolts / vi;
        prev = current = Utils.lowPassFilter(inches, prev);
        
        
        System.out.println("rfval: " + rfVal + "  rfvol: " + rfVolts + "  lpf: " + current);
         */

    }
    //private double highSpeed = 0;
    //double in;
    int count = 1;

    public void driveWithJoystick(double x, double y, double rotation, double gyroAngle) {
        //used to drive the motors in a particular direction
        drive.mecanumDrive_Cartesian(Utils.rampSpeed(x, RobotMap.SENSITIVITY), Utils.rampSpeed(y, RobotMap.SENSITIVITY), Utils.rampSpeed(-1 * rotation, RobotMap.SENSITIVITY), 0);
        //Utils.printToDriverStation("" + m_gyro.pidGet());
        /*try
        {
        //System.out.println("Encoder getSpeed " + jagBackLeft.getSpeed());
        //System.out.println("bus voltage: " + jagBackLeft.getBusVoltage() );
        //System.out.println("control mode: " + jagBackLeft.getControlMode() );
        //System.out.println();
        in = jagBackLeft.getSpeed();
        if( in > highSpeed )
        highSpeed = in;
        //System.out.println("Highest speed: " + highSpeed);
        /*System.out.println("Front Left: " + jagFrontLeft.getPosition() + " \n Front Right: " 
        + jagFrontRight.getPosition() + "\nBack Left: " + jagBackLeft.getPosition() 
        + "\nBack Right: " + jagBackRight.getPosition());
         * 
         */
        //System.out.println("Front Right: " + jagFrontRight.getSpeed());
        //System.out.println("Back Left: " + jagBackLeft.getSpeed());
        //System.out.println("Back Right: " + jagBackRight.getSpeed());
        /*}
        catch(Exception e)
        {
        System.out.println("Error getting jag speeds" + e.getMessage());
        }
         */

        //Logger.logMessage(LogLevel.INFO, "log");

    }

    public void setPositionFrontRight(double distance) {
        try {
            jagFrontRight.setX(distance);
        } catch (Exception e) {
            System.out.println("Error setting FrontRight Position: " + e.getMessage());
        }
    }

    public void setFrontRightPower(double power) {
        try {
            jagFrontRight.set(power);
        } catch (Exception e) {
            System.out.println("Error seting Front Right power: " + e.getMessage());
        }
    }

    public double getPositionFrontRight() {
        try {
            return -jagFrontRight.getPosition();
        } catch (Exception e) {
            System.out.println("Error getting jag position: " + e.getMessage());

            return 0;
        }
    }

    public double getPositionFrontLeft() {
        try {
            return jagFrontLeft.getPosition();
        } catch (Exception e) {
            System.out.println("Error getting jag position: " + e.getMessage());

            return 0;
        }
    }

    public double getPositionBackRight() {
        try {
            return -jagBackRight.getPosition();//negative because motors are inverted
        } catch (Exception e) {
            System.out.println("Error getting jag position: " + e.getMessage());

            return 0;
        }
    }

    public double getPositionBackLeft() {
        try {
            return jagBackLeft.getPosition();
        } catch (Exception e) {
            System.out.println("Error getting jag position: " + e.getMessage());

            return 0;
        }
    }

    public void zeroEncoders() {
        try {
            jagFrontRight.disableControl();
            jagFrontLeft.disableControl();
            jagBackRight.disableControl();
            jagBackLeft.disableControl();
            jagFrontRight.enableControl(0);
            jagFrontLeft.enableControl(0);
            jagBackRight.enableControl(0);
            jagBackLeft.enableControl(0);
        } catch (Exception e) {
            System.out.println("Error zeroing encoders: " + e.getMessage());
        }
    }

    public void SetPositionMode() {
        try {
            jagBackLeft.changeControlMode(CANJaguar.ControlMode.kPosition);
            jagBackRight.changeControlMode(CANJaguar.ControlMode.kPosition);
            jagFrontLeft.changeControlMode(CANJaguar.ControlMode.kPosition);
            jagFrontRight.changeControlMode(CANJaguar.ControlMode.kPosition);

            jagBackLeft.setPID(P, I, D);
            jagBackRight.setPID(P, I, D);
            jagFrontLeft.setPID(P, I, D);
            jagFrontRight.setPID(P, I, D);

        } catch (Exception e) {
            System.out.println("Error setting jag into position mode: " + e.getMessage());
        }
    }

    public void SetPercentMode() {
        try {
            jagBackLeft.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            jagBackRight.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            jagFrontLeft.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            jagFrontRight.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
        } catch (Exception e) {
            System.out.println("Error setting jag into percent mode: " + e.getMessage());
        }
    }

    public void setAllXPosition(double x) {
        //zeroEncoders();

        try {
            System.out.println("P value" + jagFrontLeft.getP());

            jagFrontLeft.setX(x);
            jagFrontRight.setX(x);
            jagBackLeft.setX(x);
            jagBackRight.setX(x);
        } catch (Exception e) {
            System.out.println("Error setting jag x positions: " + e.getMessage());
        }
    }

    public void endControlMode() {
        try {
            jagFrontLeft.disableControl();
            jagFrontRight.disableControl();
            jagBackLeft.disableControl();
            jagBackRight.disableControl();
        } catch (Exception e) {
            System.out.println("Error ending control mode: " + e.getMessage());
        }
    }
    
    public double getAngle()
    {
        return m_gyro.getAngle();
    }
    
    public void resetGyro()
    {
        m_gyro.reset();
    }
}
