package org.usfirst.frc.team63.robot.subsystems;

import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSubsystem extends Subsystem {
	private Spark winchMotor = new Spark(RobotMap.CLIMBWINCH);
	private Solenoid armSolenoid = new Solenoid(RobotMap.PCM1_CANID, RobotMap.CLIMBARM);
	private Solenoid lockSolenoid = new Solenoid(RobotMap.PCM1_CANID, RobotMap.CLIMBCLAMPLOCK);
	
	public void pullyclimb(double speed)
	{
		winchMotor.set(speed);		
	}
	
	public void armExtend()
	{
		armSolenoid.set(true);		
	}
	
	public void armRetract()
	{
		armSolenoid.set(false);		
	}
	
	public void clampLock()
	{
		lockSolenoid.set(true);
	}
	
	public void clampOpen()
	{
		lockSolenoid.set(false);
	}
	
    public void initDefaultCommand() {
    
    }
}

