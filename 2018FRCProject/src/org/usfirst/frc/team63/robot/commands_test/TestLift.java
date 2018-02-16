package org.usfirst.frc.team63.robot.commands_test;

import org.usfirst.frc.team63.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * to determine RobotMap.MIN_FORCE_UP
 * hold back to run motor
 * press LB if motor is too scott bayle 
 * press RB if motor is too luke leiter 
 */
public class TestLift extends Command {

   private double lowBound;
   private double highBound = 1;
   private double current= .005;
   private boolean go = false;
   private boolean hasOvershot = false;
   private boolean leftWasPressed = false;
   private boolean rightWasPressed = false;
   
	public TestLift() {
	   requires(Robot.lift);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift.stop();
    	lowBound = current;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	//runs only if back is pressed
    	go = Robot.m_oi.controller1_back.get();
    	if (go)
    	{
    		Robot.lift.setPercentOutput(current);
    	}
    	else Robot.lift.stop();
    	
    	//doubles current value until user deems too high
    	if(!hasOvershot)
    	{
    		if (left()) {
    			current=Math.min(0.5, current*2);
    			lowBound = current;
    		}
    		else if (right()) {
    			hasOvershot = true;
    			highBound = current;
        		current = (highBound+lowBound)/2;
    		}
    	}
    	//main binary search bit
    	else if (left())
    	{
    		lowBound = current;
    		current = (lowBound+highBound)/2;
    	}
    	else if (right())
    	{
    		highBound = current;
    		current = (highBound+lowBound)/2;
    	}
    	
    	leftWasPressed = Robot.m_oi.controller1_LB.get();
    	rightWasPressed = Robot.m_oi.controller1_RB.get();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return Robot.m_oi.controller1_start.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.stop();
    	System.out.println(current);    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lift.stop();
    	System.out.println(current);    	
    }
    
    //detects leading edge for left bumper
    private boolean left() {
    	return Robot.m_oi.controller1_LB.get() && !leftWasPressed;
    }
    
  //detects leading edge for right bumper
    private boolean right() {
    	return Robot.m_oi.controller1_RB.get() && !rightWasPressed;
    }
}
