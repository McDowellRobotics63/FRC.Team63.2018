/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team63.robot;

import org.usfirst.frc.team63.robot.XboxDPadButton.DPAD_BUTTON;
import org.usfirst.frc.team63.robot.commands.BoxDrop;
import org.usfirst.frc.team63.robot.commands.BoxObtain;
import org.usfirst.frc.team63.robot.commands.BoxShoot;
import org.usfirst.frc.team63.robot.simple_commands.BoxPullCommand;
import org.usfirst.frc.team63.robot.simple_commands.BoxPushCommand;
import org.usfirst.frc.team63.robot.simple_commands.BoxStopCommand;
import org.usfirst.frc.team63.robot.simple_commands.ClawCloseCommand;
import org.usfirst.frc.team63.robot.simple_commands.ClawOpenCommand;
import org.usfirst.frc.team63.robot.simple_commands.ClimbArmExtended;
import org.usfirst.frc.team63.robot.simple_commands.ClimbArmRetract;
import org.usfirst.frc.team63.robot.simple_commands.ClimbClampLock;
import org.usfirst.frc.team63.robot.simple_commands.ClimbClampOpen;
import org.usfirst.frc.team63.robot.simple_commands.ClimbRetract;
import org.usfirst.frc.team63.robot.simple_commands.ClimbStop;
import org.usfirst.frc.team63.robot.simple_commands.ClimbUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick controller1 = new Joystick(0);
	public Joystick controller2 = new Joystick(1);
	
	 public Button controller1_X = new JoystickButton(controller1 , RobotMap.XBOX_X);
	 public Button controller1_Y = new JoystickButton(controller1 , RobotMap.XBOX_Y);
	 public Button controller1_A = new JoystickButton(controller1 , RobotMap.XBOX_A);
	 public Button controller1_B = new JoystickButton(controller1 , RobotMap.XBOX_B);
	 public Button controller1_LB = new JoystickButton(controller1 , RobotMap.XBOX_LEFT_BUMPER);
	 public Button controller1_RB = new JoystickButton(controller1 , RobotMap.XBOX_RIGHT_BUMPER);
	 public Button controller1_start = new JoystickButton(controller1 , RobotMap.XBOX_START);
	 public Button controller1_back = new JoystickButton(controller1 , RobotMap.XBOX_BACK);
	 public Button controller1_dpadUp = new XboxDPadButton(controller1, DPAD_BUTTON.DPAD_UP);
	 public Button controller1_dpadRight = new XboxDPadButton(controller1, DPAD_BUTTON.DPAD_RIGHT);
	 public Button controller1_dpadDown = new XboxDPadButton(controller1, DPAD_BUTTON.DPAD_DOWN);
	 public Button controller1_dpadLeft = new XboxDPadButton(controller1, DPAD_BUTTON.DPAD_LEFT);
	
	 public Button controller2_X = new JoystickButton(controller2 , RobotMap.XBOX_X);
	 public Button controller2_Y = new JoystickButton(controller2 , RobotMap.XBOX_Y);
	 public Button controller2_A = new JoystickButton(controller2 , RobotMap.XBOX_A);
	 public Button controller2_B = new JoystickButton(controller2 , RobotMap.XBOX_B);
	 public Button controller2_LB = new JoystickButton(controller2 , RobotMap.XBOX_LEFT_BUMPER);
	 public Button controller2_RB = new JoystickButton(controller2 , RobotMap.XBOX_RIGHT_BUMPER);
	 public Button controller2_start = new JoystickButton(controller2 , RobotMap.XBOX_START);
	 public Button controller2_back = new JoystickButton(controller2 , RobotMap.XBOX_BACK);
	 public Button controller2_dpadUp = new XboxDPadButton(controller2, DPAD_BUTTON.DPAD_UP);
	 public Button controller2_dpadRight = new XboxDPadButton(controller2, DPAD_BUTTON.DPAD_RIGHT);
	 public Button controller2_dpadDown = new XboxDPadButton(controller2, DPAD_BUTTON.DPAD_DOWN);
	 public Button controller2_dpadLeft = new XboxDPadButton(controller2, DPAD_BUTTON.DPAD_LEFT);	 

	//// TRIGGERING COMMANDS WITH BUTTONS
	 public OI()
	 {
			
		 controller2_LB.whenPressed(new ClimbArmExtended());
		 controller2_RB.whenPressed(new ClimbArmRetract());
		 controller2_start.whenPressed(new ClimbClampLock());
		 controller2_back.whenPressed(new ClimbClampOpen());
		 controller2_dpadUp.whileHeld(new ClimbUp());
		 controller2_dpadUp.whenReleased(new ClimbStop());
		 controller2_dpadDown.whileHeld(new ClimbRetract());
		 controller2_dpadDown.whenReleased(new ClimbStop());
		 
		 controller1_RB.whenPressed(new ClawOpenCommand());
		 controller1_LB.whenPressed(new ClawCloseCommand());
		 controller1_A.whileHeld(new BoxObtain());
		 controller1_A.whenReleased(new BoxStopCommand());
		 controller1_B.whileHeld(new BoxPushCommand());
		 controller1_B.whenReleased(new BoxStopCommand());
		 
		 //Lift
		 
		 
		 //Drive Gear Shift
		 
		 
		 
	 }
	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
