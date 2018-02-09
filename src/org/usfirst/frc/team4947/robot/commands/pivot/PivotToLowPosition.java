package org.usfirst.frc.team4947.robot.commands.pivot;

import org.usfirst.frc.team4947.robot.subsystems.Pivot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class PivotToLowPosition extends Command {

	// Constants.
	private static final double MOTOR_PERCENT_OUTPUT = -0.5;
	private static final boolean STATE_POSITION_REACHED = true;

	// Members.
	private Pivot pivot;

	public PivotToLowPosition(Pivot pivot) {
		requires(pivot);
		
		this.pivot = pivot;
		
		setInterruptible(false);
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		pivot.getMotor().set(ControlMode.PercentOutput, MOTOR_PERCENT_OUTPUT);
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
		return (pivot.getLowPosDigitalInput().get() == STATE_POSITION_REACHED);
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
		pivot.getMotor().set(ControlMode.PercentOutput, 0.0);
	}
}