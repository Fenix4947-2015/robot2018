package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pivot extends Subsystem {
	
	// Negatif monte
	// Pos descend
	
	// Constants.
	private static final double PERCENT_OUTPUT_MOTOR_TO_LOW = 0.5;
	private static final double PERCENT_OUTPUT_MOTOR_TO_HIGH = -0.5;
	
	// Members.
	private final WPI_TalonSRX motor;
	
	private DigitalInput verticalLimitSwitch;
	private Counter verticalCounter;
	
	private int count = 0;
	
	public Pivot() {
		motor = createMotor();
		
		initVerticalLimitSwitch();
	}
	
	private static WPI_TalonSRX	createMotor() {
		WPI_TalonSRX motor = new WPI_TalonSRX(RobotMap.PIVOT_MOTOR_DEVICE_NUMBER);
		motor.setInverted(false);
		
		motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		motor.overrideLimitSwitchesEnable(true);
			
		return motor;
	}
	
	private void initVerticalLimitSwitch() {
		verticalLimitSwitch = new DigitalInput(RobotMap.PIVOT_VERTICAL_LIMIT_SWITCH);
		verticalCounter = new Counter(verticalLimitSwitch);
	}

	public void initDefaultCommand() {
	}
	
	public boolean isAtLowPos() {
		return motor.getSensorCollection().isFwdLimitSwitchClosed();
	}
	
	public boolean isAtHighPos() {
		return motor.getSensorCollection().isRevLimitSwitchClosed();
	}
	
	public boolean hasPassedVertical() {
		return (verticalCounter.get() > 0);
	}
	
	public void activeBrakeWhenGoingHigh() {
		motor.set(ControlMode.PercentOutput, 0.1);
	}
	
	public void activeBrakeWhenGoingLow() {
		motor.set(ControlMode.PercentOutput, -0.1);
	}	
	
	public void moveToLowPos() {
		verticalCounter.reset();
		motor.set(ControlMode.PercentOutput, PERCENT_OUTPUT_MOTOR_TO_LOW);
	}
	
	public void moveToHighPos() {
		verticalCounter.reset();
		motor.set(ControlMode.PercentOutput, PERCENT_OUTPUT_MOTOR_TO_HIGH);
	}
	
	public void moveCustomSpeed(double percent) {
		motor.set(ControlMode.PercentOutput, percent);
	}
	
	public void stop() {
		motor.set(ControlMode.PercentOutput, 0.0);
	}
	
	public void log() {
		++count;
		
		if ((count % 10) == 0) {
//			boolean reverseLimitSwitch = motor.getSensorCollection().isRevLimitSwitchClosed();
//			boolean forwardLimitSwitch = motor.getSensorCollection().isFwdLimitSwitchClosed();
//			boolean verticalLimitSwitchValue = verticalLimitSwitch.get();
//			
//			System.out.println("reverseLimitSwitch=" + reverseLimitSwitch 
//					+ ", forwardLimitSwitch=" + forwardLimitSwitch
//					+ ", verticalLimitSwitchValue=" + verticalLimitSwitchValue);
		}
	}
}