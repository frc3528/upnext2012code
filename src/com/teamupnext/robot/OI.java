package com.teamupnext.robot;

import com.teamupnext.robot.commands.DriveForwardEncoder;
import com.teamupnext.robot.commands.DriveWhileHeld;
import com.teamupnext.robot.commands.LowerBridgeTipper;
import com.teamupnext.robot.commands.LowerScissorLift;
import com.teamupnext.robot.commands.RaiseBridgeTipper;
import com.teamupnext.robot.commands.RaiseScissorLift;
import com.teamupnext.robot.commands.ReleaseBalls;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

public class OI {
    // Process operator interface input here.

    private Joystick drivingJoystick;
    private Joystick controlsJoystick;
    private JoystickButton scissorUpButton;
    private JoystickButton scissorDownButton;
    private JoystickButton releaseButton;
    private JoystickButton tipperDownButton;
    private JoystickButton tipperUpButton;
    //private JoystickButton testButton;

    /*
    private JoystickButton button1;
    private JoystickButton button2;
    private JoystickButton button3;
    private JoystickButton button4;//for bridge tipper
    private JoystickButton button5;
    private JoystickButton button6;
     */
    public OI() {

        //Driving Joystick
        // --Drive with left Joystick
        // --Bumpers for scissorrs

        //Controls Joystick
        // --Left Joystick for conveyor
        // --Button A for Release
        // --Bridge Tipper X and Y
        // hi steve!!!


        drivingJoystick = new Joystick(RobotMap.DRIVING_JOYSTICK_PORT);
        controlsJoystick = new Joystick(RobotMap.CONTROLS_JOYSTICK_PORT);

        scissorUpButton = new JoystickButton(drivingJoystick, RobotMap.RIGHT_BUMPER);
        scissorDownButton = new JoystickButton(drivingJoystick, RobotMap.LEFT_BUMPER);
        releaseButton = new JoystickButton(controlsJoystick, RobotMap.A_BUTTON);
        tipperDownButton = new JoystickButton(controlsJoystick, RobotMap.X_BUTTON);
        tipperUpButton = new JoystickButton(controlsJoystick, RobotMap.Y_BUTTON);

        //testButton = new JoystickButton(controlsJoystick, RobotMap.B_BUTTON);
        //testButton.whenPressed(new DriveForwardEncoder(10000, 1));

        scissorUpButton.whenPressed(new RaiseScissorLift());
        scissorDownButton.whenPressed(new LowerScissorLift());
        releaseButton.whenPressed(new ReleaseBalls());
        tipperDownButton.whenPressed(new LowerBridgeTipper());
        tipperUpButton.whenPressed(new RaiseBridgeTipper());



        /*
        button1 = new JoystickButton(drivingJoystick, RobotMap.JOYSTICK_BUTTON_1);
        button2 = new JoystickButton(drivingJoystick, RobotMap.JOYSTICK_BUTTON_2);
        button3 = new JoystickButton(drivingJoystick, RobotMap.JOYSTICK_BUTTON_3);
        button4 = new JoystickButton(drivingJoystick, RobotMap.JOYSTICK_BUTTON_4);
        button5 = new JoystickButton(drivingJoystick, RobotMap.JOYSTICK_BUTTON_5);
        button6 = new JoystickButton(drivingJoystick, RobotMap.JOYSTICK_BUTTON_6);
        
        button1.whileHeld(new MoveConveyorUp()); //runs the conveyor up while button 1 is pressed
        button2.whileHeld(new MoveConveyorDown()); //reverses conveyor when the button 2 is pressed
        //button3.whenPressed(new ReleaseBalls());//release the balls on button 3 pressed
        button3.whenPressed(new RaiseBridgeTipper()); //raise bridge tipper
        button4.whenPressed(new LowerBridgeTipper()); //lower Bridge Tipper
        button5.whenPressed(new LowerScissorLift()); //lower scissor
        button6.whenPressed(new RaiseScissorLift()); //raise scissor      
         */
    }

    public Joystick getDrivingJoystick() {
        return drivingJoystick;
    }

    public Joystick getControlsJoystick() {
        return controlsJoystick;
    }
}
