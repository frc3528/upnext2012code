package com.teamupnext.robot.commands;

import com.teamupnext.robot.RobotMap;

/**
 *
 * @author Up Next!
 */
public class LowerScissorLift extends CommandBase {

    public LowerScissorLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(scissorLift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(RobotMap.SCISSOR_TIMEOUT_DOWN);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!scissorLift.isDown()) {
            scissorLift.lowerScissorLift();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return scissorLift.isDown() || this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("finished lowering scissor lift");
        scissorLift.stopScissorLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
