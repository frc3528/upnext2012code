package com.teamupnext.robot.commands;

import com.teamupnext.robot.OI;
import com.teamupnext.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static DriveTrain driveTrain = new DriveTrain();
    public static Conveyor conveyor = new Conveyor();
    public static Dumper dumper = new Dumper();
    public static ScissorLift scissorLift = new ScissorLift();
    //public static Targeting targeting = new Targeting();
    public static BridgeTipper bridgeTipper = new BridgeTipper();
    //Must be last because the OI class instantiates objects which need the subsystems initialized
    public static OI oi = new OI();

    // Create a single static instance of all of your subsystems
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        //oi = new OI();
        //riveTrain = new DriveTrain();
        //conveyor = new Conveyor();
        //dumper = new Dumper();
        //scissorLift = new ScissorLift();
        //targeting = new Targeting();

        // Show what command your subsystem is running on the SmartDashboard
        //SmartDashboard.putData(exampleSubsystem);
        SmartDashboard.putData("SchedulerData", Scheduler.getInstance());
        SmartDashboard.putData(conveyor);


    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
