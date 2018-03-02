package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Side;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperClose;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperShootToSwitch;


import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoRightTakeSwitch extends CommandGroup {
	
	// Constants.
	public static final String NAME = "AutoRightTakeSwitch";

	public AutoRightTakeSwitch(Side side, double waitBeforeAutonomous) {
		super(NAME);
		addSequential(new WaitCommand(waitBeforeAutonomous));
		
		if (side == Side.LEFT) {
			// mecanique de crossover de la switch c�t� pilote
			/*
			addParallel(new GripperClose());
			addSequential(new DriveDistance(DistanceAutoConstants.DIST_CLEAR_EXCHANGE_Y));
			addSequential(new DriveRotate(-90.0));	
			addSequential(new DriveDistance(DistanceAutoConstants.DPORTALTOPORTAL_X - DistanceAutoConstants.ROBOT_LENGTH_FORWARD_DIRECTION));
			addSequential(new DriveRotate(90.0));
			addSequential(new DriveDistance(DistanceAutoConstants.DWALLTOSWITCH_Y+ DistanceAutoConstants.D_SWITCH_THICKNESS_Y/2.0 - DistanceAutoConstants.DIST_CLEAR_EXCHANGE_Y - DistanceAutoConstants.ROBOT_LENGTH_FORWARD_DIRECTION/2.0));
			addSequential(new DriveRotate(90.0));
			addSequential(new DriveFinalApproach(DistanceAutoConstants.D_EXCHANGE_TO_SWITCH_X));			
			addSequential(new GripperShootToSwitch());*/
			
			// m�canique de crossover de la switch c�t� balance
			addParallel(new GripperClose());
			addSequential(new DriveDistance(DistanceAutoConstants.DWALLTOSWITCH_Y + DistanceAutoConstants.DIST_CLEAR_SWITCH_Y));
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveDistance(DistanceAutoConstants.DPORTALTOPORTAL_X - DistanceAutoConstants.ROBOT_LENGTH_FORWARD_DIRECTION - DistanceAutoConstants.D_EXCHANGE_TO_SWITCH_X - DistanceAutoConstants.D_SWITCH_PLATEAU_X));
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveFinalApproach(DistanceAutoConstants.DIST_CLEAR_SWITCH_Y - DistanceAutoConstants.D_SWITCH_THICKNESS_Y/2.0)); // todo calculate this value
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