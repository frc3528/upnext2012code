package com.teamupnext.robot.commands;

import com.teamupnext.robot.RobotMap;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Up Next!
 */
public class ConveyorWithJoystick extends CommandBase {

    Joystick joystick;

    public ConveyorWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(conveyor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        joystick = oi.getControlsJoystick();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (joystick.getY() < -RobotMap.CONVEYOR_DEADZONE) {
            conveyor.moveConveyorUp();
        } else if (joystick.getY() > RobotMap.CONVEYOR_DEADZONE) {
            conveyor.moveConveyorDown();
        } else {
            conveyor.conveyorStop();
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
