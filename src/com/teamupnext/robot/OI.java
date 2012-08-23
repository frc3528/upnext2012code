package com.teamupnext.robot;

import com.teamupnext.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
    // Process operator interface input here.

    private Joystick drivingJoystick;
    private Joystick controlsJoystick;
    private JoystickButton scissorUpButton;
    private JoystickButton scissorDownButton;
    private JoystickButton releaseButton;
    private JoystickButton tipperDownButton;
    private JoystickButton tipperUpButton;

    public OI() {

        //Driving Joystick
        // --Drive with left Joystick
        // --Bumpers for scissorrs

        //Controls Joystick
        // --Left Joystick for conveyor
        // --Button A for Release
        // --Bridge Tipper X and Y


        drivingJoystick = new Joystick(RobotMap.DRIVING_JOYSTICK_PORT);
        controlsJoystick = new Joystick(RobotMap.CONTROLS_JOYSTICK_PORT);

        scissorUpButton = new JoystickButton(drivingJoystick, RobotMap.RIGHT_BUMPER);
        scissorDownButton = new JoystickButton(drivingJoystick, RobotMap.LEFT_BUMPER);
        releaseButton = new JoystickButton(controlsJoystick, RobotMap.A_BUTTON);
        tipperDownButton = new JoystickButton(controlsJoystick, RobotMap.X_BUTTON);
        tipperUpButton = new JoystickButton(controlsJoystick, RobotMap.Y_BUTTON);

        scissorUpButton.whenPressed(new RaiseScissorLift());
        scissorDownButton.whenPressed(new LowerScissorLift());
        releaseButton.whenPressed(new ReleaseBalls());
        tipperDownButton.whenPressed(new LowerBridgeTipper());
        tipperUpButton.whenPressed(new RaiseBridgeTipper());

    }

    public Joystick getDrivingJoystick() {
        return drivingJoystick;
    }

    public Joystick getControlsJoystick() {
        return controlsJoystick;
    }
}
