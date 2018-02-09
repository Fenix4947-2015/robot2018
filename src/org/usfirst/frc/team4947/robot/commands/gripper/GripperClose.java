package org.usfirst.frc.team4947.robot.commands.gripper;

import org.usfirst.frc.team4947.robot.subsystems.Gripper;

import edu.wpi.first.wpilibj.command.Command;

public class GripperClose extends Command {
	
	// Constants.
	private static final boolean OPENER_SOLENOID_OUTPUT = !GripperOpen.OPENER_SOLENOID_OUTPUT;
	
	// Members.
	private Gripper gripper;

	public GripperClose(Gripper gripper) {
		requires(gripper);
		
		this.gripper = gripper;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		gripper.getOpenerSolenoid().set(OPENER_SOLENOID_OUTPUT);
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