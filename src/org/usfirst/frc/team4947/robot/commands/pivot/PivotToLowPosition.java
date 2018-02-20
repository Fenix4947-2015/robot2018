package org.usfirst.frc.team4947.robot.commands.pivot;

import org.usfirst.frc.team4947.robot.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PivotToLowPosition extends CommandGroup {

	public PivotToLowPosition() {
		addSequential(new PivotToVerticalFromHighPosition());
		addSequential(new WaitCommand(0.25));
		addSequential(new WaitForLowLimitSwitch());
	}
}