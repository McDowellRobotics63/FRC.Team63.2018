package org.usfirst.frc.team63.robot.subsystems;

import org.usfirst.frc.team63.robot.InfraredSensor;
import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;
import org.usfirst.frc.team63.robot.commands_claw.ObtainBoxContinuous;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ClawSubsystem extends Subsystem {

	private Spark leftMotor = new Spark(RobotMap.CLAWLEFT);
	private Spark rightMotor = new Spark(RobotMap.CLAWRIGHT);
	private Solenoid CLAW_OPEN = new Solenoid(RobotMap.PCM1_CANID,RobotMap.CLAW_OPEN_SOLENOID);
	private Solenoid CLAW_CLOSE = new Solenoid(RobotMap.PCM1_CANID,RobotMap.CLAW_CLOSE_SOLENOID);
	private InfraredSensor sensor = new InfraredSensor(RobotMap.INFRARED_SENSOR_CHANNEL);
	
	public ClawSubsystem() {
		close();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ObtainBoxContinuous());
    }
    
    public void open() {
    	CLAW_OPEN.set(true);
    	CLAW_CLOSE.set(false);
    }
    
    public void close() {
    	CLAW_OPEN.set(false);
    	CLAW_CLOSE.set(true);
    }
    
    public void setSpeed(double pullSpeed) {
    	//positive left speed is pull
    	leftMotor.set(pullSpeed);
    	rightMotor.set(-pullSpeed);
    }
    
    public boolean boxIsClose()
    {
    	return Robot.claw.sensor.GetMedianVoltage() > 0.6;
    }
    
    public boolean boxIsReallyClose()
    {
    	return Robot.claw.sensor.GetMedianVoltage() > 2.6;
    }
}

