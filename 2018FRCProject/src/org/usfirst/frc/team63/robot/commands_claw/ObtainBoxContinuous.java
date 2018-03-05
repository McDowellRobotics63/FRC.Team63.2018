package org.usfirst.frc.team63.robot.commands_claw;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ObtainBoxContinuous extends Command {

    public ObtainBoxContinuous() {
        requires(Robot.claw);
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
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
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
