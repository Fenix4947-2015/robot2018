package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.OI.XBoxAxis;
import org.usfirst.frc.team4947.robot.OI.XBoxButton;
import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.subsystems.Platform;

import edu.wpi.first.wpilibj.command.Command;

public class PlatformDefault extends Command {
	
	// Constants.
	private static final double DEAD_BAND = 0.05;
	
	// Members.
	private Platform platform;
	private XBoxButton unlockButton;
	private XBoxAxis liftSpeedAxis;

	public PlatformDefault(Platform platform, XBoxButton unlockButton, XBoxAxis liftSpeedAxis) {
		requires(platform);
		
		this.platform = platform;
		this.unlockButton = unlockButton;
		this.liftSpeedAxis = liftSpeedAxis;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
	}

	// Called repeatedly when the command is scheduled to run.
	protected void execute() {
		updateUnlock();
		updateLiftSpeed();
	}
	
	private void updateUnlock() {
		boolean pressed = Robot.oi.getJoystickHelperButton(unlockButton);
		if (pressed) {
			platform.unlockPlatform(true);
		}
	}
	
	private void updateLiftSpeed() {
		double value = Robot.oi.getJoystickHelperAxis(liftSpeedAxis, DEAD_BAND);
		platform.liftSpeed(value);
	}
	
	// Called when another command which requires one or more of the same subsystems is scheduled to run.
	protected void interrupted() {
	}

	// Make this return TRUE when the command no longer needs to run execute().
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
	}
}