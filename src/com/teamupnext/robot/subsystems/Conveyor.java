package com.teamupnext.robot.subsystems;

import com.teamupnext.robot.RobotMap;
import com.teamupnext.robot.commands.ConveyorWithJoystick;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Up Next!
 */
public class Conveyor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Jaguar conveyorMotor = null;

    public Conveyor() {
        super();

        conveyorMotor = new Jaguar(RobotMap.CONVEYOR_PORT);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ConveyorWithJoystick());
    }

    public void moveConveyorUp() {
        //motor.setDirection(CONVEYOR_UP);
        //conveyorMotor.set(RobotMap.CONVEYOR_UP);
        conveyorMotor.set(RobotMap.CONVEYOR_FORWARD);
    }

    public void moveConveyorDown() {
        conveyorMotor.set(RobotMap.CONVEYOR_REVERSE);
 
    }

    public void conveyorStop() {
        conveyorMotor.set(0);
    }

    public boolean isFull() {
        return false;
    }
}
