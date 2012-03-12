package com.teamupnext.robot;

import edu.wpi.first.wpilibj.Relay;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    //Drive Train Constants
    public static final int FRONT_LEFT_MOTOR = 4;
    public static final int BACK_LEFT_MOTOR = 2;
    public static final int FRONT_RIGHT_MOTOR = 5;
    public static final int BACK_RIGHT_MOTOR = 3;
    public static final double SENSITIVITY = .5;
    //End Drive Train Constants
    //Autonomous Options Constants
    public static final int OPTION_DO_NOTHING = 1;
    public static final int OPTION_DUMP_RIGHT = 2;
    public static final int OPTION_DUMP_LEFT = 3;
    public static final int OPTION_BRIDGE_FIRST = 4;
    public static final int OPTION_BRIDGE_LAST = 5;
    //End Autonomous Options Constants
    //Conveyor Constants
    public static final int CONVEYOR_SPIKE_RELAY = 1;
    public static final Relay.Value CONVEYOR_UP = Relay.Value.kForward;
    public static final Relay.Value CONVEYOR_DOWN = Relay.Value.kReverse;
    public static final double CONVEYOR_DEADZONE = .5;
    //End Conveyor Constants
    //Scissor Lift Constants
    public static final int SCISSOR_HIGH_SWITCH = 1;
    public static final int SCISSOR_LOW_SWITCH = 2;
    public static final double SCISSOR_RAISE_VALUE = 1.0;
    public static final double SCISSOR_LOWER_VALUE = -1;
    public static final double SCISSOR_TIMEOUT_UP = 3;
    public static final double SCISSOR_TIMEOUT_DOWN = 4;
    public static final int SCISSOR_MOTOR = 1;
    //End Scissor Lift Constants
    //Sensors Constants
    public static final int GYRO_PORT = 2;
    public static final int RANGEFINDER_PORT = 3;
    //End Sensors Constants
    //Joystick Constants
    public static final int DRIVING_JOYSTICK_PORT = 1;
    public static final int CONTROLS_JOYSTICK_PORT = 2;
    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
    public static final int X_BUTTON = 3;
    public static final int Y_BUTTON = 4;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;
    public static final int JOYSTICK_BUTTON_1 = 1;
    public static final int JOYSTICK_BUTTON_2 = 2;
    public static final int JOYSTICK_BUTTON_3 = 3;
    public static final int JOYSTICK_BUTTON_4 = 4;
    public static final int JOYSTICK_BUTTON_5 = 5;
    public static final int JOYSTICK_BUTTON_6 = 6;
    //End Joystick Constants
    //Bridge Tipper Constants
    public static final int BRIDGE_RELAY_PORT = 4;
    public static final double BRIDGE_TIME_TO_UP = 3;
    public static final double BRIDGE_TIME_TO_DOWN = .4;
    public static final Relay.Value TIPPER_DOWN = Relay.Value.kForward;
    public static final Relay.Value TIPPER_UP = Relay.Value.kReverse;
    public static final int TIPPER_SWITCH_UP_PORT = 5;
    public static final int COUNT_MAX = 3;//cutting speed by one fourth
    //End Bridge Tipper Constants
    //Dump Constants
    public static final int DUMP_SERVO_CHANNEL = 5;
    public static final int DUMP_SERVO_UP = 60;
    public static final int DUMP_SERVO_RESET = 150;
    public static final int DUMP_TIMEOUT = 5;
    //End Dump Constants
    //Encoder Constants
    public static final int ENCODER_FRONT_RIGHT_CHANNEL_A = 1;
    public static final int ENCODER_FRONT_RIGHT_CHANNEL_B = 2;
    public static final int ENCODER_BACK_RIGHT_CHANNEL_A = 3;
    public static final int ENCODER_BACK_RIGHT_CHANNEL_B = 4;
    public static final int ENCODER_FRONT_LEFT_CHANNEL_A = 5;
    public static final int ENCODER_FRONT_LEFT_CHANNEL_B = 6;
    public static final int ENCODER_BACK_LEFT_CHANNEL_A = 7;
    public static final int ENCODER_BACK_LEFT_CHANNEL_B = 8;
    //End Encoder Constants
    //PID Constants
    public static final double MAX_FLAT_VELOCITY = 432.4090576171875;
    //End PID Constants
}

//test
//test2
