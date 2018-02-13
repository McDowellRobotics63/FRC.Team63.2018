package org.usfirst.frc.team63.robot.simple_commands;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLiftOneBoxHeight extends Command {
	
	
	
    public MoveLiftOneBoxHeight(Robot.lift.Direction Direction) {
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double currentPosition = Robot.lift.getCurrentPosition();
    	if()
    	{
    		Robot.lift.setMotionMagicSetpoint(currentPosition + RobotMap.BOX_HEIGHT);
    	}
    	else if()
    	{
    		Robot.lift.setMotionMagicSetpoint(currentPosition - RobotMap.BOX_HEIGHT);
    	}
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
