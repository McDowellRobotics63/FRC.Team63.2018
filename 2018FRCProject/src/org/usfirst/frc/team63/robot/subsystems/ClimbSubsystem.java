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
	private Solenoid armUpSolenoid = new Solenoid(RobotMap.PCM1_CANID, RobotMap.CLIMBARM_UP);
	private Solenoid armDownSolenoid = new Solenoid(RobotMap.PCM2_CANID, RobotMap.CLIMBARM_DOWN);
	private Solenoid lockCloseSolenoid = new Solenoid(RobotMap.PCM2_CANID, RobotMap.CLIMBCLAMPLOCK_CLOSE);	
	private Solenoid lockOpenSolenoid = new Solenoid(RobotMap.PCM1_CANID, RobotMap.CLIMBCLAMPLOCK_OPEN);
	
	public void pullyclimb(double speed)
	{
		winchMotor.set(speed);		
	}
	
	public void armExtend()
	{
		armUpSolenoid.set(true);
		armDownSolenoid.set(false);
	}
	
	public void armRetract()
	{
		armUpSolenoid.set(false);
		armDownSolenoid.set(true);
	}
	
	public void clampLock()
	{
		lockCloseSolenoid.set(true);
		lockOpenSolenoid.set(false);
	}
	
	public void clampOpen()
	{
		lockCloseSolenoid.set(false);
		lockOpenSolenoid.set(true);
	}
	
    public void initDefaultCommand() {
    
    }
}

