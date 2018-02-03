/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team63.robot;

import org.usfirst.frc.team63.robot.XboxDPadButton.DPAD_BUTTON;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick stick1 = new Joystick(0);
	public Joystick stick2 = new Joystick(1);
	
	 public Button stick1_X = new JoystickButton(stick1 , RobotMap.XBOX_X);
	 public Button stick1_Y = new JoystickButton(stick1 , RobotMap.XBOX_Y);
	 public Button stick1_A = new JoystickButton(stick1 , RobotMap.XBOX_A);
	 public Button stick1_B = new JoystickButton(stick1 , RobotMap.XBOX_B);
	 public Button stick1_LB = new JoystickButton(stick1 , RobotMap.XBOX_LEFT_BUMPER);
	 public Button stick1_RB = new JoystickButton(stick1 , RobotMap.XBOX_RIGHT_BUMPER);
	 public Button stick1_start = new JoystickButton(stick1 , RobotMap.XBOX_START);
	 public Button stick1_back = new JoystickButton(stick1 , RobotMap.XBOX_BACK);
	 public Button stick1_dpadUp = new XboxDPadButton(stick1, DPAD_BUTTON.DPAD_UP);
	 public Button stick1_dpadRight = new XboxDPadButton(stick1, DPAD_BUTTON.DPAD_RIGHT);
	 public Button stick1_dpadDown = new XboxDPadButton(stick1, DPAD_BUTTON.DPAD_DOWN);
	 public Button stick1_dpadLeft = new XboxDPadButton(stick1, DPAD_BUTTON.DPAD_LEFT);
	
	 public Button stick2_X = new JoystickButton(stick2 , RobotMap.XBOX_X);
	 public Button stick2_Y = new JoystickButton(stick2 , RobotMap.XBOX_Y);
	 public Button stick2_A = new JoystickButton(stick2 , RobotMap.XBOX_A);
	 public Button stick2_B = new JoystickButton(stick2 , RobotMap.XBOX_B);
	 public Button stick2_LB = new JoystickButton(stick2 , RobotMap.XBOX_LEFT_BUMPER);
	 public Button stick2_RB = new JoystickButton(stick2 , RobotMap.XBOX_RIGHT_BUMPER);
	 public Button stick2_start = new JoystickButton(stick2 , RobotMap.XBOX_START);
	 public Button stick2_back = new JoystickButton(stick2 , RobotMap.XBOX_BACK);
	 public Button stick2_dpadUp = new XboxDPadButton(stick2, DPAD_BUTTON.DPAD_UP);
	 public Button stick2_dpadRight = new XboxDPadButton(stick2, DPAD_BUTTON.DPAD_RIGHT);
	 public Button stick2_dpadDown = new XboxDPadButton(stick2, DPAD_BUTTON.DPAD_DOWN);
	 public Button stick2_dpadLeft = new XboxDPadButton(stick2, DPAD_BUTTON.DPAD_LEFT);	 

	//// TRIGGERING COMMANDS WITH BUTTONS
	 public OI()
	 {
			
//	 stick2_Y.whenPressed(new ShootUpClimbCommand());
//	 stick2_X.whenPressed(new LockClimbCommand());
//	 stick2_B.whileHeld(new ClimbCommand());
//	 stick2_A.whileHeld(new DescendCommand());
//	 stick2_LB.whenPressed(new OpenClawCommand());
//	 stick2_RB.whenPressed(new CloseClawCommand());
//	 stick2_dpadDown.whileHeld(new LiftUpCommand());
//	 stick2_dpadUp.whileHeld(new LiftDownCommand());
//	 
//	 stick1_X.whenPressed(new ResetGyroOriginCommand());
	 }
	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
