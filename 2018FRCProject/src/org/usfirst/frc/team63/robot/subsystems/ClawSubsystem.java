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
	private Solenoid CLAW_OPEN = new Solenoid(RobotMap.PCM2_CANID,RobotMap.CLAW_OPEN_SOLENOID);
	private Solenoid CLAW_CLOSE = new Solenoid(RobotMap.PCM1_CANID,RobotMap.CLAW_CLOSE_SOLENOID);
	
	public ClawSubsystem() {
		clawToggle(false);
	}
	
    public void initDefaultCommand() {
    	
    }
    
    public void clawToggle(boolean isOpen) {
    	//true = open    	
    	CLAW_OPEN.set(isOpen);
    	CLAW_CLOSE.set(!isOpen);
    }
    
    
    public void clawSetSpeed(double speed) {
    	//positive left speed is pull
    	leftMotor.set(speed);
    	rightMotor.set(-speed);
    }
}

