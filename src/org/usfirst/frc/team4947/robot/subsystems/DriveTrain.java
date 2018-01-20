package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.commands.DriveArcade;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	private WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(11); // encoder
	private WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(13);
	private SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMotor1, leftMotor2);
	
	private WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(12); // encoder
	private WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(14);
	private SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMotor1, rightMotor2);
	
	double targetPositionRotations;
	
	public Solenoid gearboxSpeedSolenoid = new Solenoid(7);

	public AnalogGyro gyro = new AnalogGyro(0);
	private static final double kVoltsPerDegreePerSecond = 0.0128/2; // gyro sensitivity, estimated 2017, jp choiniere

	
	private DifferentialDrive robotDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
	
	public PowerDistributionPanel powerDistPanel = new PowerDistributionPanel(1);
	
	public DriveTrain()
	{
		gyro.setSensitivity(kVoltsPerDegreePerSecond);
		
		robotDrive.setSafetyEnabled(false);
		
		leftMotor1.setInverted(false);
		leftMotor2.setInverted(false);
				
		rightMotor1.setInverted(false);
		rightMotor2.setInverted(false);
			
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveArcade());
    }
    

    public void DriveArcadeMethod(double Speed, double Rotation) {
    	//ptoSolenoid.set(true);
    	SmartDashboard.putNumber("ForwardSpeed", Speed);
    	SmartDashboard.putNumber("RotationSpeed", Rotation);
    	
    	double GoStraightCompensation = 0;
    	if(Math.abs(Speed) > 0.1)
    	{
    		GoStraightCompensation = Speed * 0.01 + 0.08 * Math.signum(Speed)  ; 
    		// TODO Tune this value. Has a speed proportional component (friction in mechanism()  and a fixed component
    	}
    	SmartDashboard.putNumber("Go Straight Compensation", GoStraightCompensation);
    	
    	robotDrive.arcadeDrive(Speed, Rotation + GoStraightCompensation);   	
    	
    }
    

    public void log()
    {
    	SmartDashboard.putNumber("GyroAngleAbsolute", gyro.getAngle());
    }
}

