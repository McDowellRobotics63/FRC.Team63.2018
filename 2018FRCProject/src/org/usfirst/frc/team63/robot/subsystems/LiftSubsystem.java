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

	private TalonSRX liftMotor = new TalonSRX(RobotMap.LIFT);
	private double setpoint = 0;
    public void initDefaultCommand() {
    	TalonConfig();
    	resetEncoder();
    }

    private void TalonConfig() {
    	liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);		
    	liftMotor.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);    	
    	liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
    	liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);		
    	liftMotor.setSensorPhase(true);    	
    	liftMotor.setInverted(false);    	
    	liftMotor.setNeutralMode(NeutralMode.Brake);    	
    	liftMotor.configNominalOutputForward(RobotMap.MIN_FORCE_UP, RobotMap.kTimeoutMs);    	
    	liftMotor.configNominalOutputReverse(-0.0, RobotMap.kTimeoutMs);    	
    	liftMotor.configPeakOutputForward(1.0, RobotMap.kTimeoutMs);
    	liftMotor.configPeakOutputReverse(-1.0, RobotMap.kTimeoutMs);
    }
    public void configGains(double f, double p, double i, double d, int izone, int accel, int cruise) {
    	liftMotor.selectProfileSlot(0, 0);
    	liftMotor.config_kF(0, f, RobotMap.kTimeoutMs);
    	liftMotor.config_kP(0, p, RobotMap.kTimeoutMs);
    	liftMotor.config_kI(0, i, RobotMap.kTimeoutMs);
    	liftMotor.config_kD(0, d, RobotMap.kTimeoutMs);
    	liftMotor.config_IntegralZone(0, izone, RobotMap.kTimeoutMs);
    	liftMotor.configMotionCruiseVelocity(cruise, RobotMap.kTimeoutMs);
    	liftMotor.configMotionAcceleration(accel, RobotMap.kTimeoutMs);
    }
    
    public void setMotionMagicSetpoint(double setpoint) {
    	liftMotor.set(ControlMode.MotionMagic, setpoint);
    	//liftMotor.set(ControlMode.MotionMagic, inchesToUnits(setpoint));
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
		liftMotor.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		liftMotor.getSensorCollection().setQuadraturePosition(RobotMap.kVelocityControlSlot, RobotMap.kTimeoutMs);		
	}
    
	public double getLiftSpeed() {
		return unitsToInches(liftMotor.getSelectedSensorVelocity(0));
	}
	
	public void setPercentOutput (double anything)
	{
    	liftMotor.set(ControlMode.PercentOutput, anything);
	}
    private static double unitsToInches(double units) {
        return units * RobotMap.kLiftInchesPerRev / RobotMap.kLiftEncoderPPR;
    }
    
    private static double inchesToUnits(double inches) {
    	return inches * RobotMap.kLiftEncoderPPR / RobotMap.kLiftInchesPerRev; 
    }
}

