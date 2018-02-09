package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCenterFoward extends CommandGroup {

	// Constants.
	public static final String NAME = "AutoCenterFoward";

	public AutoCenterFoward(DriveTrain driveTrain) {
		super(NAME);

		addSequential(new DriveDistance(driveTrain, 0.5));
		addSequential(new DriveRotate(driveTrain, 35.0));
		addSequential(new DriveDistance(driveTrain, 16.0)); // Drive to nothing ;-)
	}
}