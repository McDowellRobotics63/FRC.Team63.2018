package org.usfirst.frc.team63.robot.commands_lift;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team63.robot.subsystems.LiftSubsystem.Direction;

/**
 *
 */
public class MoveLiftMaxHeight extends Command {
	private final Timer totalTimer;
	
    public MoveLiftMaxHeight() {
    	requires(Robot.lift);        
        totalTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift.setMotionMagicSetpoint(RobotMap.MAX_LIFT_DISPLACEMENT_INCHES);
    	
    	totalTimer.reset();
    	totalTimer.start();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.lift.isMotionMagicNearTarget() || totalTimer.get() > 5.0;
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
