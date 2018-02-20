package org.usfirst.frc.team4947.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class WaitCommand extends Command {
	
	// Members.
	private double seconds;

	public WaitCommand(double seconds) {
		this.seconds = seconds;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		setTimeout(seconds);
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
		return isTimedOut();
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
	}
}