package org.usfirst.frc.team4947.robot.commands.autonomous;

public class DistanceAutoConstants {
	// X sur le sens de la largeur du terrain
	// y sur la longueur du terrain 
	
	// 90 is clockwise (right turn)
	// -90 is counterclockwise ( left turn) 
	public static final double DPORTALTOPORTAL_X = 264.0 /12.0; // inches to feet. 
	public static final double D_EXCHANGE_TO_SWITCH_X = 55.56 /12.0;
	public static final double DWALLTOSWITCH_Y = 168.0 /12.0;
	public static final double OFFSET_FROM_CENTER_X = 5.0 /12.0;
	public static final double DIST_CLEAR_EXCHANGE_Y = 36.0 /12.0;
	private static final double BUMPER_WIDTH_INCH = 3.0 ; // bumper thickness in inches (1 bumper)
	public static final double ROBOT_LENGTH_FORWARD_DIRECTION = (32.5 + 2.0* BUMPER_WIDTH_INCH) / 12.0 ; // total robot length (frame + bumper) -> to feet 
}
