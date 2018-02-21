package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Platform extends Subsystem {

	// Members.
	private WPI_TalonSRX liftMotor;
	private Solenoid unlockerSolenoid;
	private static final boolean STATE_UNLOCKED = false;

	
	public Platform() {
		liftMotor = new WPI_TalonSRX(RobotMap.LIFT_MOTOR_ADDRESS);
		liftMotor.setInverted(false);
		unlockerSolenoid = new Solenoid(RobotMap.UNLOCKER_SOLENOID_ADDRESS);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new ActivateEndGameProfile(this, this));		
	}

	public void liftSpeed(double speed) {
		liftMotor.set(ControlMode.PercentOutput, speed);
	}

	public void unlockPlatform(boolean unlock) {
		if(unlock)
		{
			unlockerSolenoid.set(STATE_UNLOCKED);
		}
		else
		{
			unlockerSolenoid.set(!STATE_UNLOCKED);
		}		
	}
	
	public void liftStop()
	{
		liftMotor.stopMotor();
	}
}