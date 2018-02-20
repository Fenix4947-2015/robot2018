package org.usfirst.frc.team4947.robot.commands.gripper;

import org.usfirst.frc.team4947.robot.OI.XBoxAxis;
import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GripperDefault extends Command {
	
	public GripperDefault() {
		requires(Robot.gripper);
	}

	// Called just before the command runs the first time.
	protected void initialize() {
	}

	// Called repeatedly when the command is scheduled to run.
	protected void execute() {
		updateOpening();
		updateSpeed();
	}
	
	private void updateOpening() {
		double leftStickAxisY = Robot.oi.getJoystickHelperAxis(XBoxAxis.LEFT_STICK_Y, 0.1);
		if (leftStickAxisY > 0.5) {
			Robot.gripper.open();
		} else if (leftStickAxisY < -0.5) {
			Robot.gripper.close();
		}
	}
	
	private void updateSpeed() {
		double leftTrigger = -Robot.oi.getJoystickHelperAxis(XBoxAxis.LEFT_TRIGGER);
		double rightTrigger = Robot.oi.getJoystickHelperAxis(XBoxAxis.RIGHT_TRIGGER);
		
		double percentOutput = (leftTrigger + rightTrigger);
		double direction = Math.signum(Double.compare(rightTrigger, leftTrigger));

		Robot.gripper.rotateFlip(direction * percentOutput);
	}
	
	// Called when another command which requires one or more of the same subsystems is scheduled to run.
	protected void interrupted() {
	}

	// Make this return TRUE when the command no longer needs to run execute().
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns TRUE.
	protected void end() {
	}
}