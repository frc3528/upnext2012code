package com.teamupnext.robot.commands;

import com.teamupnext.robot.RobotMap;

/**
 *
 * @author Up Next!
 */
public class ReleaseBalls extends CommandBase {

    public ReleaseBalls() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(dumper);
        //timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(RobotMap.DUMP_TIMEOUT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        dumper.dump();
        //timer.reset();
        //timer.start();        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        dumper.reset();
        //Utils.printToDriverStation("ending dump");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
