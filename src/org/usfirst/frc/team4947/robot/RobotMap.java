/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4947.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	// Branchements du driveTrain
	public static final int LEFT_MOTOR1_ADDRESS =11;	
	public static final int LEFT_MOTOR2_ADDRESS =13;
	
	public static final int RIGHT_MOTOR1_ADDRESS =17;
	public static final int RIGHT_MOTOR2_ADDRESS =18;
	
	public static final int GEARBOX_SPEEDSOLENOID_ADDRESS = 7;
	public static final int ANALOG_GYRO_ADDRESS = 0;
	
	// Gripper subsystem.
	public static final int GRIPPER_LEFT_MOTOR_DEVICE_NUMBER = -1;
	public static final int GRIPPER_RIGHT_MOTOR_DEVICE_NUMBER = -1;
	public static final int GRIPPER_OPENER_SOLENOID_CHANNEL = -1;
}
