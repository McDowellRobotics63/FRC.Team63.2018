package org.usfirst.frc.team63.robot.commands_claw;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ObtainBox extends Command {

    public ObtainBox() {
        requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.claw.close();
    	Robot.claw.setSpeed(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.claw.boxIsReallyClose();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.claw.close();
    	Robot.claw.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
