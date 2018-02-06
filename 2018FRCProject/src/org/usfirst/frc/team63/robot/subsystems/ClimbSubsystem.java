package org.usfirst.frc.team63.robot.subsystems;

import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSubsystem extends Subsystem {
	private Spark winchMotor = new Spark(RobotMap.CLIMBWINCH);
	
	public void pullyclimb( double speed)
	{
		winchMotor.set(speed);
		
	}

    public void initDefaultCommand() {
    
    	
    }
}

