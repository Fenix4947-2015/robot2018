package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class PlatformRelease extends Command {
	
	public PlatformRelease() {
		requires(Robot.platform);
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		boolean release = true;
		Robot.platform.unlockPlatform(release);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same subsystems is scheduled to run
	protected void interrupted() {
	}
}