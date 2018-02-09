package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/** LE CODE EST POUR PILOTER SEULEMENT !!!!!!!! */
public class DriveRotate extends Command {

	private static final double WHEEL_SPACING = 20 * 0.0254; // 20 inches to meter.
	private static final double TOLERANCE_IN_DEGREES = 5; // degree
	private static final double CONVERT_SPD_2_DEG_PER_SEC = 1; // to calculate with wheel spacing and linear speed.
	
	private DriveTrain driveTrain;
	private double angleLeftDegrees;
	private double speedDegPerSec;

	public DriveRotate(DriveTrain driveTrain, double angleLeftDegrees, double speedDegPerSec) {
		this.driveTrain = driveTrain;
		this.angleLeftDegrees = angleLeftDegrees;
		this.speedDegPerSec = speedDegPerSec;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		driveTrain.resetGyroAngle();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (angleLeftDegrees < driveTrain.getGyroAngle()) {
			// Robot.driveTrain.robotDrive.tankDrive(-speed * convertspd2degpsec, speed*convertspd2degpsec);
			driveTrain.driveArcadeMethod(0, speedDegPerSec * CONVERT_SPD_2_DEG_PER_SEC);
		} else if (driveTrain.getGyroAngle() < angleLeftDegrees) {
			// Robot.driveTrain.robotDrive.tankDrive(speed * convertspd2degpsec, -speed*convertspd2degpsec);
			driveTrain.driveArcadeMethod(0, -speedDegPerSec * CONVERT_SPD_2_DEG_PER_SEC);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs((driveTrain.getGyroAngle() - angleLeftDegrees)) < TOLERANCE_IN_DEGREES;// <>
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