package com.teamupnext.robot.commands;

import com.teamupnext.robot.RobotMap;

/**
 *
 * @author Up Next!
 */
public class RaiseScissorLift extends CommandBase {

    public RaiseScissorLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);        
        requires(scissorLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(RobotMap.SCISSOR_TIMEOUT_UP);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!scissorLift.isUp()) {
            //scissorLift.raiseScissorLift();
            if (timeSinceInitialized() <= .1) {
                scissorLift.raiseScissorLift(50);//run motor at 50% for the first 100 ms
                return;
            }

            if (timeSinceInitialized() <= .2) {
                scissorLift.raiseScissorLift(75);//run motor at 75% for the second 100 ms
                return;
            }

            scissorLift.raiseScissorLift(100);//run motor at 100% for the rest of the time
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return scissorLift.isUp() || this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("finished raising scissor lift");
        scissorLift.stopScissorLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
