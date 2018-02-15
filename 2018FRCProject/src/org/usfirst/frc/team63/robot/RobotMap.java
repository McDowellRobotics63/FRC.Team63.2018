/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team63.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Talon CAN IDs
	public static final int DRIVELEFTMASTER = 1;
	public static final int DRIVELEFTSLAVE = 2;
	public static final int DRIVERIGHTMASTER = 3;
	public static final int DRIVERIGHTSLAVE = 4;	
	public static final int LIFT = 5;
	
	//Spark PWM Channels
	public static final int CLAWLEFT = 0;
	public static final int CLAWRIGHT = 0;
	public static final int CLIMBWINCH = 0;
	
	//Solenoid Mappings
	public static final int PCM1_CANID = 6;
	public static final int PCM2_CANID = 7;
	public static final int CLIMBARM = 0;
	public static final int CLIMBCLAMPLOCK = 0;
	public static final int LEFT_CLAW_SOLENOID = 0;
	public static final int RIGHT_CLAW_SOLENOID = 0;
	public static final int SHIFTER = 0;
	
	public static final double BOX_IN_SPEED = 0;
	public static final double BOX_OUT_SPEED = 0;
	public static final double CLIMB_UP_SPEED = 0;
	public static final double CLIMB_DOWN_SPEED = 0;
	public static final double LIFT_SPEED = 0;
	
    //Drive constants
	private static final double DRIVE_WHEEL_DIAMETER = 6;
	public static final double DRIVE_WHEEL_CIRCUMFERENCE = DRIVE_WHEEL_DIAMETER*Math.PI; //inches
	public static final double DRIVE_TRACK = 23.25; //inches between centerlines of left and right wheels
	public static final double DRIVE_ENCODER_PPR = 4096; //native units per rotation
	public static final int TIMOUT_MS = 10; //ms to wait for talon timeout, literally doesn't matter
	public static final int VELOCITY_CONTROL_SLOT = 0;
	
	//Lift constants
	public static final double LIFT_ENCODER_PPR = 4096;
	public static final double LIFT_INCHES_PER_REV = 1;
	private static final double MAX_LIFT_DISPLACEMENT_INCHES = 18;
	public static final double MAX_LIFT_DISPLACEMENT = MAX_LIFT_DISPLACEMENT_INCHES*LIFT_ENCODER_PPR/LIFT_INCHES_PER_REV; //native units
	public static final double MIN_FORCE_UP = 0;
	private static final double BOX_HEIGHT_INCHES = 11;
	public static final double BOX_HEIGHT = BOX_HEIGHT_INCHES*LIFT_ENCODER_PPR/LIFT_INCHES_PER_REV; //native encoder units
	
	//Controller Map
	public static final int XBOX_LEFT_X_AXIS = 0;
    public static final int XBOX_LEFT_Y_AXIS = 1;
    public static final int XBOX_LEFT_TRIGGER_AXIS = 2;
    public static final int XBOX_RIGHT_TRIGGER_AXIS = 3;
    public static final int XBOX_RIGHT_X_AXIS = 4;
    public static final int XBOX_RIGHT_Y_AXIS = 5;
    public static final int XBOX_A = 1;
    public static final int XBOX_B = 2;
    public static final int XBOX_X = 3;
    public static final int XBOX_Y = 4;
    public static final int XBOX_LEFT_BUMPER = 5;    
    public static final int XBOX_RIGHT_BUMPER = 6;
    public static final int XBOX_BACK = 7;
    public static final int XBOX_START = 8;
    
    //Vision
    public static final double POWER = -1.07436389463682;
    public static final double XCOEFF = 11834.7562904465;
    public static final double intercept = -4.40793976463289;
}
