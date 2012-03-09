package com.teamupnext.robot.commands;

import com.teamupnext.robot.subsystems.Targeting;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author Up Next!
 */
public class NavigateToTarget extends CommandGroup {

    CriteriaCollection cc;
    ColorImage image;
    ParticleAnalysisReport[] m_reports;
    Targeting targeting;

    public NavigateToTarget() {

        cc = new CriteriaCollection();      // create the criteria for the particle filter
        cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 30, 400, false);
        cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 40, 400, false);
        targeting = new Targeting();
        m_reports = FindTargets();

        if (m_reports.length < 2) {
            //move backwards
        }

        if (m_reports.length == 2) {
            //if y-values or x-values are the same, decide closest
            //if no values are the same, move backwards

            if (m_reports[0].center_mass_y != m_reports[1].center_mass_y
                    && m_reports[0].center_mass_x != m_reports[1].center_mass_x) {
                //move backwards
            }

            if (m_reports[0].center_mass_y == m_reports[1].center_mass_y) {
                //seeing two medium goals
            }

            if (m_reports[0].center_mass_x == m_reports[1].center_mass_x) {
                //seeing high and low goal
            }
        }

        if (m_reports.length >= 3) {
            //find closest
        }


        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.


    }

    private ParticleAnalysisReport[] FindTargets() {
        ParticleAnalysisReport[] reports;
        image = targeting.getImage();

        try {
            BinaryImage thresholdImage = image.thresholdRGB(25, 255, 0, 45, 0, 47);   // keep only red objects
            BinaryImage bigObjectsImage = thresholdImage.removeSmallObjects(false, 2);  // remove small artifacts
            BinaryImage convexHullImage = bigObjectsImage.convexHull(false);          // fill in occluded rectangles
            BinaryImage filteredImage = convexHullImage.particleFilter(cc);           // find filled in rectangles

            reports = filteredImage.getOrderedParticleAnalysisReports();  // get list of results

            if (reports.length < 2) {
                return null;
            }

            Coordinates closest = ClosestGoal(reports);

            /*for (int i = 0; i < reports.length; i++) 
            {     // print results
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
            return null;
        }

        return reports;
    }

    //assumes the array is populated
    //decides which goal is the closest
    private Coordinates ClosestGoal(ParticleAnalysisReport[] reports) {
        Coordinates coordinates = null;
        int maxX = 0;//the index of the report with the max X coordinates
        int minX = 0;//the index of the report with the min X coordinates
        int newX = 0;//the x value of the report

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
            //minX is closer
            coordinates = new Coordinates(reports[minX].center_mass_x, reports[minX].center_mass_y);
        } else {
            //maxX is closer
            coordinates = new Coordinates(reports[maxX].center_mass_x, reports[maxX].center_mass_y);
        }


        return coordinates;
    }
}