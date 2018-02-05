package org.usfirst.frc.team63.robot.commands;

import org.usfirst.frc.team63.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveFixedDistance extends Command {
	public static final double PGAIN = 3.0; // hz
	public static final double GYROGAIN = 30;
	public static final double TOLERANCE = 3;
	public static final double TARGETTIME = 0.5;
	public static final double TOTALTIMELIMIT = 9999.0;
	public static final double MIN_SPEED = 0.2; //arcadeDrive input units equivalent
	public static final double MIN_ROTATE = 0.15;
	
	private double setpoint; //inches to go
	private final Timer totalTimer;
	private final Timer onTargetTimer;
	
    public AutoDriveFixedDistance() {
        requires(Robot.drive);
        totalTimer = new Timer();
        onTargetTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	totalTimer.reset();
    	totalTimer.start();
    	
    	Robot.drive.resetEncoders();
    	
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
    	//Robot.drive.DebugMotionMagic();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return totalTimer.get() > 0.5 && Math.abs(Robot.drive.getErrorLeft()) < 300 && Math.abs(Robot.drive.getErrorRight()) < 300;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.stop();
    }  
}
