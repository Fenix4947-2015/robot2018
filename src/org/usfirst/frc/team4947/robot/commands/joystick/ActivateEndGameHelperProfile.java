package org.usfirst.frc.team4947.robot.commands.joystick;

import org.usfirst.frc.team4947.robot.OI.XBoxAxis;
import org.usfirst.frc.team4947.robot.OI.XBoxButton;
import org.usfirst.frc.team4947.robot.commands.PlatformDefault;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;
import org.usfirst.frc.team4947.robot.subsystems.Platform;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateEndGameHelperProfile extends Command {
	
	// Members.
	private Platform platformLeft;
	private XBoxButton unlockButtonLeft;
	private XBoxAxis liftSpeedAxisLeft;
	
	private Platform platformRight;
	private XBoxButton unlockButtonRight;
	private XBoxAxis liftSpeedAxisRight;
	
	private Gripper gripper;

	public ActivateEndGameHelperProfile(
			Gripper gripper,
			Platform platformLeft, 
			XBoxButton unlockButtonLeft, 
			XBoxAxis liftSpeedAxisLeft,
			Platform platformRight, 
			XBoxButton unlockButtonRight, 
			XBoxAxis liftSpeedAxisRight) {
		requires(platformLeft);
		requires(platformRight);
		
		this.gripper = gripper;

		this.platformLeft = platformLeft;
		this.unlockButtonLeft = unlockButtonLeft;
		this.liftSpeedAxisLeft = liftSpeedAxisLeft;
		
		this.platformRight = platformRight;
		this.unlockButtonRight = unlockButtonRight;
		this.liftSpeedAxisRight = liftSpeedAxisRight;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		gripper.setDefaultCommand(null);

		platformLeft.setDefaultCommand(new PlatformDefault(platformLeft, unlockButtonLeft, liftSpeedAxisLeft));
		platformRight.setDefaultCommand(new PlatformDefault(platformRight, unlockButtonRight, liftSpeedAxisRight));		
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
		return true;
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
	}
}