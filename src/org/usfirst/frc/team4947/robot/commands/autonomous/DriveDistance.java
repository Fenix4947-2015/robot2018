package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {

	// Constants.
	private static final double DISTANCE_THRESHOLD = 0.003;
	
	// Members.
	private DriveTrain driveTrain;
	private double distanceFeet;
	
	public DriveDistance(DriveTrain driveTrain, double distanceFeet) {
		this.driveTrain = driveTrain;
		this.distanceFeet = distanceFeet;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		setTimeout(10);
		driveTrain.driveToDistance(distanceFeet);
	}
	
	// Called repeatedly when the command is scheduled to run.
	protected void execute() {
	}

	// Called when another command which requires one or more of the same subsystems is scheduled to run.
	protected void interrupted() {
		end();
	}

	// Make this return TRUE when the command no longer needs to run execute().
	protected boolean isFinished() {
		boolean reachedPosition = (driveTrain.getEncoderDistanceError()<DISTANCE_THRESHOLD);
        return (reachedPosition|| isTimedOut());
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
		driveTrain.driveStop();
	}
}