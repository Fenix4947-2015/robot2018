package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.OI.XBoxAxis;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveArcade extends Command {

	private DriveTrain driveTrain;

	public DriveArcade(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// motion forward is with left trigger, backwards with right trigger.
		double moveValue = -Robot.oi.getJoystickDriverAxis(XBoxAxis.LEFT_TRIGGER) + Robot.oi.getJoystickDriverAxis(XBoxAxis.RIGHT_TRIGGER);

		double rotationValueGain = 0.65; // for full rotation speed, use 1. Tune to have smoother rotation.
		double rotateValue = -Robot.oi.getJoystickDriverAxis(XBoxAxis.LEFT_STICK_X, 0.1) * rotationValueGain;

		driveTrain.driveArcadeMethod(moveValue, rotateValue);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.driveStop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		driveTrain.driveStop();
	}
}