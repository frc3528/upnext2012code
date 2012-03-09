package com.teamupnext.robot.subsystems;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.ColorImage;

/**
 *
 * @author Up Next!
 */
public class Targeting extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static final int CAMERA_BRIGHTNESS = 50;
    private AxisCamera camera = null;

    public Targeting() {
        camera = AxisCamera.getInstance();
        camera.writeBrightness(CAMERA_BRIGHTNESS);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    //return a ColorImage from the Axis Camera.  May return null if there was an error getting the image.
    public ColorImage getImage() {
        try {
            return camera.getImage();
        } catch (Exception e) {
            System.out.println("Error getting image: " + e.getMessage());
            return null;
        }
    }
}
