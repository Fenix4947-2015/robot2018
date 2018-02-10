package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTime extends Command {

    public DriveTime(double seconds) {
        // Use requires() here to declare subsystem dependencies
       requires(Robot.driveTrain);
   		Timer.delay(seconds);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.driveArcadeMethod(1, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.driveArcadeMethod(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
