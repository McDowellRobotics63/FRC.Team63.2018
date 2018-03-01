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
	public XboxController controller1 = new XboxController(0);
	public XboxController controller2 = new XboxController(1);

	//// TRIGGERING COMMANDS WITH BUTTONS
	 public OI()
	 {			
		 controller2.RightBumper().whenPressed(new ClimbArmExtended());
		 controller2.LeftBumper().whenPressed(new ClimbArmRetract());
		 controller2.Back().whenPressed(new ClimbClampLock());
		 controller2.Start().whenPressed(new ClimbClampOpen());
		 controller2.Y().whileHeld(new ClimbUp());
		 controller2.Y().whenReleased(new ClimbStop());
		 controller2.X().whileHeld(new ClimbDown());
		 controller2.X().whenReleased(new ClimbStop());
		 
//		 controller1.DpadRight().whenPressed(new ClawOpenCommand());
//		 controller1.LeftBumper().whenPressed(new ClawCloseCommand());
//		 controller2.A().whileHeld(new AutoBoxObtain());
//		 controller2.A().whenReleased(new BoxStopCommand());
		 controller2.B().whileHeld(new BoxPushCommand());
//		 controller2.B().whenReleased(new BoxStopCommand());
		 		 
		 //Lift
		 controller1.DpadDown().whenPressed(new MoveLiftOneBoxHeight(Direction.DOWN));
		 controller1.DpadUp().whenPressed(new MoveLiftOneBoxHeight(Direction.UP));
		 controller1.DpadRight().whenPressed(new MoveLiftMaxHeight());
		 controller1.DpadLeft().whenPressed(new MoveLiftMinHeight());
		 
		 //Drive Gear Shift
		 controller1.X().whileHeld(new TeleopDriveHighCommand());
		 controller1.X().whenReleased(new TeleopDriveLowCommand());		 		
	 }
}
