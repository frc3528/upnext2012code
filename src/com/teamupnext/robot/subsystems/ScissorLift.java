package com.teamupnext.robot.subsystems;

import com.teamupnext.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Up Next!
 */
public class ScissorLift extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //private Relay scissorMotor = null;
    private DigitalInput microswitchHigh;
    private DigitalInput microswitchLow;
    private Jaguar scissorMotor = null;

    public ScissorLift() {
        microswitchHigh = new DigitalInput(RobotMap.SCISSOR_HIGH_SWITCH);
        microswitchLow = new DigitalInput(RobotMap.SCISSOR_LOW_SWITCH);

        try {
            scissorMotor = new Jaguar(RobotMap.SCISSOR_MOTOR);
        } catch (Exception e) {
            System.out.println("Error intializing scissor jag " + e.getMessage());
        }
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void raiseScissorLift() {
        scissorMotor.set(RobotMap.SCISSOR_RAISE_VALUE);
    }

    //powers the scissor lift at a percentage of the power
    //power should be between 0 and 100
    public void raiseScissorLift(double powerPercentage) {
        scissorMotor.set(RobotMap.SCISSOR_RAISE_VALUE * powerPercentage / 100);
    }

    public void lowerScissorLift() {
        scissorMotor.set(RobotMap.SCISSOR_LOWER_VALUE);
    }

    public void stopScissorLift() {
        scissorMotor.set(0);
    }

    public boolean isUp() {
        return microswitchHigh.get();
    }

    public boolean isDown() {
        return microswitchLow.get();
    }
}
