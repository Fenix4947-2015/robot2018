/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4947.robot;

import org.usfirst.frc.team4947.robot.commands.AutoDefault;
import org.usfirst.frc.team4947.robot.commands.autonomous.AutoLeftTakeSwitch;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;
import org.usfirst.frc.team4947.robot.subsystems.Pivot;
import org.usfirst.frc.team4947.robot.subsystems.Platform;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveTrain driveTrain = new DriveTrain();
	public static OI oi;
	public static final Platform platformLeft = new Platform(RobotMap.LIFT_MOTOR_ADDRESS_LEFT ,RobotMap.UNLOCKER_SOLENOID_ADDRESS_LEFT);	
	public static final Platform platformRight = new Platform(RobotMap.LIFT_MOTOR_ADDRESS_RIGHT ,RobotMap.UNLOCKER_SOLENOID_ADDRESS_RIGHT);	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	private Gripper gripper;
	private Pivot pivot;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		gripper = new Gripper();
		pivot = new Pivot();
		
		oi = new OI(gripper, pivot);
		m_chooser.addDefault("Default Auto", new AutoDefault());
		m_chooser.addObject("Robot a gauche - switch", new AutoLeftTakeSwitch(driveTrain, pivot, gripper));
		// todo : ajouter les autres modes autonomes 
		SmartDashboard.putData("Auto mode", m_chooser);
		
		// Camera sur le dashboard
		CameraServer.getInstance().startAutomaticCapture();
		
		 
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			String gameData = DriverStation.getInstance().getGameSpecificMessage();
			if (gameData != null) {
				planAutonomous(gameData);
			}
		}
	}
	
	private void planAutonomous(String gameData) {
		String commandName = m_autonomousCommand.getName();
		switch (commandName) {
			case "DriveAutoLine":
				break;

			case AutoLeftTakeSwitch.NAME:
				Side sideOfSwitch = Side.ofSwitch(gameData);

				AutoLeftTakeSwitch autoLeftTakeSwitch = ((AutoLeftTakeSwitch) m_autonomousCommand);
				autoLeftTakeSwitch.setSide(sideOfSwitch);
				autoLeftTakeSwitch.start();
				break;

			default:
				System.out.format("Command not supported (command=%s).%n", commandName);
				break;
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		log();
	}
	

	private void log() 
	{
		/*driveTrain.log();
		intake.log();
		gripper.log();		
		visionSystem.log();*/
	}
}
