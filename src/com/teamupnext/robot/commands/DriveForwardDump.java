package com.teamupnext.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Up Next!
 */
public class DriveForwardDump extends CommandGroup {

    public DriveForwardDump() {

        addSequential(new DriveForwardEncoder(2.45, .5));//drives forward 70.5" (with coast)
        addParallel(new ReleaseBalls());
        addParallel(new DriveForwardEncoder(.25, .25));//driving forward to keep from bouncing off fender
        addSequential(new RaiseScissorLift());
        addSequential(new Wait(3));
        addSequential(new LowerScissorLift());

    }
}
