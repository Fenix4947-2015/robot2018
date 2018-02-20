package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Side;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperShootToSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftTakeSwitch extends CommandGroup {
	
	// Constants.
	public static final String NAME = "AutoLeftTakeSwitch";
	
	public AutoLeftTakeSwitch(Side side) {
		super(NAME);		
		
		if (side == Side.LEFT) {
			addSequential(new DriveDistance(DistanceAutoConstants.DWALLTOSWITCH_Y - DistanceAutoConstants.ROBOT_LENGTH_FORWARD_DIRECTION/2.0));
			addSequential(new DriveRotate(90.0));
			addSequential(new DriveFinalApproach(DistanceAutoConstants.D_EXCHANGE_TO_SWITCH_X));			
			addSequential(new GripperShootToSwitch());
		} else if (side == Side.RIGHT) {
			addSequential(new DriveDistance(DistanceAutoConstants.DIST_CLEAR_EXCHANGE_Y));
			addSequential(new DriveRotate(90.0));	
			addSequential(new DriveDistance(DistanceAutoConstants.DPORTALTOPORTAL_X));
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveDistance(DistanceAutoConstants.DWALLTOSWITCH_Y-DistanceAutoConstants.DIST_CLEAR_EXCHANGE_Y - DistanceAutoConstants.ROBOT_LENGTH_FORWARD_DIRECTION/2.0));
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveFinalApproach(DistanceAutoConstants.D_EXCHANGE_TO_SWITCH_X));			
			addSequential(new GripperShootToSwitch());
		} 
		else {
			addSequential(new AutoLeftRightFoward());
		}
	}
}