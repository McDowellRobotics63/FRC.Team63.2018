package org.usfirst.frc.team63.robot.subsystems;

import org.usfirst.frc.team63.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
	 
	 public DriveSubsystem() {
		 TalonConfig();
	 }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /*Called in constructor to ensure correct drive talon settings*/
    private void TalonConfig() {
    	//Makes talons force zero voltage across when zero output to resist motion
    	LeftMaster.setNeutralMode(NeutralMode.Brake);
    	LeftSlave.setNeutralMode(NeutralMode.Brake);
    	RightMaster.setNeutralMode(NeutralMode.Brake);
    	RightSlave.setNeutralMode(NeutralMode.Brake);
    	
    	LeftSlave.set(ControlMode.Follower, RobotMap.DRIVELEFTMASTER);
    	RightSlave.set(ControlMode.Follower, RobotMap.DRIVERIGHTMASTER);
    }
}