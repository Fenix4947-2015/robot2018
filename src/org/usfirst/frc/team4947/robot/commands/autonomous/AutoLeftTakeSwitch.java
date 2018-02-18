package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Side;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;
import org.usfirst.frc.team4947.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftTakeSwitch extends CommandGroup {
	
	// Constants.
	public static final String NAME = "AutoLeftTakeSwitch";

	// Members.
	private Pivot pivot;
	private Gripper gripper;
	
	public AutoLeftTakeSwitch( Pivot pivot, Gripper gripper) {
		super(NAME);

		this.pivot = pivot;
		this.gripper = gripper;
	}
	
	public void setSide(Side side) {
		if (side == Side.LEFT) {
			addSequential(new DriveDistance(14.0));	// Drive to switch.
			addSequential(new DriveRotate(90.0));	// Turn right.
			addSequential(new DriveDistance(1.5));	// Drive to touch switch.
			//addSequential(new PivotToSwitchPosition(pivot));	// Set pivot output to height of switch.
			//addSequential(new GripperShootToSwitch(gripper));	// Throw cube.
			addSequential(new DriveDistance(-1.5));	// Get out of there to let other teams do their thing using the switch.
		} else if (side == Side.RIGHT) {
			addSequential(new DriveDistance( 6.0));  // Foward.
			addSequential(new DriveRotate(90.0));	// Turn right.
			addSequential(new DriveDistance( 10.0)); // Drive to right switch.
			addSequential(new DriveRotate(-90.0));	// Turn left.
			addSequential(new DriveDistance( 6.0));  // Forward.
			//addSequential(new PivotToSwitchPosition(pivot));	// Set pivot output to height of switch.
			//addSequential(new GripperShootToSwitch(gripper));	// Throw cube.
			addSequential(new DriveDistance( -1.5));	// Get out of there to let other teams do their thing using the switch.
		} else {
			addSequential(new DriveDistance( 14.0));	// Drive to switch.
		}
	}
}