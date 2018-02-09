package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Side;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperShootToSwitch;
import org.usfirst.frc.team4947.robot.commands.pivot.PivotToSwitchPosition;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;
import org.usfirst.frc.team4947.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightTakeSwitch extends CommandGroup {
	
	// Constants.
	public static final String NAME = "AutoRightTakeSwitch";

	// Members.
	private DriveTrain driveTrain;
	private Pivot pivot;
	private Gripper gripper;
	
	public AutoRightTakeSwitch(DriveTrain driveTrain, Pivot pivot, Gripper gripper) {
		super(NAME);

		this.driveTrain = driveTrain;
		this.pivot = pivot;
		this.gripper = gripper;
	}
	
	public void setSide(Side side) {
		if (side == Side.LEFT) {
			addSequential(new DriveDistance(driveTrain, 1.0));	// Drive to turn.
			addSequential(new DriveRotate(driveTrain,-25.0));	// Turn to the switch.
			addSequential(new DriveDistance(driveTrain, 10.0)); // Drive to the switch.
			addSequential(new DriveRotate(driveTrain,25.0));	// Turn to the switch for real.
			addSequential(new PivotToSwitchPosition(pivot));	// Set pivot output to height of switch.
			addSequential(new GripperShootToSwitch(gripper));	// Throw cube.
			addSequential(new DriveDistance(driveTrain, -1.5));	// Get out of there to let other teams do their thing using the switch.
		} else if (side == Side.RIGHT) {
			addSequential(new DriveDistance(driveTrain, 1.0));	// Drive to turn.
			addSequential(new DriveRotate(driveTrain,25.0));	// Turn to the switch.
			addSequential(new DriveDistance(driveTrain, 10.0)); // Drive to the switch.
			addSequential(new DriveRotate(driveTrain,-25.0));	// Turn to the switch for real.
			addSequential(new PivotToSwitchPosition(pivot));	// Set pivot output to height of switch.
			addSequential(new GripperShootToSwitch(gripper));	// Throw cube.
			addSequential(new DriveDistance(driveTrain, -1.5));	// Get out of there to let other teams do their thing using the switch.
		} else {
			addSequential(new DriveDistance(driveTrain, 14.0));	// Drive to switch.
		}
	}
}