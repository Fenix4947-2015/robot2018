package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.OI.XBoxAxis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveArcade extends Command {

    public DriveArcade() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// motion forward is with left trigger, backwards with right trigger.
    	double moveValue = -Robot.oi.getJoystickDriverAxis(XBoxAxis.LeftTrigger) + Robot.oi.getJoystickDriverAxis(XBoxAxis.RightTrigger);
    	
    	double rotationValueGain = 0.65; // for full rotation speed, use 1. Tune to have smoother rotation.
    	double rotateValue = - Robot.oi.getJoystickDriverAxis(XBoxAxis.LeftStickX, 0.1) * rotationValueGain;
    	
    	Robot.driveTrain.DriveArcadeMethod(moveValue, rotateValue);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.DriveArcadeMethod(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
}
