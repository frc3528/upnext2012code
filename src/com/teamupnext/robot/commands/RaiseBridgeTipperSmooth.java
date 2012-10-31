/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamupnext.robot.commands;

import com.teamupnext.robot.RobotMap;

/**
 *
 * @author jousley
 */
public class RaiseBridgeTipperSmooth extends CommandBase {
    
    public RaiseBridgeTipperSmooth() {
        requires(bridgeTipper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(RobotMap.BRIDGE_TIME_UP_SMOOTH);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        bridgeTipper.RaiseTipperSmooth();
    }

    protected boolean isFinished() {
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
