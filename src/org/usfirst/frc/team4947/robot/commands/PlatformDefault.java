package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.OI.XBoxAxis;
import org.usfirst.frc.team4947.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class PlatformDefault extends Command {
	
	// Constants.
	private static final double DEAD_BAND = 0.05;
	
	public PlatformDefault() 
	{
		requires(Robot.platform);		
	}

	// Called just before the command runs the first time.
	protected void initialize() {
	}

	// Called repeatedly when the command is scheduled to run.
	protected void execute() {		
		double value = Robot.oi.getJoystickDriverAxis(XBoxAxis.RIGHT_STICK_X, DEAD_BAND);
		Robot.platform.liftSpeed(value);
	}	
		
	// Called when another command which requires one or more of the same subsystems is scheduled to run.
	protected void interrupted() {
		Robot.platform.liftStop();
	}

	// Make this return TRUE when the command no longer needs to run execute().
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
		Robot.platform.liftStop();
	}
}