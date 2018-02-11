package org.usfirst.frc.team63.robot.subsystems;

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

    public void initDefaultCommand() {
    	TalonConfig();
    }

    private void TalonConfig() {
    	liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);		
    	liftMotor.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);    	
    	liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
    	liftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);		
    	liftMotor.setSensorPhase(true);    	
    	liftMotor.setInverted(false);    	
    	liftMotor.setNeutralMode(NeutralMode.Brake);    	
    	liftMotor.configNominalOutputForward(0.0, RobotMap.kTimeoutMs);    	
    	liftMotor.configNominalOutputReverse(-0.0, RobotMap.kTimeoutMs);    	
    	liftMotor.configPeakOutputForward(1.0, RobotMap.kTimeoutMs);
    	liftMotor.configPeakOutputReverse(-1.0, RobotMap.kTimeoutMs);
    	liftMotor.configMotionCruiseVelocity(312, RobotMap.kTimeoutMs);
    	liftMotor.configMotionAcceleration(312, RobotMap.kTimeoutMs);
    }
}

