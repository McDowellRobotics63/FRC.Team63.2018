package org.usfirst.frc.team63.robot.commands;

import org.usfirst.frc.team63.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveFixedDistance extends Command {
	
	private double setpoint; //inches to go

    public AutoDriveFixedDistance() {
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.configGains(SmartDashboard.getNumber("kF", 0.0), 
    	  SmartDashboard.getNumber("kP", 0.0), 
    	  SmartDashboard.getNumber("kI", 0.0), 
    	  SmartDashboard.getNumber("kD", 0.0));
    	
        setpoint = SmartDashboard.getNumber("setpoint", 0.0);
        Robot.drive.setMotionMagicLeft(setpoint);
    	Robot.drive.setMotionMagicRight(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }  
}
