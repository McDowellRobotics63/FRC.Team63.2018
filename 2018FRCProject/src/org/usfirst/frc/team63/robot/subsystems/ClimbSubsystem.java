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
	private Solenoid armSolenoid = new Solenoid(0, RobotMap.ClimbArmSolenoid);
	private Solenoid lockSolenoid = new Solenoid(0, RobotMap.ClimbClampLockSolenoid);
	public void pullyclimb( double speed)
	{
		winchMotor.set(speed);
		
	}
	public void armExtend (boolean isExtended)
	{
		armSolenoid.set(isExtended);
		
	}
	public void armRetract (boolean isExtended)
	{
		armSolenoid.set(isExtended);
		
	}
	public void clampLock(boolean isclamp)
	{
		lockSolenoid.set(isclamp);
	}
	
	
    public void initDefaultCommand() {
    
    	
    }
}

