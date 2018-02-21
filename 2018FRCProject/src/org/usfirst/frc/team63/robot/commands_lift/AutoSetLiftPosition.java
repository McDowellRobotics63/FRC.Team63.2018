package org.usfirst.frc.team63.robot.commands_lift;

import java.util.Arrays;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoSetLiftPosition extends Command {
	
	private double m_setpoint;
	private final Timer totalTimer;

    public AutoSetLiftPosition(double setpoint) {
        requires(Robot.lift);
        //requires(Robot.debug);
        
        m_setpoint = LiftSubsystem.inchesToUnits(setpoint);
        totalTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.debug.Start("AutoSetLiftPosition", Arrays.asList("Sensor_Position", "Sensor_Velocity",
    			"Trajectory_Position", "Trajectory_Velocity", "Motor_Output", "Error")); 
    	totalTimer.reset();
    	totalTimer.start();

    	Robot.lift.setMotionMagicSetpoint(m_setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.debug.Update(Robot.lift.DebugMotionMagic());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (totalTimer.get() > 1.0 && Robot.lift.isMotionMagicNearTarget()) || totalTimer.get() > 5.0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Just let the lift keep holding the current motion magic
    	//setpoint after this command ends
    	Robot.debug.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lift.hold();
    	Robot.debug.Stop();
    }  
}
