/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4947.robot;

import org.usfirst.frc.team4947.robot.commands.ShiftDown;
import org.usfirst.frc.team4947.robot.commands.ShiftUp;
import org.usfirst.frc.team4947.robot.commands.joystick.ActivateEndGameHelperProfile;
import org.usfirst.frc.team4947.robot.commands.pivot.PivotToExchangePosition;
import org.usfirst.frc.team4947.robot.commands.pivot.PivotToHighPosition;
import org.usfirst.frc.team4947.robot.commands.pivot.PivotToLowPosition;
import org.usfirst.frc.team4947.robot.commands.pivot.PivotToSwitchPosition;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups 
 * that allow control of the robot.
 */
public class OI {

	public static enum XBoxAxis {
		LEFT_STICK_X(0), 
		LEFT_STICK_Y(1), 
		LEFT_TRIGGER(2), 
		RIGHT_TRIGGER(3), 
		RIGHT_STICK_X(4), 
		RIGHT_STICK_Y(5);

		private int value;

		private XBoxAxis(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public static enum XBoxButton {
		A(1), 
		B(2), 
		X(3), 
		Y(4), 
		LB(5), 
		RB(6), 
		BACK(7), 
		START(8),
		LEFT_STICK(9), 
		RIGHT_STICK(10);

		private int value;

		private XBoxButton(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	// Members.
	private Joystick joystickDriver;
	private Joystick joystickHelper;

	public OI() {
		initJoystickDriver();
		initJoystickHelper();

		// TODO Add buttons to smart dashboard
		// example :SmartDashboard.putData("RobotLift", new RobotLift());
	}
	
	private void initJoystickDriver() {
		joystickDriver = new Joystick(RobotMap.JOYSTICK_DRIVER_PORT);
		
		// Create all buttons in case we need them.
		JoystickButton driverA = new JoystickButton(joystickDriver, XBoxButton.A.getValue());
		JoystickButton driverB = new JoystickButton(joystickDriver, XBoxButton.B.getValue());
		JoystickButton driverX = new JoystickButton(joystickDriver, XBoxButton.X.getValue());
		JoystickButton driverY = new JoystickButton(joystickDriver, XBoxButton.Y.getValue());
		JoystickButton driverLB = new JoystickButton(joystickDriver, XBoxButton.LB.getValue());
		JoystickButton driverRB = new JoystickButton(joystickDriver, XBoxButton.RB.getValue());
		JoystickButton driverBack = new JoystickButton(joystickDriver, XBoxButton.BACK.getValue());
		JoystickButton driverStart = new JoystickButton(joystickDriver, XBoxButton.START.getValue());
		JoystickButton driverLeftStick = new JoystickButton(joystickDriver, XBoxButton.LEFT_STICK.getValue());
		JoystickButton driverRightStick = new JoystickButton(joystickDriver, XBoxButton.RIGHT_STICK.getValue());		
		
		// TODO Link button state to execute commands
		// example : driverA.whenPressed(new RobotPickGear());
		
		driverX.whenPressed(new ShiftUp());
		driverA.whenPressed(new ShiftDown());
	
	}
	
	private void initJoystickHelper() {
		joystickHelper = new Joystick(RobotMap.JOYSTICK_HELPER_PORT);

		// Create all buttons in case we need them.
		JoystickButton helperA = new JoystickButton(joystickHelper, XBoxButton.A.getValue());
		JoystickButton helperB = new JoystickButton(joystickHelper, XBoxButton.B.getValue());
		JoystickButton helperX = new JoystickButton(joystickHelper, XBoxButton.X.getValue());
		JoystickButton helperY = new JoystickButton(joystickHelper, XBoxButton.Y.getValue());
		JoystickButton helperLB = new JoystickButton(joystickHelper, XBoxButton.LB.getValue());
		JoystickButton helperRB = new JoystickButton(joystickHelper, XBoxButton.RB.getValue());
		JoystickButton helperBack = new JoystickButton(joystickHelper, XBoxButton.BACK.getValue());
		JoystickButton helperStart = new JoystickButton(joystickHelper, XBoxButton.START.getValue());
		JoystickButton helperLeftStick = new JoystickButton(joystickHelper, XBoxButton.LEFT_STICK.getValue());
		JoystickButton helperRightStick = new JoystickButton(joystickHelper, XBoxButton.RIGHT_STICK.getValue());
		
		helperA.whenPressed(new PivotToLowPosition());
		helperB.whenPressed(new PivotToExchangePosition());
		helperX.whenPressed(new PivotToSwitchPosition());
		helperY.whenPressed(new PivotToHighPosition());
		
		helperBack.whenPressed(new ActivateEndGameHelperProfile());
		}

	public double getJoystickDriverAxis(XBoxAxis axis) {
		return joystickDriver.getRawAxis(axis.getValue());
	}

	public double getJoystickDriverAxis(XBoxAxis axis, double deadBand) {
		return getAxisWithDeadBand(joystickDriver, axis, deadBand);
	}

	public boolean getJoystickDriverButton(XBoxButton button) {
		return joystickDriver.getRawButton(button.getValue());
	}

	public double getJoystickHelperAxis(XBoxAxis axis) {
		return joystickHelper.getRawAxis(axis.getValue());
	}

	public double getJoystickHelperAxis(XBoxAxis axis, double deadBand) {
		return getAxisWithDeadBand(joystickHelper, axis, deadBand);
	}

	public boolean getJoystickHelperButton(XBoxButton button) {
		return joystickHelper.getRawButton(button.getValue());
	}

	public void setJoystickDriverRumble(RumbleType rumbleType, float value) {
		joystickDriver.setRumble(rumbleType, value);
	}
	
	private static double getAxisWithDeadBand(Joystick joystick, XBoxAxis axis, double deadBand) {
		double axisValue = joystick.getRawAxis(axis.getValue());
		if (Math.abs(axisValue) <= deadBand) {
			axisValue = 0;
		}

		return axisValue;
	}
}