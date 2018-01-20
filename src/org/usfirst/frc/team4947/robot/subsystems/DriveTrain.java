package org.usfirst.frc.team4947.robot.subsystems;
import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.RobotMap;
import org.usfirst.frc.team4947.robot.commands.DriveArcade;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	private WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR1_ADDRESS); // encoder
	private WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR2_ADDRESS);
	private SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMotor1, leftMotor2);
	
	private WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR1_ADDRESS); // encoder
	private WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR2_ADDRESS);
	private SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMotor1, rightMotor2);
	
	double targetPositionRotations;
	
	private Solenoid gearboxSpeedSolenoid = new Solenoid(RobotMap.GEARBOX_SPEEDSOLENOID_ADDRESS);

	private AnalogGyro gyro = new AnalogGyro(RobotMap.ANALOG_GYRO_ADDRESS);
	private static final double kVoltsPerDegreePerSecond = 0.0128/2; // gyro sensitivity, estimated 2017, jp choiniere
	
	private DifferentialDrive robotDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
	
	// Define names for the shifter possibilities.
	public enum ShifterSpeed
	{
		Fast(true),
		Slow(false);
		
		private boolean value;
		ShifterSpeed(boolean value){
			this.value = value;
		}
		
		public boolean getValue() {
			return value;
		}
	}
	
	
	public DriveTrain()
	{
		gyro.setSensitivity(kVoltsPerDegreePerSecond);
		
		robotDrive.setSafetyEnabled(false);
		
		leftMotor1.setInverted(false);
		leftMotor2.setInverted(false);
				
		rightMotor1.setInverted(false);
		rightMotor2.setInverted(false);
			
	}
	
    public void initDefaultCommand() 
    {
        setDefaultCommand(new DriveArcade());
    }
    
    public void DriveToDistance(double distance_meters)
    {
    	// todo : faire la boucle fermée
    }
    
    public double GetEncoderDistanceError()
    {
    	// return error remaining on the closed loop, in meters. 
    	return  0.1;
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
    
    public void DriveStop()
    {
    	robotDrive.arcadeDrive(0,0);
    }
    
    
    public void GearboxShift(ShifterSpeed speed)
    {
    	gearboxSpeedSolenoid.set(speed.getValue());  
    	// Change the mapping in the ShifterSpeed enum, if required.
    }
    
    public double GetGyroAngle()
    {
    	return gyro.getAngle();
    }
    
    public void ResetGyroAngle()
    {
    	gyro.reset();
    }
    
    public void log()
    {
    	SmartDashboard.putNumber("GyroAngleAbsolute", gyro.getAngle());
    }
}

