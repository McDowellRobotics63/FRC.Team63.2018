package org.usfirst.frc.team63.robot.commands_claw;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawAndLift extends Command {

    public ClawAndLift() {
        requires(Robot.claw);
        requires(Robot.lift);
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.claw.close();
    	Robot.claw.setSpeed(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.claw.boxIsReallyClose()) 
    	{
    		if(Robot.lift.getCurrentPosition() < RobotMap.BOX_HEIGHT_INCHES)
    		{
    			Robot.lift.setMotionMagicSetpoint(RobotMap.BOX_HEIGHT_INCHES);
    		}
        	Robot.claw.setSpeed(0);
    	} else {
    		Robot.claw.setSpeed(RobotMap.BOX_IN_SPEED);
    		if (Robot.claw.boxIsClose())
    		{
    			Robot.claw.close();
    		}
    		else
    		{
    			Robot.claw.open();
    		}
    	}
    	
    	if(Robot.lift.isMotionMagicNearTarget())
    	{
    		if(Robot.lift.getCurrentPosition() > 60)
    		{
    			Robot.lift.stop();
    		}
    		else
    		{
    			Robot.lift.hold();
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("WTFahhhhhhh!?");
    	Robot.claw.close();
    	Robot.claw.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.claw.close();
    	Robot.claw.setSpeed(0);
    }
}
