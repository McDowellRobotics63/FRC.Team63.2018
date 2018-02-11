package org.usfirst.frc.team63.robot.commands;

import java.util.Arrays;

import org.usfirst.frc.team63.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoSetLiftPosition extends Command {
	
	private double m_setpoint;
	private final Timer totalTimer;

    public AutoSetLiftPosition(double setpoint) {
        requires(Robot.climb);
        requires(Robot.debug);
        
        m_setpoint = setpoint;
        totalTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.debug.Start("AutoSetLiftPosition", Arrays.asList("Sensor_Position", "Sensor_Velocity",
    			"Trajectory_Position", "Trajectory_Velocity", "Motor_Output", "Error")); 
    	totalTimer.reset();
    	totalTimer.start();
    	
    	m_setpoint = SmartDashboard.getNumber("setpoint_lift", 0.0);
    	
    	if(Robot.lift.getCurrentSetpoint() < m_setpoint)
    	{
	    	Robot.lift.configGains(
	    			SmartDashboard.getNumber("kF_lift_up", 0.0), 
	    			SmartDashboard.getNumber("kP_lift_up", 0.0), 
	    			SmartDashboard.getNumber("kI_lift_up", 0.0), 
	    			SmartDashboard.getNumber("kD_lift_up", 0.0),
	    			(int)SmartDashboard.getNumber("kiZone_lift_up", 0.0),
	    			(int)SmartDashboard.getNumber("kCruise_lift", 0.0),
	    			(int)SmartDashboard.getNumber("kAccel_lift", 0.0));
    	}
    	else
    	{
	    	Robot.lift.configGains(
	    			SmartDashboard.getNumber("kF_lift_down", 0.0), 
	    			SmartDashboard.getNumber("kP_lift_down", 0.0), 
	    			SmartDashboard.getNumber("kI_lift_down", 0.0), 
	    			SmartDashboard.getNumber("kD_lift_down", 0.0),
	    			(int)SmartDashboard.getNumber("kiZone_lift_down", 0.0),
	    			(int)SmartDashboard.getNumber("kCruise_lift", 0.0),
	    			(int)SmartDashboard.getNumber("kAccel_lift", 0.0));
    	}

    	Robot.lift.setMotionMagicSetpoint(m_setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.debug.Update(Robot.lift.DebugMotionMagic());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return totalTimer.get() > 10;
        //return totalTimer.get() > 0.5 && Math.abs(Robot.lift.getMotionMagicError() < 10);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.stop();
    	Robot.debug.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lift.stop();
    	Robot.debug.Stop();
    }  
}
