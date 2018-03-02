package org.usfirst.frc.team63.robot.commands_lift;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftAdjustDownCommand extends Command {
	
    public LiftAdjustDownCommand() {
        requires(Robot.lift);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.lift.setMotionMagicSetpoint(Robot.lift.getCurrentSetpoint() - 
			RobotMap.MAX_LIFT_ADJUST_SPEED * 0.02);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {    	
        return true;
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

