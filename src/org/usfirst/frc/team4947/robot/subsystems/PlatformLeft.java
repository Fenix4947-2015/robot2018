package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PlatformLeft extends Subsystem {
	private TalonSRX LiftMotor = new TalonSRX(RobotMap.Lift_Motor_Address);
	private Solenoid UnlockerSolenoid = new Solenoid(RobotMap.Unlocker_Solenoid_Address);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void LiftSpeed(double Speed) 
    {    	
    	LiftMotor.set(ControlMode.PercentOutput, Speed);
    }

    public void UnlockPlatform(boolean unlock)
    {
    	if(unlock)
    	{
    		UnlockerSolenoid.set(true);
    	}
    	else
    	{
    		UnlockerSolenoid.set(false);
    	}
    }
}

