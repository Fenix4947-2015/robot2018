package org.usfirst.frc.team4947.robot.commands.gripper;

import org.usfirst.frc.team4947.robot.subsystems.Gripper;

import edu.wpi.first.wpilibj.command.Command;

public class GripperReject extends Command {
	
	// Members.
	private Gripper gripper;

	public GripperReject(Gripper gripper) {
		requires(gripper);
		
		this.gripper = gripper;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		gripper.reject();
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
		gripper.stop();
	}
}