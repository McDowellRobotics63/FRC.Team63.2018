/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team63.robot;

import org.usfirst.frc.team63.robot.XboxDPadButton.DPAD_BUTTON;
import org.usfirst.frc.team63.robot.commands_claw.AutoBoxObtain;
import org.usfirst.frc.team63.robot.commands_claw.BoxPushCommand;
import org.usfirst.frc.team63.robot.commands_claw.BoxStopCommand;
import org.usfirst.frc.team63.robot.commands_claw.ClawCloseCommand;
import org.usfirst.frc.team63.robot.commands_claw.ClawOpenCommand;
import org.usfirst.frc.team63.robot.commands_climb.ClimbArmExtended;
import org.usfirst.frc.team63.robot.commands_climb.ClimbArmRetract;
import org.usfirst.frc.team63.robot.commands_climb.ClimbClampLock;
import org.usfirst.frc.team63.robot.commands_climb.ClimbClampOpen;
import org.usfirst.frc.team63.robot.commands_climb.ClimbDown;
import org.usfirst.frc.team63.robot.commands_climb.ClimbStop;
import org.usfirst.frc.team63.robot.commands_climb.ClimbUp;
import org.usfirst.frc.team63.robot.commands_drive.TeleopDriveHighCommand;
import org.usfirst.frc.team63.robot.commands_drive.TeleopDriveLowCommand;
import org.usfirst.frc.team63.robot.commands_lift.MoveLiftMaxHeight;
import org.usfirst.frc.team63.robot.commands_lift.MoveLiftMinHeight;
import org.usfirst.frc.team63.robot.commands_lift.MoveLiftOneBoxHeight;
import org.usfirst.frc.team63.robot.subsystems.DriveSubsystem.Shift;
import org.usfirst.frc.team63.robot.subsystems.LiftSubsystem.Direction;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
		 controller2_dpadDown.whileHeld(new ClimbDown());
		 controller2_dpadDown.whenReleased(new ClimbStop());
		 
		 controller1_RB.whenPressed(new ClawOpenCommand());
		 controller1_LB.whenPressed(new ClawCloseCommand());
		 controller1_A.whileHeld(new AutoBoxObtain());
		 controller1_A.whenReleased(new BoxStopCommand());
		 controller1_B.whileHeld(new BoxPushCommand());
		 controller1_B.whenReleased(new BoxStopCommand());
		 		 
		 //Lift
		 controller1_dpadDown.whenPressed(new MoveLiftOneBoxHeight(Direction.DOWN));
		 controller1_dpadUp.whenPressed(new MoveLiftOneBoxHeight(Direction.UP));
		 controller1_dpadRight.whenPressed(new MoveLiftMaxHeight());
		 controller1_dpadLeft.whenPressed(new MoveLiftMinHeight());
		 
		 //Drive Gear Shift
		 controller1_X.whileHeld(new TeleopDriveHighCommand());
		 controller1_X.whenReleased(new TeleopDriveLowCommand());		 		
	 }
}
