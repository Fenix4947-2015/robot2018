package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveRotate extends Command {

	// Constants.
	private static final double DEFAULT_SPEED = 0.4;
	private static final double Tolerance = 5.0; // degree
	private static final double ACCURACY_ADJUSTMENT_FACTOR_FOR_END = 8.0;// factor to remove from input to compensate for stopping time

	// Members.
	private double degrees;
	private double speed;

	public DriveRotate(double degreesClockWise) {
		this(degreesClockWise, DEFAULT_SPEED);
	}

	public DriveRotate(double degreesClockWise, double speed) {
		requires(Robot.driveTrain);
		this.degrees = degreesClockWise - Math.signum(degreesClockWise) * ACCURACY_ADJUSTMENT_FACTOR_FOR_END;
		this.speed = speed;
		setTimeout(2.0);
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		Robot.driveTrain.resetGyroAngle();
	}

	// Called repeatedly when the command is scheduled to run.
	protected void execute() {
		double currentAngle = Robot.driveTrain.getGyroAngle();		
		
		if (degrees < currentAngle) 
		{			
			Robot.driveTrain.rawDrive(speed, -speed);
		} 
		else if (currentAngle < degrees) 
		{			
			Robot.driveTrain.rawDrive(-speed, speed);
		}
		
	}

	// Called when another command which requires one or more of the same subsystems is scheduled to run.
	protected void interrupted() {
		end();
	}

	// Make this return TRUE when the command no longer needs to run execute().
	protected boolean isFinished() {
		boolean reachedTarget = Math.abs((Robot.driveTrain.getGyroAngle() - degrees)) < Tolerance;
		return reachedTarget;//reachedTarget || isFinished();
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
		Robot.driveTrain.driveStop();		
	}
}