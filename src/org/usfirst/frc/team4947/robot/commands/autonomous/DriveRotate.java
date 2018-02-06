package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveRotate extends Command {
	
	// Constants.
	public static final double DEFAULT_SPEED = 0.1;

	double angle=0.0;
	double speed=DEFAULT_SPEED;
	private double Tolerance = 3 ; // degree 
	public DriveRotate(double degrees) {
		this(degrees, DEFAULT_SPEED);
	}
	
	public DriveRotate(double degrees, double speed) {
		angle=degrees;
		this.speed=speed;
	}

	// Called just before the command runs the first time.
	protected void initialize() {
		Robot.driveTrain.ResetGyroAngle();
	}
	
	// Called repeatedly when the command is scheduled to run.
	protected void execute() {
		if (angle<Robot.driveTrain.GetGyroAngle())
    	{     		
    	//Robot.driveTrain.robotDrive.tankDrive(-speed*convertspd2degpsec, speed*convertspd2degpsec);
    		Robot.driveTrain.DriveArcadeMethod(0, speed);
    	}
    	else if (Robot.driveTrain.GetGyroAngle()<angle)
    	{     		
        	//Robot.driveTrain.robotDrive.tankDrive(speed*convertspd2degpsec, -speed*convertspd2degpsec);
    		Robot.driveTrain.DriveArcadeMethod(0, -speed);
    	}
	}

	// Called when another command which requires one or more of the same subsystems is scheduled to run.
	protected void interrupted() {
		end();
	}

	// Make this return TRUE when the command no longer needs to run execute().
	protected boolean isFinished() {
		return Math.abs((Robot.driveTrain.GetGyroAngle() - angle)) < Tolerance ;//<>
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
		Robot.driveTrain.DriveStop();
	}
}