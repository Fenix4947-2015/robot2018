package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
//LE CODE EST POUR PILOTER SEULEMENT !!!!!!!!
public class DriveRotate extends Command {

	private double angle;
	private double speed;
	private double WheelSpacing = 20*0.0254; // 20 inches to meter. 
	private double convertspd2degpsec = 1; // to calculate with wheel spacing and linear speed. 
	private double Tolerance = 5 ; // degree 
	 
    public DriveRotate(double AngleLeftDegrees, double SpeedDegPerSec) {
        requires(Robot.driveTrain);
        
        this.angle = AngleLeftDegrees;
        this.speed = SpeedDegPerSec;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.ResetGyroAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {  
    	if (angle<Robot.driveTrain.GetGyroAngle())
    	{     		
    	//Robot.driveTrain.robotDrive.tankDrive(-speed*convertspd2degpsec, speed*convertspd2degpsec);
    		Robot.driveTrain.DriveArcadeMethod(0, speed*convertspd2degpsec);
    	}
    	else if (Robot.driveTrain.GetGyroAngle()<angle)
    	{     		
        	//Robot.driveTrain.robotDrive.tankDrive(speed*convertspd2degpsec, -speed*convertspd2degpsec);
    		Robot.driveTrain.DriveArcadeMethod(0, -speed*convertspd2degpsec);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs((Robot.driveTrain.GetGyroAngle() - angle)) < Tolerance ;//<>
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.DriveStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.DriveStop();
    }
}
