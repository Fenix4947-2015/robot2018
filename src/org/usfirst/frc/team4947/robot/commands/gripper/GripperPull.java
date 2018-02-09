package org.usfirst.frc.team4947.robot.commands.gripper;

import org.usfirst.frc.team4947.robot.subsystems.Gripper;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class GripperPull extends Command {
	
	// Constants.
	private static final double MOTOR_PERCENT_OUTPUT = 0.5;
	private static final boolean STATE_CUBE_PRESENT = true;

	// Members.
	private Gripper gripper;

	public GripperPull(Gripper gripper) {
		requires(gripper);
		
		this.gripper = gripper;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		gripper.getLeftMotor().set(ControlMode.PercentOutput, MOTOR_PERCENT_OUTPUT);
		gripper.getRightMotor().set(ControlMode.PercentOutput, MOTOR_PERCENT_OUTPUT);
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
		return (gripper.getCubePresenceDigitalInput().get() == STATE_CUBE_PRESENT);
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
		gripper.getRightMotor().set(ControlMode.PercentOutput, 0.0);
		gripper.getLeftMotor().set(ControlMode.PercentOutput, 0.0);
	}
}