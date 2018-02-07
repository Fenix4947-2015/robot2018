package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveRotate extends Command {
	
	// Constants.
	public static final double DEFAULT_SPEED = 0.1;
	private final DriveTrain driveTrain;
	double degrees;
	double speed;
	private double Tolerance = 3 ; // degree 
	public DriveRotate(DriveTrain driveTrain,double degrees) {
		this(driveTrain,degrees, DEFAULT_SPEED);
	}
	
	public DriveRotate(DriveTrain drivetrain,double degrees, double speed) {
		this.driveTrain=drivetrain;
		degrees=degrees;
		this.speed=speed;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		driveTrain.resetGyroAngle();
	}
	
	// Called repeatedly when the command is scheduled to run.
	protected void execute() {
		if (degrees<driveTrain.getGyroAngle())
    	{     		
    	//Robot.driveTrain.robotDrive.tankDrive(-speed*convertspd2degpsec, speed*convertspd2degpsec);
    		driveTrain.driveArcadeMethod(0, speed);
    	}
    	else if (driveTrain.getGyroAngle()<degrees)
    	{     		
        	//Robot.driveTrain.robotDrive.tankDrive(speed*convertspd2degpsec, -speed*convertspd2degpsec);
    		driveTrain.driveArcadeMethod(0, -speed);
    	}
	}

	// Called when another command which requires one or more of the same subsystems is scheduled to run.
	protected void interrupted() {
		end();
	}

	// Make this return TRUE when the command no longer needs to run execute().
	protected boolean isFinished() {
		return Math.abs((driveTrain.getGyroAngle() - degrees)) < Tolerance ;//<>
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
		driveTrain.driveStop();
	}
}