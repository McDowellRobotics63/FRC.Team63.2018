package org.usfirst.frc.team63.robot.commands;

import java.util.Arrays;

import org.usfirst.frc.team63.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DashboardSetLiftPosition extends Command {
	private final Timer totalTimer;

    public DashboardSetLiftPosition() {
        requires(Robot.climb);
        requires(Robot.debug);
        
        totalTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.debug.Start("AutoSetLiftPosition", Arrays.asList("Sensor_Position", "Sensor_Velocity",
    			"Trajectory_Position", "Trajectory_Velocity", "Motor_Output", "Error")); 
    	totalTimer.reset();
    	totalTimer.start();

    	Robot.lift.configGains(
    			SmartDashboard.getNumber("kF_lift_up", 0.0), 
    			SmartDashboard.getNumber("kP_lift", 0.0), 
    			SmartDashboard.getNumber("kI_lift", 0.0), 
    			SmartDashboard.getNumber("kD_lift", 0.0),
    			(int)SmartDashboard.getNumber("kiZone_lift", 0.0),
    			(int)SmartDashboard.getNumber("kCruise_lift", 0.0),
    			(int)SmartDashboard.getNumber("kAccel_lift", 0.0));
    	
    	Robot.lift.setMotionMagicSetpoint(SmartDashboard.getNumber("setpoint_lift", 0.0));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.debug.Update(Robot.lift.DebugMotionMagic());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.lift.isMotionMagicNearTarget() || totalTimer.get() > 5.0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Just let the lift keep holding the current motion magic
    	//setpoint after this command ends
    	Robot.debug.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lift.hold();
    	Robot.debug.Stop();
    }  
}
