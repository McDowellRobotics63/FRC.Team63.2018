package org.usfirst.frc.team63.robot.commands_drive;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopDriveLowCommand extends Command {

    public TeleopDriveLowCommand() {
        requires(Robot.drive);
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double axis = Robot.m_oi.controller1.getRawAxis(RobotMap.XBOX_LEFT_X_AXIS);
    	if(axis > -0.2 && axis < 0.2)
    	{
    		axis = 0;
    	}
    	
    	axis = Math.signum(axis) * ((Math.abs(axis) - 0.2) / 0.8);
    	
    	Robot.drive.shiftLow();
    	Robot.drive.teleDrive(
    			-Robot.m_oi.controller1.getRawAxis(RobotMap.XBOX_LEFT_Y_AXIS),
    			axis
    			);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.stop();
    }
}
