package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Side;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperClose;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperShootToSwitch;
import org.usfirst.frc.team4947.robot.commands.pivot.PivotToHighPosition;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;
import org.usfirst.frc.team4947.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightTakeSwitch extends CommandGroup {
	
	// Constants.
	public static final String NAME = "AutoRightTakeSwitch";

	public AutoRightTakeSwitch(Side side) {
		super(NAME);
		
		if (side == Side.LEFT) {
			addParallel(new GripperClose());
			addSequential(new DriveDistance(DistanceAutoConstants.DIST_CLEAR_EXCHANGE_Y));
			addSequential(new DriveRotate(-90.0));	
			addSequential(new DriveDistance(DistanceAutoConstants.DPORTALTOPORTAL_X - DistanceAutoConstants.ROBOT_LENGTH_FORWARD_DIRECTION));
			addSequential(new DriveRotate(90.0));
			addSequential(new DriveDistance(DistanceAutoConstants.DWALLTOSWITCH_Y+ DistanceAutoConstants.D_SWITCH_THICKNESS_Y/2.0 - DistanceAutoConstants.DIST_CLEAR_EXCHANGE_Y - DistanceAutoConstants.ROBOT_LENGTH_FORWARD_DIRECTION/2.0));
			addSequential(new DriveRotate(90.0));
			addSequential(new DriveFinalApproach(DistanceAutoConstants.D_EXCHANGE_TO_SWITCH_X));			
			addSequential(new GripperShootToSwitch());
		} else if (side == Side.RIGHT) {
			addParallel(new GripperClose());
			addSequential(new DriveDistance(DistanceAutoConstants.DWALLTOSWITCH_Y + DistanceAutoConstants.D_SWITCH_THICKNESS_Y/2.0 - DistanceAutoConstants.ROBOT_LENGTH_FORWARD_DIRECTION/2.0));
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveFinalApproach(DistanceAutoConstants.D_EXCHANGE_TO_SWITCH_X));
			addSequential(new GripperShootToSwitch());
		} else {
			addSequential(new AutoLeftRightFoward());	// Drive to switch.
		}
	}
}