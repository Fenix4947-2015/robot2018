package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.RobotMap;
import org.usfirst.frc.team4947.robot.commands.gripper.GripperDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gripper extends Subsystem {
	
	// Constants.
	private static final double MOTOR_PULL_PERCENT_OUTPUT = 0.5;
	private static final double MOTOR_REJECT_PERCENT_OUTPUT = -0.5;
	private static final double MOTOR_SHOOT_TO_SWITCH_PERCENT_OUTPUT = 0.5;
	
	private static final boolean OPENER_SOLENOID_CLOSE_STATE = false;
	private static final boolean OPENER_SOLENOID_OPEN_STATE = !OPENER_SOLENOID_CLOSE_STATE;
	
	private static final boolean STATE_CUBE_PRESENT = true;

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
		TalonSRX motor = new TalonSRX(RobotMap.GRIPPER_LEFT_MOTOR_DEVICE_NUMBER);
		return motor;
	}
	
	private static TalonSRX createRightMotor() {
		TalonSRX motor = new TalonSRX(RobotMap.GRIPPER_RIGHT_MOTOR_DEVICE_NUMBER);
		return motor;
	}
	
	private static Solenoid createOpenerSolenoid() {
		Solenoid solenoid = new Solenoid(RobotMap.GRIPPER_OPENER_SOLENOID_CHANNEL);
		return solenoid;
	}
	
	private static DigitalInput createCubePresenceDigitalInput() {
		DigitalInput digitalInput = new DigitalInput(RobotMap.GRIPPER_CUBE_PRESENCE_DIGITAL_INPUT_CHANNEL);
		return digitalInput;
	}

	public void initDefaultCommand() {
		//setDefaultCommand(new GripperDefault(this));
	}
	
	public boolean isCubePresent() {
		return (cubePresenceDigitalInput.get() == STATE_CUBE_PRESENT);
	}
	
	public void close() {
		openerSolenoid.set(OPENER_SOLENOID_CLOSE_STATE);
	}
	
	public void open() {
		openerSolenoid.set(OPENER_SOLENOID_OPEN_STATE);
	}
	
	public void pull() {
		rotate(MOTOR_PULL_PERCENT_OUTPUT);
	}
	
	public void reject() {
		rotate(MOTOR_REJECT_PERCENT_OUTPUT);
	}
	
	public void shootToSwitch() {
		rotate(MOTOR_SHOOT_TO_SWITCH_PERCENT_OUTPUT);
	}
	
	public void rotate(double percentOutput) {
		leftMotor.set(ControlMode.PercentOutput, percentOutput);
		rightMotor.set(ControlMode.PercentOutput, percentOutput);
	}

	public void stop() {
		leftMotor.set(ControlMode.PercentOutput, 0.0);
		rightMotor.set(ControlMode.PercentOutput, 0.0);
	}
}