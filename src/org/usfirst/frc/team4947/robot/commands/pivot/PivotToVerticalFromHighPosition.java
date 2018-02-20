package org.usfirst.frc.team4947.robot.commands.pivot;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PivotToVerticalFromHighPosition extends Command {

	private boolean lastVerticalPosRead;
	private boolean verticalPosReached;
	
	public PivotToVerticalFromHighPosition() {
		requires(Robot.pivot);
		
		verticalPosReached = false;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		Robot.pivot.moveToLowPos();
	}

	// Called repeatedly when the command is scheduled to run.
	protected void execute() {
		lastVerticalPosRead = Robot.pivot.isAtVerticalPos();
		verticalPosReached |= lastVerticalPosRead;
	}

	// Called when another command which requires one or more of the same subsystems is scheduled to run.
	protected void interrupted() {
		end();
	}

	// Make this return TRUE when the command no longer needs to run execute().
	protected boolean isFinished() {
		return (verticalPosReached && !lastVerticalPosRead);
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
	}
}