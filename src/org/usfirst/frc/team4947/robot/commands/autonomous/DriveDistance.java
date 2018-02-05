package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {
	
	// Members.
	private final DriveTrain driveTrain;
	private final double distance_feet;

	public DriveDistance(DriveTrain driveTrain, double distance_feet) {
		this.driveTrain = driveTrain;
		this.distance_feet = distance_feet;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		driveTrain.DriveToDistance(distance_feet);
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
		return false;
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
	}
}