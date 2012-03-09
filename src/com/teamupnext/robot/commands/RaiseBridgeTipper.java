package com.teamupnext.robot.commands;

import com.teamupnext.robot.RobotMap;

/**
 *
 * @author Up Next!
 */
public class RaiseBridgeTipper extends CommandBase {

    public RaiseBridgeTipper() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(bridgeTipper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(RobotMap.BRIDGE_TIME_TO_UP);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        bridgeTipper.RaiseTipper();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return bridgeTipper.IsUp();
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        bridgeTipper.StopTipper();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
