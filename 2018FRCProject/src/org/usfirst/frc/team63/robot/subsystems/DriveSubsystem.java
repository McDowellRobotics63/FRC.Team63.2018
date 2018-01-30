package org.usfirst.frc.team63.robot.subsystems;

import org.usfirst.frc.team63.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	 private WPI_TalonSRX LeftMaster = new WPI_TalonSRX(RobotMap.DRIVELEFTMASTER); 
	 private WPI_TalonSRX RightMaster = new WPI_TalonSRX(RobotMap.DRIVERIGHTMASTER); 
	 private WPI_TalonSRX LeftSlave = new WPI_TalonSRX(RobotMap.DRIVELEFTSLAVE); 
	 private WPI_TalonSRX RightSlave = new WPI_TalonSRX(RobotMap.DRIVERIGHTSLAVE); 

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

