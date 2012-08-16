package com.teamupnext.robot.subsystems;

import com.teamupnext.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Up Next!
 */
public class BridgeTipper extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Relay motor;
    private DigitalInput microSwitchUp;
    private int count = 0;

    public void initDefaultCommand() {
        motor = new Relay(RobotMap.BRIDGE_RELAY_PORT);
        microSwitchUp = new DigitalInput(RobotMap.TIPPER_SWITCH_UP_PORT);
    }

    public void LowerTipper() {
        motor.set(RobotMap.TIPPER_DOWN);
    }

    public void RaiseTipper() {
        if (count < RobotMap.COUNT_MAX) {
            StopTipper();
            count++;
            return;
        }
        
        motor.set(RobotMap.TIPPER_UP);
        count = 0;
    }

    public void StopTipper() {
        motor.set(Relay.Value.kOff);
    }

    public boolean IsUp() {
        return microSwitchUp.get();
    }
}
