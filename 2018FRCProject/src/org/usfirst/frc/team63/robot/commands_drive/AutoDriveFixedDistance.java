package org.usfirst.frc.team63.robot.commands_drive;

import java.util.Arrays;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveFixedDistance extends Command {
	
	private double testingSetpoint; //inches to go
	private double setpoint; //actual inches for normal use
	private final Timer totalTimer;
	private boolean isTesting = true;

    public AutoDriveFixedDistance() {
        requires(Robot.drive);
        //requires(Robot.debug);
        totalTimer = new Timer();
    }
    
    public AutoDriveFixedDistance(double d) {
        requires(Robot.drive);
        //requires(Robot.debug);
        totalTimer = new Timer();
        isTesting = false;
        setpoint = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (isTesting) {
	    	Robot.debug.Start("AutoDriveFixedDistance", Arrays.asList("Sensor_Position_R", "Sensor_Velocity_R",
	    			"Trajectory_Position_R", "Trajectory_Velocity_R", "Motor_Output_R", "Error_R", "Sensor_Position_L", "Sensor_Velocity_L",
	    			"Trajectory_Position_L", "Trajectory_Velocity_L", "Motor_Output_L", "Error_L")); 
	    	totalTimer.reset();
	    	totalTimer.start();
	    	
	    	Robot.drive.resetEncoders();
	    	
	    	Robot.drive.configGains(SmartDashboard.getNumber("kF", 0.0), 
	    	  SmartDashboard.getNumber("kP", 0.0), 
	    	  SmartDashboard.getNumber("kI", 0.0), 
	    	  SmartDashboard.getNumber("kD", 0.0),
	    	  (int)SmartDashboard.getNumber("kiZone", 0.0),
	    	  (int)SmartDashboard.getNumber("cruise", 0.0),
	    	  (int)SmartDashboard.getNumber("acceleration", 0.0));
	    	
	    	
	        testingSetpoint = SmartDashboard.getNumber("setpoint", 0.0);
	        Robot.drive.setMotionMagicLeft(testingSetpoint);
	    	Robot.drive.setMotionMagicRight(testingSetpoint);
	    }
    	
    	else {
    		totalTimer.reset();
	    	totalTimer.start();
	    	Robot.drive.resetEncoders();
	    	Robot.drive.configGains(RobotMap.DRIVE_F, RobotMap.DRIVE_P, RobotMap.DRIVE_I, RobotMap.DRIVE_D,
	    			RobotMap.DRIVE_IZONE, RobotMap.DRIVE_CRUISE, RobotMap.DRIVE_ACCEL);
	        Robot.drive.setMotionMagicLeft(setpoint);
	    	Robot.drive.setMotionMagicRight(setpoint);
    	}
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isTesting) Robot.debug.Update(Robot.drive.DebugMotionMagic());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (totalTimer.get() > 1.0 && Robot.drive.isMotionMagicNearTarget()) || totalTimer.get() > 10.0;
        //return totalTimer.get() > 0.5 && Math.abs(Robot.drive.getErrorLeft()) < 300 && Math.abs(Robot.drive.getErrorRight()) < 300;
    	//return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stop();
    	if(isTesting) Robot.debug.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.stop();
    	if(isTesting) Robot.debug.Stop();
    }  
}
