/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team63.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RobotTemplate extends Subsystem {
	
	static double range;
	static Ultrasonic ultra = new Ultrasonic(0,0); // creates the ultra object andassigns ultra to be an ultrasonic sensor which uses DigitalOutput 1 for 
        // the echo pulse and DigitalInput 1 for the trigger pulse
    public void robotInit() {
    	ultra.setAutomaticMode(true); // turns on automatic mode
    }

    public static void ultrasonicSample() {
     range = ultra.getRangeInches(); // reads the range on the ultrasonic sensor
    	
    }
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		ultrasonicSample();
		System.out.println(range);
	}
}