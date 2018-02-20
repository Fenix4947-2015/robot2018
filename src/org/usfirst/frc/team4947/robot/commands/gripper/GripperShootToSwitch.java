package org.usfirst.frc.team4947.robot.commands.gripper;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;

import edu.wpi.first.wpilibj.command.Command;

public class GripperShootToSwitch extends Command {


	public GripperShootToSwitch() {
		requires(Robot.gripper);

	}

	// Called just before the command runs the first time.
	protected void initialize() {
		Robot.gripper.shootToSwitch();
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
		Robot.gripper.stop();
	}
}