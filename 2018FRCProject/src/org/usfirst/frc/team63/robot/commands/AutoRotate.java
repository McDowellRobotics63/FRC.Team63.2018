package org.usfirst.frc.team63.robot.commands;

import java.util.Arrays;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoRotate extends Command {
	
	private double setpoint; //inches to go
	private final Timer totalTimer;

    public AutoRotate() {
        requires(Robot.drive);
        requires(Robot.debug);
        totalTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.debug.Start("MotionMagicCalibration", Arrays.asList("Sensor_Position", "Sensor_Velocity",
    			"Trajectory_Position", "Trajectory_Velocity", "Motor_Output", "Error")); 
    	totalTimer.reset();
    	totalTimer.start();
    	
    	Robot.drive.resetEncoders();
    	
    	Robot.drive.configGains(SmartDashboard.getNumber("kF", 0.0), 
    	  SmartDashboard.getNumber("kP", 0.0), 
    	  SmartDashboard.getNumber("kI", 0.0), 
    	  SmartDashboard.getNumber("kD", 0.0),
    	  (int)SmartDashboard.getNumber("kiZone", 0.0),
    	  (int)SmartDashboard.getNumber("cruise_rotate", 0.0),
    	  (int)SmartDashboard.getNumber("acceleration_rotate", 0.0));
    	
    	
        setpoint = degreesToInches(SmartDashboard.getNumber("setpoint_rotate", 0.0));
        Robot.drive.setMotionMagicLeft(setpoint);
    	Robot.drive.setMotionMagicRight(-setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.debug.Update(Robot.drive.DebugMotionMagicRight());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return totalTimer.get() > 10;
        //return totalTimer.get() > 0.5 && Math.abs(Robot.drive.getErrorLeft()) < 300 && Math.abs(Robot.drive.getErrorRight()) < 300;
    	//return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stop();
    	Robot.debug.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.stop();
    	Robot.debug.Stop();
    }
    
    private double degreesToInches(double degrees) {
    	return degrees/180*Math.PI*(RobotMap.kDriveTrack/2);
    }
}
