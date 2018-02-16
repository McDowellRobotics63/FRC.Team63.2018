package org.usfirst.frc.team63.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team63.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LiftSubsystem extends Subsystem {

	public TalonSRX liftMotor = new TalonSRX(RobotMap.LIFT);
	private double setpoint = 0;
	public enum Direction {
		UP, DOWN
	}
    public void initDefaultCommand() {
    	TalonConfig();
    	resetEncoder();
    }

    private void TalonConfig() {
    	liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.TIMOUT_MS);		
    	liftMotor.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);    	
    	liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.TIMOUT_MS);
    	liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.TIMOUT_MS);		
    	liftMotor.setSensorPhase(true);    	
    	liftMotor.setInverted(false);    	
    	liftMotor.setNeutralMode(NeutralMode.Brake);    	
    	liftMotor.configNominalOutputForward(RobotMap.MIN_FORCE_UP, RobotMap.TIMOUT_MS);    	
    	liftMotor.configNominalOutputReverse(RobotMap.MIN_FORCE_UP, RobotMap.TIMOUT_MS);    	
    	liftMotor.configPeakOutputForward(1.0, RobotMap.TIMOUT_MS);
    	liftMotor.configPeakOutputReverse(-1.0, RobotMap.TIMOUT_MS);
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
    
    public void setMotionMagicSetpoint(double setpoint) {
    	liftMotor.set(ControlMode.MotionMagic, setpoint);
    	//liftMotor.set(ControlMode.MotionMagic, inchesToUnits(setpoint));
    }
    
    public double getCurrentPosition()
    {
    	return liftMotor.getSelectedSensorPosition(0);
    }
    
    public double getCurrentSetpoint() {
    	return unitsToInches(liftMotor.getClosedLoopTarget(0));
    }
    
    public int getMotionMagicError() {
    	return liftMotor.getClosedLoopError(0);
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
    
    public void stop()
    {
    	liftMotor.set(ControlMode.PercentOutput, 0.0);
    }
    
	public void resetEncoder() {
		liftMotor.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);
		liftMotor.getSensorCollection().setQuadraturePosition(0, RobotMap.TIMOUT_MS);		
	}
    
	public double getLiftSpeed() {
//		return unitsToInches(liftMotor.getSelectedSensorVelocity(0));
		return liftMotor.getSelectedSensorVelocity(0);
	}
	
	public void setPercentOutput (double anything)
	{
    	liftMotor.set(ControlMode.PercentOutput, anything);
	}
    private static double unitsToInches(double units) {
        return units * RobotMap.LIFT_INCHES_PER_UNIT;
    }
    
    private static double inchesToUnits(double inches) {
    	return inches * RobotMap.LIFT_ENCODER_PPR / RobotMap.LIFT_INCHES_PER_REV; 
    }
}

