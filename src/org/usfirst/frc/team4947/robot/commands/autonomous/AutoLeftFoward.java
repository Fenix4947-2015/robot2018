package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Side;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperShootSwitch;
import org.usfirst.frc.team4947.robot.commands.pivot.PivotToSwitchPosition;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;
import org.usfirst.frc.team4947.robot.subsystems.Pivot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftFoward extends CommandGroup {
	
	// Constants.
	public static final String NAME = "AutoLeftFoward";

	// Members.
	private final DriveTrain driveTrain;
	private final Pivot pivot;
	private final Gripper gripper;
	
	public AutoLeftFoward(DriveTrain driveTrain, Pivot pivot, Gripper gripper) {
		super(NAME);

		this.driveTrain = driveTrain;
		this.pivot = pivot;
		this.gripper=gripper;

		addSequential(new DriveDistance(driveTrain, 16.0));	// Drive to nothing ;-)
	}
	}
