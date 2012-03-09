package com.teamupnext.robot.commands;

/**
 *
 * @author Up Next!
 */
public class Wait extends CommandBase {

    private double m_time;

    public Wait(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.m_time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(m_time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
