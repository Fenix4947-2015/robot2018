package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {

	// Constants.
	private static final double DISTANCE_THRESHOLD_FEET = 0.25; // three inches
	private static final int TIMEOUT_VALUE = 6; //sec
	// Members.
	private double distanceFeet;
	
	public DriveDistance(double distanceFeet) {
		requires(Robot.driveTrain);
		this.distanceFeet = distanceFeet;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		setTimeout(TIMEOUT_VALUE);
		Robot.driveTrain.driveToDistance(distanceFeet);		
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
		boolean reachedPosition = (Robot.driveTrain.getEncoderDistanceErrorFeet()<DISTANCE_THRESHOLD_FEET);
        return false; //(reachedPosition|| isTimedOut() || Robot.driveTrain.isRobotMoving());
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
		Robot.driveTrain.driveStop();
	}
}