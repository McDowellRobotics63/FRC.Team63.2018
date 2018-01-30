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
	 
	
	public void motorConfig() {
		LeftMaster.configNominalOutputForward(0.0, RobotMap.kTimeoutMs);
    	LeftMaster.configNominalOutputReverse(-0.0, RobotMap.kTimeoutMs);
    	LeftMaster.configPeakOutputForward(+12.0, RobotMap.kTimeoutMs);    	    
    	LeftMaster.configPeakOutputReverse(-12.0, RobotMap.kTimeoutMs);  
    	
    	LeftSlave.configNominalOutputForward(0.0, RobotMap.kTimeoutMs);
    	LeftSlave.configNominalOutputReverse(-0.0, RobotMap.kTimeoutMs);
    	LeftSlave.configPeakOutputForward(+12.0, RobotMap.kTimeoutMs);    	    
    	LeftSlave.configPeakOutputReverse(-12.0, RobotMap.kTimeoutMs);   	    
    	
    	RightMaster.configNominalOutputForward(0.0, RobotMap.kTimeoutMs);
    	RightMaster.configNominalOutputReverse(-0.0, RobotMap.kTimeoutMs);
    	RightMaster.configPeakOutputForward(+12.0, RobotMap.kTimeoutMs);    	    
    	RightMaster.configPeakOutputReverse(-12.0, RobotMap.kTimeoutMs);   	    
    	
    	RightSlave.configNominalOutputForward(0.0, RobotMap.kTimeoutMs);
    	RightSlave.configNominalOutputReverse(-0.0, RobotMap.kTimeoutMs);
    	RightSlave.configPeakOutputForward(+12.0, RobotMap.kTimeoutMs);    	    
    	RightSlave.configPeakOutputReverse(-12.0, RobotMap.kTimeoutMs);    	    
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

