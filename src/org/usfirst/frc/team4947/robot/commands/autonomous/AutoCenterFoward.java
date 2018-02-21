package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.Side;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperClose;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperShootToSwitch;
import org.usfirst.frc.team4947.robot.commands.pivot.PivotToHighPosition;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;
import org.usfirst.frc.team4947.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCenterFoward extends CommandGroup {

	// Constants.
	public static final String NAME = "AutoCenterFoward";
	private Gripper gripper;
	
	public AutoCenterFoward() {
		super(NAME);
		requires(Robot.gripper);
		this.gripper = gripper;

	}
	public void setSide(Side side) {
		if (side == Side.LEFT) {
			addParallel(new GripperClose());
			addSequential(new DriveDistance(DistanceAutoConstants.DIST_CLEAR_EXCHANGE_Y));
			addSequential(new DriveRotate(90.0));	
			addSequential(new DriveDistance((DistanceAutoConstants.DPORTALTOPORTAL_X/2.0)-DistanceAutoConstants.OFFSET_FROM_CENTER_X));
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveDistance(DistanceAutoConstants.DWALLTOSWITCH_Y-DistanceAutoConstants.DIST_CLEAR_EXCHANGE_Y)); 
			
		} else if (side == Side.RIGHT) {
			addParallel(new GripperClose());
			addSequential(new DriveDistance(DistanceAutoConstants.DIST_CLEAR_EXCHANGE_Y));	
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveDistance((DistanceAutoConstants.DPORTALTOPORTAL_X/2.0)+DistanceAutoConstants.OFFSET_FROM_CENTER_X)); // Turn right.
			addSequential(new DriveRotate(90.0)); 
			addSequential(new DriveDistance(DistanceAutoConstants.DWALLTOSWITCH_Y-DistanceAutoConstants.DIST_CLEAR_EXCHANGE_Y));
		}
	}
}