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
	
	public static final double BoxInputSpeed = 0;
	public static final double BoxOutputSpeed = 0;
	public static final double climbUpSpeed=0;
	public static final double climbDownSpeed=0;
	
    //Drive constants
	public static final double kDriveWheelDiameterInches = 6;
	public static final double kDriveWheelCircumference = kDriveWheelDiameterInches*Math.PI; //inches
	public static final double kDriveTrack = 23.25; //inches between centerlines of left and right wheels
	public static final double kDriveEncoderFactor = 4096; //native units per rotation
	public static final int kTimeoutMs = 10; //ms to wait for talon timeout, literally doesn't matter
	public static final int kVelocityControlSlot = 0;
	
	//Lift constants
	public static final double kLiftEncoderPPR = 4096;
	public static final double kLiftInchesPerRev = 1;
	public static final double kMaxDisplacementInches = 18;
	public static final double MIN_FORCE_UP = 0;
	
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
}
