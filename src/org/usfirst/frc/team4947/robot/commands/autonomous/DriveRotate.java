package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveRotate extends Command {

	// Constants.
	private static final double DEFAULT_SPEED = 0.1;
	private static final double Tolerance = 3; // degree

	// Members.
	private double degrees;
	private double speed;

	public DriveRotate(double degrees) {
		this(degrees, DEFAULT_SPEED);
	}

	public DriveRotate(double degrees, double speed) {
		requires(Robot.driveTrain);
		this.degrees = degrees;
		this.speed = speed;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		Robot.driveTrain.resetGyroAngle();
	}

	// Called repeatedly when the command is scheduled to run.
	protected void execute() {
		if (degrees < Robot.driveTrain.getGyroAngle()) {
			// Robot.driveTrain.robotDrive.tankDrive(-speed*convertspd2degpsec, speed*convertspd2degpsec);
			Robot.driveTrain.driveArcadeMethod(0, speed);
		} else if (Robot.driveTrain.getGyroAngle() < degrees) {
			// Robot.driveTrain.robotDrive.tankDrive(speed*convertspd2degpsec, -speed*convertspd2degpsec);
			Robot.driveTrain.driveArcadeMethod(0, -speed);
		}
	}

	// Called when another command which requires one or more of the same subsystems is scheduled to run.
	protected void interrupted() {
		end();
	}

	// Make this return TRUE when the command no longer needs to run execute().
	protected boolean isFinished() {
		return Math.abs((Robot.driveTrain.getGyroAngle() - degrees)) < Tolerance;// <>
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
		Robot.driveTrain.driveStop();
	}
}