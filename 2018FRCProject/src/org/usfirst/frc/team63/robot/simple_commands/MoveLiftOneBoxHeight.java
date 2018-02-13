package org.usfirst.frc.team63.robot.simple_commands;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team63.robot.subsystems.LiftSubsystem.Direction;

/**
 *
 */
public class MoveLiftOneBoxHeight extends Command {
	
	private Direction direction;
	private double tolerance; //native encoder units
	private double setpoint;
	
    public MoveLiftOneBoxHeight(Direction d) {
    	direction = d;
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double currentPosition = Robot.lift.getCurrentPosition();
    	if(direction == Direction.UP)
    	{
    		setpoint=currentPosition+RobotMap.BOX_HEIGHT;
    	}
    	else if(direction == Direction.DOWN)
    	{
    		setpoint=currentPosition-RobotMap.BOX_HEIGHT;
    	}
    	else System.out.println("It was at this moment that the Robot realized, Jake f***ed up");
    	Robot.lift.setMotionMagicSetpoint(setpoint);
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.lift.getCurrentPosition()-setpoint)<tolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
