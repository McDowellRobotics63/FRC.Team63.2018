package org.usfirst.frc.team63.robot.commands_claw;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BoxObtainFoSho extends Command {

    public BoxObtainFoSho() {
        requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.claw.clawToggle(false);
    	Robot.claw.clawSetSpeed(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.claw.clawSetSpeed(RobotMap.BOX_IN_SPEED);
		if (Robot.claw.boxIsClose())
		{
			Robot.claw.clawToggle(false);
		}
		else
		{
			Robot.claw.clawToggle(true);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.claw.boxIsReallyClose();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.claw.clawToggle(false);
    	Robot.claw.clawSetSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
