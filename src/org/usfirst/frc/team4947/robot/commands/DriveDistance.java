package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {
	
	double desiredDistance;
	double DISTANCE_THRESHOLD = 0.01; // meters
	
    public DriveDistance(double distance_meter) {
    	requires(Robot.driveTrain);
    	desiredDistance = distance_meter; // distance in meters. 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(10); // todo : validate if the timeout is required and if the value is appropriate. 
    	Robot.driveTrain.DriveToDistance(desiredDistance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean reachedPosition = (Robot.driveTrain.GetEncoderDistanceError()<DISTANCE_THRESHOLD);
        return (reachedPosition|| isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.DriveStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.DriveStop();
    }
}
