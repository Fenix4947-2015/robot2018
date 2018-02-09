package org.usfirst.frc.team4947.robot.subsystems;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

import org.usfirst.frc.team4947.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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
	
	// Members.
	private TalonSRX motor;
	private DigitalInput lowPosDigitalInput;
	private DigitalInput highPosDigitalInput;
	private DigitalInput exchangePosDigitalInput;
	private DigitalInput switchPosDigitalInput;

	private Map<Position, DigitalInput> digitalInputs;

	public Pivot() {
		motor = createMotor();
		lowPosDigitalInput = createLowPosDigitalInput();
		highPosDigitalInput = createHighPosDigitalInput();
		exchangePosDigitalInput = createExchangePosDigitalInput();
		switchPosDigitalInput = createSwitchPosDigitalInput();
	
		digitalInputs = createMapping();
	}
	
	private static TalonSRX createMotor() {
		TalonSRX motor = new TalonSRX(RobotMap.PIVOT_MOTOR_DEVICE_NUMBER);
		return motor;
	}
	
	private static DigitalInput createLowPosDigitalInput() {
		DigitalInput digitalInput = new DigitalInput(RobotMap.PIVOT_LOW_POSITION_DIGITAL_INPUT_CHANNEL);
		return digitalInput;
	}
	
	private static DigitalInput createHighPosDigitalInput() {
		DigitalInput digitalInput = new DigitalInput(RobotMap.PIVOT_HIGH_POSITION_DIGITAL_INPUT_CHANNEL);
		return digitalInput;
	}
	
	private static DigitalInput createExchangePosDigitalInput() {
		DigitalInput digitalInput = new DigitalInput(RobotMap.PIVOT_EXCHANGE_POSITION_DIGITAL_INPUT_CHANNEL);
		return digitalInput;
	}
	
	private static DigitalInput createSwitchPosDigitalInput() {
		DigitalInput digitalInput = new DigitalInput(RobotMap.PIVOT_SWITCH_POSITION_DIGITAL_INPUT_CHANNEL);
		return digitalInput;
	}
	
	private Map<Position, DigitalInput> createMapping() {
		EnumMap<Position, DigitalInput> mapping = new EnumMap<>(Position.class);
		mapping.put(Position.LOW, lowPosDigitalInput);
		mapping.put(Position.HIGH, highPosDigitalInput);
		mapping.put(Position.EXCHANGE, exchangePosDigitalInput);
		mapping.put(Position.SWITCH, switchPosDigitalInput);
		
		return mapping;
	}

	public void initDefaultCommand() {
	}
	
	public TalonSRX getMotor() {
		return motor;
	}

	public DigitalInput getLowPosDigitalInput() {
		return lowPosDigitalInput;
	}

	public DigitalInput getHighPosDigitalInput() {
		return highPosDigitalInput;
	}

	public DigitalInput getExchangePosDigitalInput() {
		return exchangePosDigitalInput;
	}

	public DigitalInput getSwitchPosDigitalInput() {
		return switchPosDigitalInput;
	}
	
	public Position getCurrentPosition() {
		for (Entry<Position, DigitalInput> entry : digitalInputs.entrySet()) {
			boolean enabled = entry.getValue().get();
			if (enabled) {
				return entry.getKey();
			}
		}
		
		return Position.UNKNOWN;
	}
	
	public double getDirectionTo(Position position) {
		int comparison = Integer.compare(getCurrentPosition().ordinal(), position.ordinal());
		if (comparison < 0) {
			return 1.0;
		}

		return -1.0;
	}	
}