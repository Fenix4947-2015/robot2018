package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Side;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperShootToSwitch;
import org.usfirst.frc.team4947.robot.commands.pivot.PivotToHighPosition;
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
			addSequential(new DriveDistance(DistanceAuto.DEXCHANGE));	
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveDistance((DistanceAuto.DPORTALTOPORTAL/2)+DistanceAuto.OFFSET)); // Turn right.
			addSequential(new DriveRotate(90.0)); 
			addSequential(new DriveDistance(DistanceAuto.DWALLTOSWITCH-DistanceAuto.DEXCHANGE));
			addSequential(new DriveRotate(90.0)); 
			addSequential(new DriveDistance(DistanceAuto.DTOSWITCH));
			addSequential(new PivotToHighPosition());
			addSequential(new GripperShootToSwitch(gripper));
		} else if (side == Side.RIGHT) {
			addSequential(new DriveDistance(DistanceAuto.DEXCHANGE));
			addSequential(new DriveRotate(90.0));	
			addSequential(new DriveDistance((DistanceAuto.DPORTALTOPORTAL/2)-DistanceAuto.OFFSET));
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveDistance(DistanceAuto.DWALLTOSWITCH-DistanceAuto.DEXCHANGE));
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveDistance(DistanceAuto.DTOSWITCH));
			addSequential(new PivotToHighPosition());
			addSequential(new GripperShootToSwitch(gripper));
		} else {
			addSequential(new AutoCenterFoward());
		}
	}
}