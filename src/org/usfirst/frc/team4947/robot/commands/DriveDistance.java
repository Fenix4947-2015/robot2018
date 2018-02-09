package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {

	private static final double DISTANCE_THRESHOLD = 0.01; // meters
	
	private DriveTrain driveTrain;
	private double distanceMeter;

	public DriveDistance(DriveTrain driveTrain, double distanceMeter) {
		this.driveTrain = driveTrain;
		this.distanceMeter = distanceMeter;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(10); // TODO: validate if the timeout is required and if the value is appropriate.
		driveTrain.driveToDistance(distanceMeter);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean reachedPosition = (driveTrain.getEncoderDistanceError() < DISTANCE_THRESHOLD);
		return (reachedPosition || isTimedOut());
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.driveStop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		driveTrain.driveStop();
	}
}