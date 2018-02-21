package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.OI.XBoxAxis;
import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.subsystems.Platform;

import edu.wpi.first.wpilibj.command.Command;

public class PlatformPull extends Command {

	private Platform platformSubsystem;
	private XBoxAxis xboxAxis;

	public PlatformPull(Platform platformSubsystem) {
		requires(platformSubsystem);

		this.platformSubsystem = platformSubsystem;
		this.xboxAxis = xboxAxis;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double desiredSpeed = Robot.oi.getJoystickDriverAxis(XBoxAxis.RIGHT_STICK_Y);
		platformSubsystem.liftSpeed(desiredSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		double desiredSpeed = 0;
		platformSubsystem.liftSpeed(desiredSpeed);
	}

	// Called when another command which requires one or more of the same subsystems is scheduled to run
	protected void interrupted() {
		double desiredSpeed = 0;
		platformSubsystem.liftSpeed(desiredSpeed);
	}
}
