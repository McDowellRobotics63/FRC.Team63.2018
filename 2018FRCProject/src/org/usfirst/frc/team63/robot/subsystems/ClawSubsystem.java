package org.usfirst.frc.team63.robot.subsystems;

import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClawSubsystem extends Subsystem {

	private Spark leftMotor = new Spark(RobotMap.CLAWLEFT);
	private Spark rightMotor = new Spark(RobotMap.CLAWRIGHT);

	
	public ClawSubsystem() {
	}
	
    public void initDefaultCommand() {
    	
    }
    
    public void clawOpen() {
    	
    }
    
    public void clawClose() {
    	
    }
    
    public void clawPull(double speed) {
    	//positive left speed is pull
    	leftMotor.set(speed);
    	rightMotor.set(-speed);
    }
}

