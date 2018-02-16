package org.usfirst.frc.team63.robot.commands_test;

import org.usfirst.frc.team63.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * to determine RobotMap.MIN_FORCE_UP
 * hold back to run motor
 * press LB if motor is too scott bayle 
 * press RB if motor is too luke leiter 
 */
public class TestLift2 extends Command {

   private boolean go = false;
  
	public TestLift2() {
	   requires(Robot.lift);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	//runs only if back is pressed
    	go = Robot.m_oi.controller1_back.get();
    	if (go)
    	{
    		Robot.lift.setPercentOutput(SmartDashboard.getNumber("lift_cmd", 0));
    	}
    	else Robot.lift.stop();
    	
    	SmartDashboard.putNumber("lift_velocity2", Robot.lift.getLiftSpeed());
    	SmartDashboard.putNumber("lift_position", Robot.lift.getCurrentPosition());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return Robot.m_oi.controller1_start.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.stop();
    	System.out.println("end");    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lift.stop();
    	System.out.println("interrupted");    	
    }
}
