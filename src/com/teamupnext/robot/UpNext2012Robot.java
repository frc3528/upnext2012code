package com.teamupnext.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import com.teamupnext.robot.commands.CommandBase;
import com.teamupnext.robot.commands.DoSomeAutonomousStuff;
import com.teamupnext.robot.commands.DoSomeOtherStuff;

import com.teamupnext.robot.commands.DriveForwardDump;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Up Next!
 */
public class UpNext2012Robot extends IterativeRobot {

    CommandGroup autonomous;
    //SendableChooser autoChooser;
    DriverStation dStation;

    public UpNext2012Robot() {
        super();

        Utils.clearDriverStation();
        dStation = DriverStation.getInstance();
    }

    public void robotInit() {
        CommandBase.init();

        // smart dashboard stuff
        //autoChooser = new SendableChooser();
        //autoChooser.addDefault("Default program", new DoSomeAutonomousStuff());
        //autoChooser.addObject("Eperimental auto", new DoSomeOtherStuff());
        //SmartDashboard.putData("Autonomous mode chooser", autoChooser);

        CommandBase.dumper.reset();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //updateDashboard();
    }

    public void autonomousInit() {
        
        //autonomousCommand = (Command)autoChooser.getSelected();
        //autonomousCommand.start();
        //System.out.println("\n\n**** in auto init ***\n\n");

        //auto = new DriveStraight();
        //auto.start();

        Utils.clearDriverStation();

        if (dStation.getDigitalIn(RobotMap.OPTION_DO_NOTHING)) //If selected, no other options are needed
        {
            Logger.logMessage(Logger.LogLevel.INFO, "Do nothing");
            return; //can skip rest of initilization
        }

        
        
        /*if (dStation.getDigitalIn(RobotMap.OPTION_DUMP_RIGHT)) //Can dump on Right or Left
        {
            System.out.println("Drive Right");
            Utils.printToDriverStation("Drive Right");
        } else if (dStation.getDigitalIn(RobotMap.OPTION_DUMP_LEFT)) {
            System.out.println("Drive Left");
            Utils.printToDriverStation("Drive Left");
        }

        if (dStation.getDigitalIn(RobotMap.OPTION_BRIDGE_FIRST)) //Can tip the bridge before or after the dump
        {
            System.out.println("Bridge First");
            Utils.printToDriverStation("Bridge First");
        } else if (dStation.getDigitalIn(RobotMap.OPTION_BRIDGE_LAST)) {
            System.out.println("Bridge Last");
            Utils.printToDriverStation("Bridge Last");
        }*/
        
        //System.out.println("----------------> made it 1!!!!");
        autonomous = new DriveForwardDump();
        //System.out.println("----------------> made it!!!! 2");
        autonomous.start();
        //System.out.println("----------------> made it!!!! 3");
        //Logger.logMessage(Logger.LogLevel.INFO, "Driving Forward and Dumping");
        
        
    }

    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    } 
    /*public void autonomousContinuous() {
        //Scheduler.getInstance().run();
    }*/

    void updateDashboard() {
        Dashboard lowDashData = DriverStation.getInstance().getDashboardPackerLow();
        lowDashData.addCluster();
        {
            lowDashData.addCluster();
            {     //analog modules
                lowDashData.addCluster();
                {
                    for (int i = 1; i <= 8; i++) {
                        lowDashData.addFloat((float) AnalogModule.getInstance(1).getAverageVoltage(i));
                    }
                }
                lowDashData.finalizeCluster();
                lowDashData.addCluster();
                {
                    for (int i = 1; i <= 8; i++) {
                        lowDashData.addFloat((float) 0);
                    }
                }
                lowDashData.finalizeCluster();
            }
            lowDashData.finalizeCluster();

            lowDashData.addCluster();
            { //digital modules
                lowDashData.addCluster();
                {
                    lowDashData.addCluster();
                    {
                        int module = 1;
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
                        lowDashData.addCluster();
                        {
                            for (int i = 1; i <= 10; i++) {
                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                            }
                        }
                        lowDashData.finalizeCluster();
                    }
                    lowDashData.finalizeCluster();
                }
                lowDashData.finalizeCluster();

                lowDashData.addCluster();
                {
                    lowDashData.addCluster();
                    {
                        int module = 0;
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayReverse());
                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
                        lowDashData.addCluster();
                        {
                            for (int i = 1; i <= 10; i++) {
                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                            }
                        }
                        lowDashData.finalizeCluster();
                    }
                    lowDashData.finalizeCluster();
                }
                lowDashData.finalizeCluster();

            }
            lowDashData.finalizeCluster();

            lowDashData.addByte(Solenoid.getAllFromDefaultModule());
        }
        lowDashData.finalizeCluster();
        lowDashData.commit();

    }
}
