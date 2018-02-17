package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Side;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;
import org.usfirst.frc.team4947.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCenterTakeSwitch extends CommandGroup {
	
	// Constants.
	public static final String NAME = "AutoCenterTakeSwitch";

	// Members.
	private Pivot pivot;
	private Gripper gripper;
	
	public AutoCenterTakeSwitch( Pivot pivot, Gripper gripper) {
		super(NAME);

		this.pivot = pivot;
		this.gripper = gripper;
	}
	
	public void setSide(Side side) {
		if (side == Side.LEFT) {
			//TODO: voir s'il a peut-etre un chemin plus simple
			addSequential(new DriveDistance( 1.0));	// Drive to turn.
			addSequential(new DriveRotate(-25.0));   // Turn to the switch.
			addSequential(new DriveDistance( 10.0)); // Drive to the switch.
			addSequential(new DriveRotate(25.0));	// Turn to the switch for real.
//			addSequential(new PivotToSwitchPosition(pivot));	// Set pivot output to height of switch.
	//		addSequential(new GripperShootToSwitch(gripper));	// Throw cube.
			addSequential(new DriveDistance( -1.5));	// Get out of there to let other teams do their thing using the switch.
		} else if (side == Side.RIGHT) {
			addSequential(new DriveDistance( 1.0));	// Drive to turn.
			addSequential(new DriveRotate(25.0));    // Turn to the switch.
			addSequential(new DriveDistance( 10.0)); // Drive to the switch.
			addSequential(new DriveRotate(-25.0));	// Turn to the switch for real.
	//		addSequential(new PivotToSwitchPosition(pivot));	// Set pivot output to height of switch.
	//		addSequential(new GripperShootToSwitch(gripper));	// Throw cube.
			addSequential(new DriveDistance( -1.5));	// Get out of there to let other teams do their thing using the switch.
		} else {
			addSequential(new AutoCenterFoward());
		}
	}
}