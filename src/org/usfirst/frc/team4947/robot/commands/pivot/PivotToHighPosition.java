package org.usfirst.frc.team4947.robot.commands.pivot;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PivotToHighPosition extends Command {

	public PivotToHighPosition() {
		requires(Robot.pivot);
		
		setInterruptible(false);
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		Robot.pivot.moveToHighPos();
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
		return Robot.pivot.isAtHighPos();
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
		Robot.pivot.stop();
	}
}