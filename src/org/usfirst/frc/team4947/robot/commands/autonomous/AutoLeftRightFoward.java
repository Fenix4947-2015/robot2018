package org.usfirst.frc.team4947.robot.commands.autonomous;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.commands.DriveArcade;
import org.usfirst.frc.team4947.robot.commands.DriveTime;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftRightFoward extends CommandGroup {

	// Constants.
	public static final String NAME = "AutoLeftRightFoward";

	public AutoLeftRightFoward() {
		super(NAME);

		//addSequential(new Dr0iveDistance(16.0)); // Drive to nothing ;-)7
		addSequential(new DriveTime(0.5));
	}
}