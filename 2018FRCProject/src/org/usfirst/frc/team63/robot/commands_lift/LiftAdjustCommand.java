package org.usfirst.frc.team63.robot.commands_lift;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftAdjustCommand extends Command {
	private long lastTime = 0;
	
    public LiftAdjustCommand() {
        requires(Robot.lift);
        
        //This will be the default command for the lift.
        //Operator can always be adjust the lift using analog stick.
        //If another command comes in that wants to set the setpoint to
        //a pre-set value (top, bottom, box height) then let it interrupt to do so.
        setInterruptible(true);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	lastTime = System.currentTimeMillis();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	long currentTime = System.currentTimeMillis();
    	double timeElapsedSeconds = (currentTime - lastTime) / 1000;
    	  	
    	//Let the operator move the setpoint by 0-100% of the max lift adjust speed
    	Robot.lift.setMotionMagicSetpoint(
    			Robot.lift.getCurrentPosition() + 
    			-Robot.m_oi.controller1.getRawAxis(RobotMap.XBOX_RIGHT_Y_AXIS) * 
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
