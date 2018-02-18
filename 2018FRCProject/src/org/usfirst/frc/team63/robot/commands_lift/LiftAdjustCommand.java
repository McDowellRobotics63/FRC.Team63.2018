package org.usfirst.frc.team63.robot.commands_lift;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftAdjustCommand extends Command {
	private double lastTime = 0;
	private final Timer totalTimer;
	
    public LiftAdjustCommand() {
        requires(Robot.lift);
        
        //This will be the default command for the lift.
        //Operator can always be adjust the lift using analog stick.
        //If another command comes in that wants to set the setpoint to
        //a pre-set value (top, bottom, box height) then let it interrupt to do so.
        setInterruptible(true);
        
        totalTimer = new Timer();
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {    	
    	totalTimer.reset();
    	totalTimer.start();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentTime = totalTimer.get();
    	double timeElapsedSeconds = (currentTime - lastTime);
    	
    	double axis = -Robot.m_oi.controller1.getRawAxis(RobotMap.XBOX_RIGHT_Y_AXIS);
    	
    	if(axis > -0.2 && axis < 0.2)
    	{
    		axis = 0;
    	}
    	
    	axis = Math.signum(axis) * ((Math.abs(axis) - 0.2) / 0.8);
    	
//    	System.out.println("axis: " + axis);
    	
//    	System.out.println("setpoint: " + Robot.lift.getCurrentSetpoint() + 
//    			           ", axis: " + Robot.m_oi.controller1.getRawAxis(RobotMap.XBOX_RIGHT_Y_AXIS) + 
//    			           ", timeElapsedSeconds: " + timeElapsedSeconds);
    	  	
    	//Let the operator move the setpoint by 0-100% of the max lift adjust speed
    	Robot.lift.setMotionMagicSetpoint(
    			Robot.lift.getCurrentSetpoint() + 
    			axis * 
    			RobotMap.MAX_LIFT_ADJUST_SPEED * timeElapsedSeconds
    			);
    	
    	lastTime = currentTime;
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {    	
        return false;
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	//Just let the lift keep holding the current motion magic
    	//setpoint after this command ends
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
