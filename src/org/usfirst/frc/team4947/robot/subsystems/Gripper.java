package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gripper extends Subsystem {
	
	private final TalonSRX gripperLeftMotor;
	private final TalonSRX gripperRightMotor;
	private final Solenoid openerSolenoid;

	public Gripper() {
		gripperLeftMotor = createGripperLeftMotor();
		gripperRightMotor = createGripperRightMotor();
		openerSolenoid = createOpenerSolenoid();
	}
	
	private static TalonSRX createGripperLeftMotor() {
		TalonSRX motor = new TalonSRX(RobotMap.GRIPPER_LEFT_MOTOR_DEVICE_NUMBER);
		return motor;
	}
	
	private static TalonSRX createGripperRightMotor() {
		TalonSRX motor = new TalonSRX(RobotMap.GRIPPER_RIGHT_MOTOR_DEVICE_NUMBER);
		return motor;
	}
	
	private static Solenoid createOpenerSolenoid() {
		Solenoid solenoid = new Solenoid(RobotMap.GRIPPER_OPENER_SOLENOID_CHANNEL);
		return solenoid;
	}

	public void initDefaultCommand() {
	}

	public TalonSRX getGripperLeftMotor() {
		return gripperLeftMotor;
	}

	public TalonSRX getGripperRightMotor() {
		return gripperRightMotor;
	}

	public Solenoid getOpenerSolenoid() {
		return openerSolenoid;
	}
}