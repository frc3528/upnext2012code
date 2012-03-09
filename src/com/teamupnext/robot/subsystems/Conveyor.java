package com.teamupnext.robot.subsystems;

import com.teamupnext.robot.RobotMap;
import com.teamupnext.robot.commands.ConveyorWithJoystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Up Next!
 */
public class Conveyor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Relay conveyorMotor = null;

    public Conveyor() {
        super();

        conveyorMotor = new Relay(RobotMap.CONVEYOR_SPIKE_RELAY);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ConveyorWithJoystick());
    }

    public void moveConveyorUp() {
        //motor.setDirection(CONVEYOR_UP);
        conveyorMotor.set(RobotMap.CONVEYOR_UP);
    }

    public void moveConveyorDown() {
        conveyorMotor.set(RobotMap.CONVEYOR_DOWN);
    }

    public void conveyorStop() {
        conveyorMotor.set(Relay.Value.kOff);
    }

    public boolean isFull() {
        return false;
    }
}
