package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftRightFoward extends CommandGroup {

	// Constants.
	public static final String NAME = "AutoLeftFoward";

	public AutoLeftRightFoward(DriveTrain driveTrain) {
		super(NAME);

		addSequential(new DriveDistance(driveTrain, 16.0)); // Drive to nothing ;-)
	}
}