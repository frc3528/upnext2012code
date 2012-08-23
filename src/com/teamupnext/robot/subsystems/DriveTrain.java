package com.teamupnext.robot.subsystems;

import com.teamupnext.robot.RobotMap;
import com.teamupnext.robot.Utils;
import com.teamupnext.robot.commands.DriveWithJoystick;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Up Next!
 */
public class DriveTrain extends Subsystem {

    private RobotDrive drive;
    private CANJaguar jagFrontLeft;
    private CANJaguar jagFrontRight;
    private CANJaguar jagBackLeft;
    private CANJaguar jagBackRight;
    private Gyro m_gyro;
    private static final double P = 10;
    private static final double I = .002;
    private static final double D = 0.0;

    public DriveTrain() {
        
        super();
        
        setJagLocations();
        
        drive = new RobotDrive(jagFrontLeft, jagBackLeft, jagFrontRight, jagBackRight);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);//
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

        initializeJag(jagBackLeft);
        initializeJag(jagBackRight);
        initializeJag(jagFrontLeft);
        initializeJag(jagFrontRight);

        m_gyro = new Gyro(RobotMap.GYRO_PORT);
        m_gyro.setSensitivity(.007);
        m_gyro.reset();
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }

    public void driveWithJoystick(Joystick joystick) {
        driveWithJoystick(joystick.getX(), joystick.getY(), joystick.getZ(), 0);
   }

    public void driveWithJoystick(double x, double y, double rotation, double gyroAngle) {
        drive.mecanumDrive_Cartesian(Utils.rampSpeed(x, RobotMap.SENSITIVITY), Utils.rampSpeed(y, RobotMap.SENSITIVITY), Utils.rampSpeed(-1 * rotation, RobotMap.SENSITIVITY), 0);
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
    
    private void setJagLocations() {
       try {
            jagFrontRight = new CANJaguar(RobotMap.FRONT_RIGHT_MOTOR);
            jagFrontLeft = new CANJaguar(RobotMap.FRONT_LEFT_MOTOR);
            jagBackLeft = new CANJaguar(RobotMap.BACK_LEFT_MOTOR);
            jagBackRight = new CANJaguar(RobotMap.BACK_RIGHT_MOTOR);

        } catch (CANTimeoutException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void initializeJag(CANJaguar jag) {
        try
        {
            jag.enableControl();
            jag.configEncoderCodesPerRev(360);
            jag.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            jag.setExpiration(.5);
        }
        catch(Exception e)
        {
            System.out.println("Error enabling closed control on Jag " + e.getMessage());
        }
    }
}
