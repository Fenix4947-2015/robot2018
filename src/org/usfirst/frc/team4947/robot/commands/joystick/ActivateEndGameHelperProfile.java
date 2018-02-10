package org.usfirst.frc.team4947.robot.commands.joystick;

import org.usfirst.frc.team4947.robot.OI.XBoxAxis;
import org.usfirst.frc.team4947.robot.OI.XBoxButton;
import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.commands.PlatformDefault;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateEndGameHelperProfile extends Command {
	
	public ActivateEndGameHelperProfile() {
		requires(Robot.gripper);
		
		requires(Robot.platformLeft);
		requires(Robot.platformRight);
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		Robot.gripper.setDefaultCommand(null);

		Robot.platformLeft.setDefaultCommand(new PlatformDefault(Robot.platformLeft, XBoxButton.LB, XBoxAxis.LEFT_TRIGGER));
		Robot.platformLeft.setDefaultCommand(new PlatformDefault(Robot.platformRight, XBoxButton.RB, XBoxAxis.RIGHT_TRIGGER));		
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