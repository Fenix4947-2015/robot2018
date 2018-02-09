package org.usfirst.frc.team4947.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Platform extends Subsystem {

	// Members.
	private TalonSRX liftMotor;
	private Solenoid unlockerSolenoid;

	public Platform(int talonDeviceNumber, int solenoidChannel) {
		liftMotor = new TalonSRX(talonDeviceNumber);
		unlockerSolenoid = new Solenoid(solenoidChannel);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void liftSpeed(double speed) {
		liftMotor.set(ControlMode.PercentOutput, speed);
	}

	public void unlockPlatform(boolean unlock) {
		unlockerSolenoid.set(unlock);
	}
}