package org.usfirst.frc.team4947.robot.subsystems;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

import org.usfirst.frc.team4947.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pivot extends Subsystem {
	
	public static enum Position {
		LOW,
		EXCHANGE,
		SWITCH,
		HIGH,
		UNKNOWN,
	}
	
	// Constants.
	private static final double MOTOR_PERCENT_OUTPUT = 0.5;
	private static final boolean STATE_POSITION_REACHED = true;
	
	// Members.
	private TalonSRX motor;
	
	private DigitalInput lowPosDigitalInput;
	private Counter lowPosCounter;
	
	private DigitalInput exchangePosDigitalInput;
	private Counter exchangePosCounter;
	
	private DigitalInput switchPosDigitalInput;
	private Counter switchPosCounter;
	
	private DigitalInput highPosDigitalInput;
	private Counter highPosCounter;
	
	private Map<Position, DigitalInput> digitalInputs;
	private Map<Position, Counter> counters;
	
	public Pivot() {
		motor = createMotor();

		initLowPosition();
		initExchangePosition();
		initSwitchPosition();
		initHighPosition();

		initMappings();
	}
	
	private static TalonSRX createMotor() {
		TalonSRX motor = new TalonSRX(RobotMap.PIVOT_MOTOR_DEVICE_NUMBER);
		return motor;
	}
	
	private void initLowPosition() {
		lowPosDigitalInput = new DigitalInput(RobotMap.PIVOT_LOW_POSITION_DIGITAL_INPUT_CHANNEL);
		lowPosCounter = new Counter(lowPosDigitalInput);
	}
	
	private void initExchangePosition() {
		exchangePosDigitalInput = new DigitalInput(RobotMap.PIVOT_EXCHANGE_POSITION_DIGITAL_INPUT_CHANNEL);
		exchangePosCounter = new Counter(exchangePosDigitalInput);
	}
	
	private void initSwitchPosition() {
		switchPosDigitalInput = new DigitalInput(RobotMap.PIVOT_SWITCH_POSITION_DIGITAL_INPUT_CHANNEL);
		switchPosCounter = new Counter(switchPosDigitalInput);
	}

	private void initHighPosition() {
		highPosDigitalInput = new DigitalInput(RobotMap.PIVOT_HIGH_POSITION_DIGITAL_INPUT_CHANNEL);
		highPosCounter = new Counter(highPosDigitalInput);
	}
	
	private void initMappings() {
		digitalInputs = new EnumMap<>(Position.class);
		digitalInputs.put(Position.LOW, lowPosDigitalInput);
		digitalInputs.put(Position.HIGH, highPosDigitalInput);
		digitalInputs.put(Position.EXCHANGE, exchangePosDigitalInput);
		digitalInputs.put(Position.SWITCH, switchPosDigitalInput);		
		
		counters = new EnumMap<>(Position.class);
		counters.put(Position.LOW, lowPosCounter);
		counters.put(Position.HIGH, highPosCounter);
		counters.put(Position.EXCHANGE, exchangePosCounter);
		counters.put(Position.SWITCH, switchPosCounter);
	}

	public void initDefaultCommand() {
	}
	
	private Position getCurrentPosition() {
		for (Entry<Position, DigitalInput> entry : digitalInputs.entrySet()) {
			Position pos = entry.getKey();
			DigitalInput digitalInput = entry.getValue();

			boolean isSet = (digitalInput.get() == STATE_POSITION_REACHED);
			if (isSet) {
				return pos;
			}
		}
		
		return Position.UNKNOWN;
	}
	
	public void moveTo(Position pos) {
		resetCounter(pos);

		if (pos == Position.LOW) {
			motor.set(ControlMode.PercentOutput, -MOTOR_PERCENT_OUTPUT);
			return;
		} else if (pos == Position.HIGH) {
			motor.set(ControlMode.PercentOutput, MOTOR_PERCENT_OUTPUT);
			return;
		}

		Position currentPos = getCurrentPosition();
		if (pos == currentPos) {
			return;
		}

		double direction = getDirection(currentPos, pos);
		motor.set(ControlMode.PercentOutput, (direction * MOTOR_PERCENT_OUTPUT));
	}
	
	private void resetCounter(Position pos) {
		Counter counter = counters.get(pos);
		counter.reset();
	}
	
	// Returns -1 if new position is lower then current position.
	// Return 0 if boths positions are the same.
	// Returns 1 if new position is higher then current position.
	private double getDirection(Position from, Position to) {
		return Math.signum(Integer.compare(to.ordinal(), from.ordinal()));
	}
	
	public boolean isReached(Position pos) {
		Counter counter = counters.get(pos);
		return (counter.get() > 0);
	}
	
	public void stop() {
		motor.set(ControlMode.PercentOutput, 0.0);
	}
}