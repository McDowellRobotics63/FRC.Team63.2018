package org.usfirst.frc.team63.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team63.robot.RobotMap;
import org.usfirst.frc.team63.robot.commands_lift.LiftAdjustCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftSubsystem extends Subsystem {

	public enum Direction {
		UP, DOWN
	}
	
	public TalonSRX liftMotor = new TalonSRX(RobotMap.LIFT);
	private Direction appliedFeedFoward = Direction.UP;

	public LiftSubsystem() {
		initSubsystem();
	}
	
	public void initSubsystem()
	{
    	TalonConfig();
    	resetEncoder();
    	setMotionMagicSetpoint(0);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new LiftAdjustCommand());
    }

    private void TalonConfig() {
    	liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.TIMOUT_MS);		
    	liftMotor.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);    	
    	liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.TIMOUT_MS);
    	liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.TIMOUT_MS);		
    	liftMotor.setSensorPhase(true);    	
    	liftMotor.setInverted(false);    	
    	liftMotor.setNeutralMode(NeutralMode.Brake);    	
    	liftMotor.configNominalOutputForward(0, RobotMap.TIMOUT_MS);    	
    	liftMotor.configNominalOutputReverse(0, RobotMap.TIMOUT_MS);    	
    	liftMotor.configPeakOutputForward(1.0, RobotMap.TIMOUT_MS);
    	liftMotor.configPeakOutputReverse(-1.0, RobotMap.TIMOUT_MS);
    	liftMotor.setNeutralMode(NeutralMode.Brake);
    }
    
    public void configGains(double f, double p, double i, double d, int izone, int accel, int cruise) {
    	liftMotor.selectProfileSlot(0, 0);
    	liftMotor.config_kF(0, f, RobotMap.TIMOUT_MS);
    	liftMotor.config_kP(0, p, RobotMap.TIMOUT_MS);
    	liftMotor.config_kI(0, i, RobotMap.TIMOUT_MS);
    	liftMotor.config_kD(0, d, RobotMap.TIMOUT_MS);
    	liftMotor.config_IntegralZone(0, izone, RobotMap.TIMOUT_MS);
    	liftMotor.configMotionCruiseVelocity(cruise, RobotMap.TIMOUT_MS);
    	liftMotor.configMotionAcceleration(accel, RobotMap.TIMOUT_MS);
    }
    
    public void resetIntegrator() {
    	liftMotor.setIntegralAccumulator(0, 0, RobotMap.TIMOUT_MS);
    }
    
    public double getCurrentSetpoint() {
    	return unitsToInches(liftMotor.getClosedLoopTarget(0));
    }
    
    public double getCurrentPosition()
    {
    	return unitsToInches(liftMotor.getSelectedSensorPosition(0));
    }
    
	public double getLiftSpeed() {
		return unitsToInches(liftMotor.getSelectedSensorVelocity(0));
	}
	
    public void setMotionMagicSetpoint(double setpoint) {
    	int setpoint_units = inchesToUnits(setpoint);
    	
    	if (setpoint_units < liftMotor.getClosedLoopTarget(0) && appliedFeedFoward == Direction.UP)
    	{
    		liftMotor.config_kF(0, SmartDashboard.getNumber("kF_lift_down", 0.0), RobotMap.TIMOUT_MS);
    		appliedFeedFoward = Direction.DOWN;
    	}
    	else if (setpoint_units > liftMotor.getClosedLoopTarget(0) && appliedFeedFoward == Direction.DOWN)
    	{
    		liftMotor.config_kF(0, SmartDashboard.getNumber("kF_lift_up", 0.0), RobotMap.TIMOUT_MS);
    		appliedFeedFoward = Direction.UP;
    	}
    		
    	setpoint_units = Math.max(0, Math.min(setpoint_units, RobotMap.MAX_LIFT_DISPLACEMENT_UNITS));
    	
    	SmartDashboard.putNumber("setpoint_units", setpoint_units);
    	SmartDashboard.putNumber("setpoint_inches", setpoint);
    	
    	if(isSensorConnected())
    	{
    		liftMotor.set(ControlMode.MotionMagic, setpoint_units);
    	}
    	else
    	{
    		stop();
    	}
    }
                                                     
    public boolean isMotionMagicNearTarget() {
    	return (liftMotor.getActiveTrajectoryPosition() == liftMotor.getClosedLoopTarget(0)) &&
    			Math.abs(liftMotor.getClosedLoopError(0)) < 10;
    }
    
    public List<Double> DebugMotionMagic()
    {
		List<Double> resulterino = new ArrayList<Double>();
		resulterino.add((double) liftMotor.getSelectedSensorPosition(0));
		resulterino.add((double) liftMotor.getSelectedSensorVelocity(0));
		resulterino.add((double) liftMotor.getActiveTrajectoryPosition());
		resulterino.add((double) liftMotor.getActiveTrajectoryVelocity());
		resulterino.add(liftMotor.getMotorOutputPercent());
		resulterino.add((double) liftMotor.getClosedLoopError(0));
		return resulterino;
    }
    
    public void hold()
    {
    	liftMotor.set(ControlMode.MotionMagic, liftMotor.getSelectedSensorPosition(0));
    }
    
    public void stop()
    {
    	setPercentOutput(0.0);
    }
    
	public void setPercentOutput (double anything)
	{
    	liftMotor.set(ControlMode.PercentOutput, anything);
	}
	
	public boolean isSensorConnected() {
		return liftMotor.getSensorCollection().getPulseWidthRiseToRiseUs() != 0;
	}
    
	public void resetEncoder() {
		liftMotor.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);
		liftMotor.getSensorCollection().setQuadraturePosition(0, RobotMap.TIMOUT_MS);		
	}
	
    private static double unitsToInches(double units) {
        return units * RobotMap.LIFT_INCHES_PER_UNIT;
    }
    
    public static int inchesToUnits(double inches) {
    	return (int)(inches / RobotMap.LIFT_INCHES_PER_UNIT); 
    }
}
