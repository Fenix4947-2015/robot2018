package org.usfirst.frc.team4947.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;

public class DriveRotate extends Command {
	
	// Constants.
	public static final double DEFAULT_SPEED = 0.1;

	public DriveRotate(double degrees) {
		this(degrees, DEFAULT_SPEED);
	}
	
	public DriveRotate(double degrees, double speed) {
	}

	// Called just before the command runs the first time.
	protected void initialize() {
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