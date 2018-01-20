package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.OI.XBoxAxis;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PlatformPull extends Command {

    public PlatformPull() {
    	requires(Robot.platformLeft);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double desiredSpeed = Robot.oi.getJoystickDriverAxis(XBoxAxis.LeftTrigger);
    	Robot.platformLeft.LiftSpeed(desiredSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	double desiredSpeed = 0;
    	Robot.platformLeft.LiftSpeed(desiredSpeed);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	double desiredSpeed = 0;
    	Robot.platformLeft.LiftSpeed(desiredSpeed);
    }
}
