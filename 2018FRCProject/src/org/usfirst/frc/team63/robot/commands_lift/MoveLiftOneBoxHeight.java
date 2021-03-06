package org.usfirst.frc.team63.robot.commands_lift;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team63.robot.subsystems.LiftSubsystem.Direction;

/**
 *
 */
public class MoveLiftOneBoxHeight extends Command {
	
	private Direction direction;
	private double setpoint;
	private final Timer totalTimer;
	
    public MoveLiftOneBoxHeight(Direction d) {
    	requires(Robot.lift);
    	direction = d;        
        totalTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	
    	totalTimer.reset();
    	totalTimer.start();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentPosition = Robot.lift.getCurrentSetpoint();
    	if(direction == Direction.UP)
    	{
    		setpoint=currentPosition+RobotMap.BOX_HEIGHT_INCHES;
    	}
    	else if(direction == Direction.DOWN)
    	{
    		setpoint=currentPosition-RobotMap.BOX_HEIGHT_INCHES;
    	}
    	else System.out.println("It was at this moment that the Robot realized, Jake f***ed up");
    	Robot.lift.setMotionMagicSetpoint(setpoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       // return Robot.lift.isMotionMagicNearTarget() || totalTimer.get() > 5.0;
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
