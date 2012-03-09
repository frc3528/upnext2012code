package com.teamupnext.robot.commands;

/**
 *
 * @author Up Next!
 */
public class MoveConveyorDown extends CommandBase {

    public MoveConveyorDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        requires(conveyor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        conveyor.moveConveyorDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return conveyor.isFull();
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        conveyor.conveyorStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        conveyor.conveyorStop();//stops the conveyor when the button is not pressed
    }
}
