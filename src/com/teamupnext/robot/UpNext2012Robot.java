package com.teamupnext.robot;

import com.teamupnext.robot.commands.CommandBase;
import com.teamupnext.robot.commands.DriveForwardDump;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 * @author Up Next!
 */
public class UpNext2012Robot extends IterativeRobot {

    //CommandGroup autonomous;
    Command autonomous;
    
    //SendableChooser autoChooser;
    DriverStation dStation;

    public UpNext2012Robot() {
        super();

        Utils.clearDriverStation();
        dStation = DriverStation.getInstance();
    }

    public void robotInit() {
        CommandBase.init();

        CommandBase.dumper.reset();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        autonomous = new DriveForwardDump();
        autonomous.start();
    }

    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }
        
    public void autonomousContinuous() {
        
    }
    
    public void disabledInit() {
        
    }
    
    
    public void teleopInit() {
        
    }
    
    public void teleopContinuous() {
        
    }
    
    
    public void disabledPeriodic() {
        
    }
    
    
    public void disabledContinuous() {
        
    }
    
}
