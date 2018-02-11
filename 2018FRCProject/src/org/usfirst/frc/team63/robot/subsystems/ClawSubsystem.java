package org.usfirst.frc.team63.robot.subsystems;

import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClawSubsystem extends Subsystem {

	private Spark leftMotor = new Spark(RobotMap.CLAWLEFT);
	private Spark rightMotor = new Spark(RobotMap.CLAWRIGHT);
	private Solenoid LEFT_CLAW = new Solenoid(RobotMap.PCM1_CANID,RobotMap.LEFT_CLAW_SOLENOID);
	private Solenoid RIGHT_CLAW = new Solenoid(0,RobotMap.RIGHT_CLAW_SOLENOID);
	
	public ClawSubsystem() {
	}
	
    public void initDefaultCommand() {
    	
    }
    
    public void clawToggle(boolean isOpen) {
    	//true = open
    	LEFT_CLAW.set(isOpen);
    	RIGHT_CLAW.set(isOpen);
    }
    
    
    public void clawPull(double speed) {
    	//positive left speed is pull
    	leftMotor.set(speed);
    	rightMotor.set(-speed);
    }
}

