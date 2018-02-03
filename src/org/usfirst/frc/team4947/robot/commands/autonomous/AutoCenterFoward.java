package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Side;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperShootSwitch;
import org.usfirst.frc.team4947.robot.commands.pivot.PivotToSwitchPosition;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;
import org.usfirst.frc.team4947.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCenterFoward extends CommandGroup {
	
	// Constants.
	public static final String NAME = "AutoCenterFoward";

	
	// Members.
	private final DriveTrain driveTrain;
	private final Pivot pivot;
	private final Gripper gripper;
	
	public AutoCenterFoward(DriveTrain driveTrain, Pivot pivot, Gripper gripper) {
		super(NAME);

		this.driveTrain = driveTrain;
		this.pivot = pivot;
		this.gripper = gripper;

		addSequential(new DriveRotate(35.0));	
		addSequential(new DriveDistance(driveTrain, 16.0));	// Drive to nothing ;-)
	}
	
	
	}
