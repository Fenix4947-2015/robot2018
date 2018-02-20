package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.Side;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperShootToSwitch;
import org.usfirst.frc.team4947.robot.commands.pivot.PivotToHighPosition;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;
import org.usfirst.frc.team4947.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCenterTakeSwitch extends CommandGroup {
	
	// Constants.
	public static final String NAME = "AutoCenterTakeSwitch";
	
	public AutoCenterTakeSwitch() {
		super(NAME);
	}
	
	public void setSide(Side side) {
		if (side == Side.LEFT) {
			addSequential(new DriveDistance(DistanceAuto.DIST_CLEAR_EXCHANGE_Y));	
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveDistance((DistanceAuto.DPORTALTOPORTAL_X/2)+DistanceAuto.OFFSET_FROM_CENTER_X)); // Turn right.
			addSequential(new DriveRotate(90.0)); 
			addSequential(new DriveDistance(DistanceAuto.DWALLTOSWITCH_Y-DistanceAuto.DIST_CLEAR_EXCHANGE_Y));
			addSequential(new DriveRotate(90.0)); 
			addSequential(new DriveFinalApproach(DistanceAuto.D_EXCHANGE_TO_SWITCH_X));			
			addSequential(new GripperShootToSwitch());
		} else if (side == Side.RIGHT) {
			addSequential(new DriveDistance(DistanceAuto.DIST_CLEAR_EXCHANGE_Y));
			addSequential(new DriveRotate(90.0));	
			addSequential(new DriveDistance((DistanceAuto.DPORTALTOPORTAL_X/2)-DistanceAuto.OFFSET_FROM_CENTER_X));
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveDistance(DistanceAuto.DWALLTOSWITCH_Y-DistanceAuto.DIST_CLEAR_EXCHANGE_Y));
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveFinalApproach(DistanceAuto.D_EXCHANGE_TO_SWITCH_X));
			addSequential(new GripperShootToSwitch());
		} else {
			addSequential(new AutoCenterFoward());
		}
	}
}