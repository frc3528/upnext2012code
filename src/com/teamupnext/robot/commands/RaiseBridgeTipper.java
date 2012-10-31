package com.teamupnext.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author jousley
 */
public class RaiseBridgeTipper extends CommandGroup {
    
    public RaiseBridgeTipper() {
       addSequential(new RaiseBridgeTipperSmooth());
       addSequential(new RaiseBridgeTipperJittery());
    }
}
