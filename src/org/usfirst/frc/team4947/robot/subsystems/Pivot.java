package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pivot extends Subsystem {
	
	// Constants.
	private static final double PERCENT_OUTPUT_MOTOR_TO_LOW = -0.5;
	private static final double PERCENT_OUTPUT_MOTOR_TO_HIGH = 0.5;
	private static final double PERCENT_OUTPUT_MOTOR_TO_EXCHANGE = 0.5;
	
	private static final int TIMEOUT_MS = 10;
	
	// Members.
	private final TalonSRX motor;
	
	private DigitalInput exchangeLimitSwitch;
	private Counter exchangeCounter;
	
	public Pivot() {
		motor = createMotor();
		
		initExchangeLimitSwitch();
	}
	
	private static TalonSRX createMotor() {
		TalonSRX motor = new TalonSRX(RobotMap.PIVOT_MOTOR_DEVICE_NUMBER);
		
		// Configure nominal and peak outputs.
		motor.configNominalOutputForward(0, TIMEOUT_MS);
		motor.configNominalOutputReverse(0, TIMEOUT_MS);
		motor.configPeakOutputForward(1.0, TIMEOUT_MS);
		motor.configPeakOutputReverse(-1.0, TIMEOUT_MS);
		
		// Configure limit switches.
		motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		motor.overrideLimitSwitchesEnable(true);
			
		return motor;
	}
	
	private void initExchangeLimitSwitch() {
		exchangeLimitSwitch = new DigitalInput(RobotMap.PIVOT_EXCHANGE_POSITION_DIGITAL_INPUT_CHANNEL);
		exchangeCounter = new Counter(exchangeLimitSwitch);
	}

	public void initDefaultCommand() {
	}
	
	public boolean isAtLowPos() {
		return motor.getSensorCollection().isRevLimitSwitchClosed();
	}
	
	public boolean isAtHighPos() {
		return motor.getSensorCollection().isFwdLimitSwitchClosed();
	}
	
	public boolean isAtExchangePos() {
		return (exchangeCounter.get() > 0);
	}
	
	public void moveToLowPos() {
		exchangeCounter.reset();
		motor.set(ControlMode.PercentOutput, PERCENT_OUTPUT_MOTOR_TO_LOW);
	}
	
	public void moveToHighPos() {
		exchangeCounter.reset();
		motor.set(ControlMode.PercentOutput, PERCENT_OUTPUT_MOTOR_TO_HIGH);
	}
	
	public void moveToExchangePos() {
		exchangeCounter.reset();
		
		double direction = 1.0;
		if (isAtHighPos()) {
			direction *= -1.0;
		}
		
		motor.set(ControlMode.PercentOutput, direction * PERCENT_OUTPUT_MOTOR_TO_EXCHANGE);
	}
	
	public void stop() {
		motor.set(ControlMode.PercentOutput, 0.0);
	}
}