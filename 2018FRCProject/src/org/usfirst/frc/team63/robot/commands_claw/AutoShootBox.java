package org.usfirst.frc.team63.robot.commands_claw;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoShootBox extends Command {
	private final Timer totalTimer;
	
    public AutoShootBox() {
    	requires(Robot.claw);
    	totalTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	totalTimer.reset();
    	totalTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.claw.setSpeed(RobotMap.BOX_OUT_SPEED);
    	Robot.claw.close();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return totalTimer.get() > 0.25;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
