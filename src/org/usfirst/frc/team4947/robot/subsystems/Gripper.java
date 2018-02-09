package org.usfirst.frc.team4947.robot.subsystems;

import static org.usfirst.frc.team4947.robot.RobotMap.GRIPPER_CUBE_PRESENCE_DIGITAL_INPUT_CHANNEL;
import static org.usfirst.frc.team4947.robot.RobotMap.GRIPPER_LEFT_MOTOR_DEVICE_NUMBER;
import static org.usfirst.frc.team4947.robot.RobotMap.GRIPPER_OPENER_SOLENOID_CHANNEL;
import static org.usfirst.frc.team4947.robot.RobotMap.GRIPPER_RIGHT_MOTOR_DEVICE_NUMBER;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gripper extends Subsystem {
	
	// Members.
	private TalonSRX leftMotor;
	private TalonSRX rightMotor;
	private Solenoid openerSolenoid;
	private DigitalInput cubePresenceDigitalInput;

	public Gripper() {
		leftMotor = createLeftMotor();
		rightMotor = createRightMotor();
		openerSolenoid = createOpenerSolenoid();
		cubePresenceDigitalInput = createCubePresenceDigitalInput();
	}
	
	private static TalonSRX createLeftMotor() {
		TalonSRX motor = new TalonSRX(GRIPPER_LEFT_MOTOR_DEVICE_NUMBER);
		return motor;
	}
	
	private static TalonSRX createRightMotor() {
		TalonSRX motor = new TalonSRX(GRIPPER_RIGHT_MOTOR_DEVICE_NUMBER);
		return motor;
	}
	
	private static Solenoid createOpenerSolenoid() {
		Solenoid solenoid = new Solenoid(GRIPPER_OPENER_SOLENOID_CHANNEL);
		return solenoid;
	}
	
	private static DigitalInput createCubePresenceDigitalInput() {
		DigitalInput digitalInput = new DigitalInput(GRIPPER_CUBE_PRESENCE_DIGITAL_INPUT_CHANNEL);
		return digitalInput;
	}

	public void initDefaultCommand() {
	}

	public TalonSRX getLeftMotor() {
		return leftMotor;
	}

	public TalonSRX getRightMotor() {
		return rightMotor;
	}

	public Solenoid getOpenerSolenoid() {
		return openerSolenoid;
	}

	public DigitalInput getCubePresenceDigitalInput() {
		return cubePresenceDigitalInput;
	}
}