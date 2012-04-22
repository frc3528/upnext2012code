package com.teamupnext.robot.subsystems;

import com.teamupnext.robot.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Up Next!
 */
public class Dumper extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //private static final int RELAY_PORT = 2;
    private Relay dumper = null;
    private Servo dumpServo;

    public Dumper() {
        //dumper = new Relay(RELAY_PORT);
        dumpServo = new Servo(RobotMap.DUMP_SERVO_CHANNEL);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void dump() {
        dumpServo.setAngle(RobotMap.DUMP_SERVO_UP);
        //Utils.printToDriverStation("servo dump");
    }

    public void reset() {
        dumpServo.setAngle(RobotMap.DUMP_SERVO_RESET);
    }
}
