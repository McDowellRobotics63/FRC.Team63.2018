/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team63.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team63.robot.commands_auto.AutoRoutine1;
import org.usfirst.frc.team63.robot.commands_auto.DownAndBack;
import org.usfirst.frc.team63.robot.commands_drive.AutoDriveFixedDistance;
import org.usfirst.frc.team63.robot.commands_drive.AutoRotate;
import org.usfirst.frc.team63.robot.commands_drive.SetAxis;
import org.usfirst.frc.team63.robot.commands_drive.TeleopDriveLowCommand;
import org.usfirst.frc.team63.robot.commands_lift.AutoSetLiftPosition;
import org.usfirst.frc.team63.robot.commands_test.DashboardSetLiftPosition;
//import org.usfirst.frc.team63.robot.commands.ExampleCommand;
import org.usfirst.frc.team63.robot.subsystems.ClawSubsystem;
import org.usfirst.frc.team63.robot.subsystems.ClimbSubsystem;
import org.usfirst.frc.team63.robot.subsystems.DebugSubsystem;
import org.usfirst.frc.team63.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team63.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team63.robot.subsystems.VisionSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveSubsystem drive = new DriveSubsystem();
	public static final ClawSubsystem claw = new ClawSubsystem();
	public static final ClimbSubsystem climb = new ClimbSubsystem();
	public static final LiftSubsystem lift = new LiftSubsystem();
	public static final VisionSubsystem vision = new VisionSubsystem();
	public static final DebugSubsystem debug = new DebugSubsystem();
	
	public static OI m_oi;

	TeleopDriveLowCommand teledrive = new TeleopDriveLowCommand();
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	public DigitalInput switch1 = new DigitalInput(RobotMap.AUTO_SWITCH_1);
	public DigitalInput switch2 = new DigitalInput(RobotMap.AUTO_SWITCH_2);
	public DigitalInput switch3 = new DigitalInput(RobotMap.AUTO_SWITCH_3);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		
		SmartDashboard.putNumber("left_y_rate", 1.0);
		SmartDashboard.putNumber("left_y_expo", 0.0);
		SmartDashboard.putNumber("left_y_deadband", 0.18);
		SmartDashboard.putNumber("left_y_power", 1.0);
		SmartDashboard.putNumber("left_y_min", -1.0);
		SmartDashboard.putNumber("left_y_max", 1.0);
		
		SmartDashboard.putNumber("left_x_rate", 1.0);
		SmartDashboard.putNumber("left_x_expo", 0.8);
		SmartDashboard.putNumber("left_x_deadband", 0.18);
		SmartDashboard.putNumber("left_x_power", 5.0);
		SmartDashboard.putNumber("left_x_min", -0.5);
		SmartDashboard.putNumber("left_x_max", 0.5);
		
		SmartDashboard.putNumber("right_y_rate", 1.0);
		SmartDashboard.putNumber("right_y_expo", 0.0);
		SmartDashboard.putNumber("right_y_deadband", 0.18);
		SmartDashboard.putNumber("right_y_power", 1.0);
		SmartDashboard.putNumber("right_y_min", -1.0);
		SmartDashboard.putNumber("left_y_max", 1.0);
		
		SmartDashboard.putNumber("right_x_rate", 1.0);
		SmartDashboard.putNumber("right_x_expo", 0.0);
		SmartDashboard.putNumber("right_x_deadband", 0.18);
		SmartDashboard.putNumber("right_x_power", 1.0);
		SmartDashboard.putNumber("right_x_min", -1.0);
		SmartDashboard.putNumber("left_x_max", 1.0);
		
//		m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putNumber("setpoint_rotate", 180.0);
		SmartDashboard.putNumber("cruise_rotate", 800.0);
		SmartDashboard.putNumber("acceleration_rotate", 1600.0);
		SmartDashboard.putNumber("drive_track", 26);
		
		
		SmartDashboard.putNumber("setpoint", 240.0);
		SmartDashboard.putNumber("kF", 0.5683);
		SmartDashboard.putNumber("kP", 0.5); 
		SmartDashboard.putNumber("kI", 0.003); 
		SmartDashboard.putNumber("kD", 35.0);
		SmartDashboard.putNumber("kiZone", 200.0);
		SmartDashboard.putNumber("cruise", 1600.0);
		SmartDashboard.putNumber("acceleration", 1600.0);
		
		SmartDashboard.putData("Auto mode", m_chooser);
		
		
		SmartDashboard.putNumber("kF_lift_up", 0.016);
		SmartDashboard.putNumber("kF_lift_down", 0.012);
		
		SmartDashboard.putNumber("kP_lift", 0.45); 
		SmartDashboard.putNumber("kI_lift", 0.0045); 
		SmartDashboard.putNumber("kD_lift", 4.5);
		SmartDashboard.putNumber("kiZone_lift", 300.0);
		
		SmartDashboard.putNumber("kCruise_lift", 51200.0);
		SmartDashboard.putNumber("kAccel_lift", 102400.0);
		
		SmartDashboard.putNumber("setpoint_lift", 0.0);		
		SmartDashboard.putData("TestLift", new DashboardSetLiftPosition());
		SmartDashboard.putData("AutoDrive", new AutoDriveFixedDistance());
		SmartDashboard.putData("AutoRotate", new AutoRotate());
		SmartDashboard.putData("SetAxis", new SetAxis());

		SmartDashboard.putNumber("lift_cmd", 0);
		Robot.lift.configGains(
    			SmartDashboard.getNumber("kF_lift_up", 0.0), 
    			SmartDashboard.getNumber("kP_lift", 0.0), 
    			SmartDashboard.getNumber("kI_lift", 0.0), 
    			SmartDashboard.getNumber("kD_lift", 0.0),
    			(int)SmartDashboard.getNumber("kiZone_lift", 0.0),
    			(int)SmartDashboard.getNumber("kCruise_lift", 0.0),
    			(int)SmartDashboard.getNumber("kAccel_lift", 0.0));
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		teledrive.cancel();
		drive.teleInit();
	}

	@Override
	public void disabledPeriodic() {
		Robot.lift.resetIntegrator();
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//m_autonomousCommand = m_chooser.getSelected();
		//m_autonomousCommand =  new DownAndBack();

		climb.armRetract();
		drive.autoInit();
		lift.initSubsystem();
		// schedule the autonomous command (example)
		//while (DriverStation.getInstance().getGameSpecificMessage().isEmpty());
		int switches = (switch1.get()?4:0) + (switch2.get()?2:0) + (switch3.get()?1:0);
		m_autonomousCommand = new AutoRoutine1(DriverStation.getInstance().getGameSpecificMessage().toLowerCase(), switches);
		//m_autonomousCommand = new AutoDriveFixedDistance(120);
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("left position", drive.getLeftPosition());	
		SmartDashboard.putNumber("right position", drive.getRightPosition());
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		m_oi.controller1.setAxisCurve();
		m_oi.controller2.setAxisCurve();
		climb.armRetract();
		drive.teleInit();
		lift.initSubsystem();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		teledrive.start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("lift_pos", lift.getCurrentPosition());
		SmartDashboard.putNumber("lift_volts", lift.liftMotor.getMotorOutputVoltage());
		SmartDashboard.putNumber("Left Speed",drive.getLeftSpeed());
		SmartDashboard.putNumber("Right Speed",drive.getRightSpeed());
		SmartDashboard.putNumber("left position", drive.getLeftPosition());	
		SmartDashboard.putNumber("right position", drive.getRightPosition());
//		SmartDashboard.putNumber("current_lift", lift.liftMotor.getOutputCurrent());
		SmartDashboard.putNumber("ActiveTrajectoryHeading_lift", lift.liftMotor.getActiveTrajectoryHeading());
		SmartDashboard.putBoolean("box close",claw.boxIsClose());
		SmartDashboard.putBoolean("box really close",claw.boxIsReallyClose());
		SmartDashboard.putNumber("ActiveTrajectoryPosition_lift", lift.liftMotor.getActiveTrajectoryPosition());
		SmartDashboard.putNumber("ActiveTrajectoryVelocity", lift.liftMotor.getActiveTrajectoryVelocity());
		
		SmartDashboard.putBoolean("LiftBottom", lift.isBottomedOut());
		SmartDashboard.putBoolean("HookPressed", climb.isHookPressed());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
