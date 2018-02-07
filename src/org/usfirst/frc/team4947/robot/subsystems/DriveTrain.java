package org.usfirst.frc.team4947.robot.subsystems;
import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.RobotMap;
import org.usfirst.frc.team4947.robot.commands.DriveArcade;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
	public static final int kSlotIdx = 0;
	
	/* Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops.  
	 * For now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/*
	 * set to zero to skip waiting for confirmation, set to nonzero to wait
	 * and report to DS if action fails.
	 */
	public static final int kTimeoutMs = 10;
	
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
		/* lets grab the 360 degree position of the MagEncoder's absolute position */
		int absolutePosition = leftMotor1.getSelectedSensorPosition(kTimeoutMs) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
		leftMotor1.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);
        
        /* choose the sensor and sensor direction */
		leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,kPIDLoopIdx, kTimeoutMs);
		leftMotor1.setSensorPhase(true);
        
        /* set the peak and nominal outputs, 12V means full */
		leftMotor1.configNominalOutputForward(0, kTimeoutMs);
		leftMotor1.configNominalOutputReverse(0, kTimeoutMs);
		leftMotor1.configPeakOutputForward(1, kTimeoutMs);
		leftMotor1.configPeakOutputReverse(-1, kTimeoutMs);
        /* set the allowable closed-loop error,
         * Closed-Loop output will be neutral within this range.
         * See Table in Section 17.2.1 for native units per rotation. 
         */
		leftMotor1.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs); /* always servo */
        /* set closed loop gains in slot0 */
		leftMotor1.config_kF(kPIDLoopIdx, 0.0, kTimeoutMs);
		leftMotor1.config_kP(kPIDLoopIdx, 0.8, kTimeoutMs); //0.1
		leftMotor1.config_kI(kPIDLoopIdx, 0.003, kTimeoutMs);
		leftMotor1.config_kD(kPIDLoopIdx, 0.0, kTimeoutMs);
			
	}
	
    public void initDefaultCommand() 
    {
        setDefaultCommand(new DriveArcade(new DriveTrain()));
    }
    
    public void driveToDistance(double distance_feet)
    {
    	
		/* lets grab the 360 degree position of the MagEncoder's absolute position */
		int absolutePosition = leftMotor1.getSelectedSensorPosition(kTimeoutMs) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
		leftMotor1.setSelectedSensorPosition(absolutePosition,kPIDLoopIdx,kTimeoutMs);
		
        double nombreToursMoteur = distance_feet / ((6*Math.PI)/12);
    	targetPositionRotations =  nombreToursMoteur * 80; /* 100 Rotations * 80 u/rev in either direction (85.42 rot/second @ free shaft)(20 per phase, quad encod) */
    	leftMotor1.set(ControlMode.Position, targetPositionRotations); /* 50 rotations in either direction */
    	
    	
    }
    
    public double getEncoderDistanceError()
    {
    	// return error remaining on the closed loop, in meters. 
    	return  leftMotor1.getClosedLoopTarget(kPIDLoopIdx);
    }

    public void driveArcadeMethod(double Speed, double Rotation) {
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
    
    public void driveStop()
    {
    	robotDrive.arcadeDrive(0,0);
    }
    
    
    public void gearboxShift(ShifterSpeed speed)
    {
    	gearboxSpeedSolenoid.set(speed.getValue());  
    	
    }
    
    public double getGyroAngle()
    {
    	return gyro.getAngle();
    }
    
    public void resetGyroAngle()
    {
    	gyro.reset();
    }
    
    public void log()
    {
    	SmartDashboard.putNumber("GyroAngleAbsolute", gyro.getAngle());
    }
}

