package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain.ShifterSpeed;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftDown extends Command {
	private final DriveTrain driveTrain;
    public ShiftDown(DriveTrain driveTrain) 
    {
		this.driveTrain=driveTrain;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveTrain.gearboxShift(ShifterSpeed.Slow);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
