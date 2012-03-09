package com.teamupnext.robot.commands;

import com.teamupnext.robot.subsystems.Targeting;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author Up Next!
 */
public class FindTargetCoordinates extends CommandBase {

    CriteriaCollection cc;
    ColorImage image;
    ParticleAnalysisReport[] reports;
    Targeting targeting;

    public FindTargetCoordinates() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        //requires(targeting);
        targeting = new Targeting();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        cc = new CriteriaCollection();      // create the criteria for the particle filter
        cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 30, 400, false);
        cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 40, 400, false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        image = targeting.getImage();

        try {
            BinaryImage thresholdImage = image.thresholdRGB(25, 255, 0, 45, 0, 47);   // keep only red objects
            BinaryImage bigObjectsImage = thresholdImage.removeSmallObjects(false, 2);  // remove small artifacts
            BinaryImage convexHullImage = bigObjectsImage.convexHull(false);          // fill in occluded rectangles
            BinaryImage filteredImage = convexHullImage.particleFilter(cc);           // find filled in rectangles

            reports = filteredImage.getOrderedParticleAnalysisReports();  // get list of results

            if (reports.length < 2) {
                return;
            }

            /*for (int i = 0; i < reports.length; i++) 
            {                                // print results
            ParticleAnalysisReport r = reports[i];
            System.out.println("Particle: " + i + ":  Center of mass x: " + r.center_mass_x);
            }*/

            filteredImage.free();
            convexHullImage.free();
            bigObjectsImage.free();
            thresholdImage.free();
            image.free();
        } catch (Exception e) {
            System.out.println("Error finding target: " + e.getMessage());
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    //return the coordinates of the closest target
    private Coordinates FindClosest(ParticleAnalysisReport[] reports) {
        Coordinates coordinates = null;
        int minX = 0;
        int maxX = 0;
        int newX = 0;

        //find most left and most right and decide which is closer
        for (int i = 1; i < reports.length; i++) {
            newX = reports[i].center_mass_x;

            if (newX < reports[minX].center_mass_x) {
                minX = i;
            }

            if (newX > reports[maxX].center_mass_x) {
                maxX = i;
            }
        }

        if (Math.abs(reports[minX].center_mass_x) < Math.abs(reports[maxX].center_mass_x)) {
            //closest is minX
            coordinates = new Coordinates(reports[minX].center_mass_x, reports[minX].center_mass_y);
        } else {
            //closest is maxX
            coordinates = new Coordinates(reports[maxX].center_mass_x, reports[maxX].center_mass_y);
        }


        return coordinates;
    }
}
