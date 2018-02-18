package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.Side;
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
			addSequential(new DriveDistance(DistanceAuto.DEXCHANGE));
			addSequential(new DriveRotate(90.0));	
			addSequential(new DriveDistance((DistanceAuto.DPORTALTOPORTAL/2)-DistanceAuto.OFFSET));
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveDistance(DistanceAuto.DWALLTOSWITCH-DistanceAuto.DEXCHANGE)); 
			
		} else if (side == Side.RIGHT) {
			addSequential(new DriveDistance(DistanceAuto.DEXCHANGE));	
			addSequential(new DriveRotate(-90.0));
			addSequential(new DriveDistance((DistanceAuto.DPORTALTOPORTAL/2)+DistanceAuto.OFFSET)); // Turn right.
			addSequential(new DriveRotate(90.0)); 
			addSequential(new DriveDistance(DistanceAuto.DWALLTOSWITCH-DistanceAuto.DEXCHANGE));
		}
	}
}