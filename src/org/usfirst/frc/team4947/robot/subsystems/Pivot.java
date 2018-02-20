package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pivot extends Subsystem {
	
	// Negatif monte
	// Pos descend
	
	// Constants.
	private static final double PERCENT_OUTPUT_MOTOR_TO_LOW = 0.5;
	private static final double PERCENT_OUTPUT_MOTOR_TO_HIGH = -0.5;
	private static final double ACTIVE_BRAKE_WHEN_GOING_HIGH = 0.1;
	private static final double ACTIVE_BRAKE_WHEN_GOING_LOW = -0.1;
	
	// Members.
	private final WPI_TalonSRX motor;
	
	private DigitalInput verticalLimitSwitch;
	
	private int count = 0;
	
	public Pivot() {
		motor = createMotor();
		verticalLimitSwitch = createVerticalLimitSwitch();
	}
	
	private static WPI_TalonSRX	createMotor() {
		WPI_TalonSRX motor = new WPI_TalonSRX(RobotMap.PIVOT_MOTOR_DEVICE_NUMBER);
		motor.setInverted(false);
		
		motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		motor.overrideLimitSwitchesEnable(true);
			
		return motor;
	}
	
	private DigitalInput createVerticalLimitSwitch() {
		return new DigitalInput(RobotMap.PIVOT_VERTICAL_LIMIT_SWITCH);
	}

	public void initDefaultCommand() {
	}
	
	public boolean isAtLowPos() {
		return motor.getSensorCollection().isFwdLimitSwitchClosed();
	}
	
	public boolean isAtHighPos() {
		return motor.getSensorCollection().isRevLimitSwitchClosed();
	}
	
	public boolean isAtVerticalPos() {
		return verticalLimitSwitch.get();
	}
	
	public void activeBrakeWhenGoingHigh() {
		motor.set(ControlMode.PercentOutput, ACTIVE_BRAKE_WHEN_GOING_HIGH);
	}
	
	public void activeBrakeWhenGoingLow() {
		motor.set(ControlMode.PercentOutput, ACTIVE_BRAKE_WHEN_GOING_LOW);
	}	
	
	public void moveToLowPos() {
		motor.set(ControlMode.PercentOutput, PERCENT_OUTPUT_MOTOR_TO_LOW);
	}
	
	public void moveToHighPos() {
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