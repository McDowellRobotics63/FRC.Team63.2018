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
	public static final int DRIVELEFTMASTER = 0;
	public static final int DRIVELEFTSLAVE = 0;
	public static final int DRIVERIGHTMASTER = 0;
	public static final int DRIVERIGHTSLAVE = 0;
	
	public static final int LIFT = 0;
	public static final double desiredClimb=0;
	//Spark PWM Channels
	public static final int CLAWLEFT = 0;
	public static final int CLAWRIGHT = 0;
	public static final int CLIMBWINCH = 0;
	
	//Solenoid Mappings
	public static final int ClimbArmSolenoid = 0;
	public static final int ClimbClampLockSolenoid = 0;
	
	//Solenoid Map
	public static final int LEFT_CLAW_SOLENOID = 0;
	public static final int RIGHT_CLAW_SOLENOID = 0;
	public static final int PCM_CANID = 0;
	
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
    
    public static final int kTimeoutMs = 10;
    //Drive constants
	public static final double kDriveWheelDiameterInches = 69;
	public static final double kDriveWheelCircumference = kDriveWheelDiameterInches*Math.PI; //inches
	public static final double kDriveTrack = 42; //inches between centerlines of left and right wheels
	public static final double kDriveEncoderFactor = 1440; //native units per rotation
	public static final int kTimeoutMs = 10; //ms to wait for talon timeout, literally doesn't matter
	public static final int kVelocityControlSlot = 0;

	//Solenoid Mapping
	public static final int PCMID = 0;
	public static final int LEFTSHIPHTUR = 0; //PCM id of pneumatic gearbox thing
	public static final int RIGHTSHIPHTUR = 0;
}
