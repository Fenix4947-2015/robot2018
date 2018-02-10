package org.usfirst.frc.team4947.robot.commands.joystick;

import org.usfirst.frc.team4947.robot.subsystems.Platform;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateEndGameProfile extends Command {
	
	public ActivateEndGameProfile(Platform platformLeft, Platform platformRight) {
		requires(platformLeft);
		requires(platformRight);

		// TODO: Set default command for both platform for end game.
		//platformLeft.setDefaultCommand(null);
		//platformRight.setDefaultCommand(null);
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
		return true;
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
	}
}