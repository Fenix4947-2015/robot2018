package org.usfirst.frc.team4947.robot.commands.gripper;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GripperAtFullSpeed extends Command {
	
	public GripperAtFullSpeed() {
		requires(Robot.gripper);
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		Robot.gripper.fullSpeed();
		setTimeout(0.5);
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