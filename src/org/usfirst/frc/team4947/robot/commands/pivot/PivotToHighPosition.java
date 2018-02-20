package org.usfirst.frc.team4947.robot.commands.pivot;

import org.usfirst.frc.team4947.robot.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PivotToHighPosition extends CommandGroup {

	public PivotToHighPosition() {
		addSequential(new PivotToVerticalFromLowPosition());
		addSequential(new WaitCommand(0.25));
		addSequential(new WaitForHighLimitSwitch());
	}
}