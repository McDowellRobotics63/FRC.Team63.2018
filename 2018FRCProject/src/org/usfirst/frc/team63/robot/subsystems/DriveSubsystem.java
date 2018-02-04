package org.usfirst.frc.team63.robot.subsystems;

import org.usfirst.frc.team63.robot.Instrum;
import org.usfirst.frc.team63.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSubsystem extends Subsystem {
	 private TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.DRIVELEFTMASTER); 
	 private TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.DRIVERIGHTMASTER); 
	 private TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.DRIVELEFTSLAVE); 
	 private TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.DRIVERIGHTSLAVE);
//	 private DifferentialDrive differentialDrive;
	 StringBuilder _sb = new StringBuilder();
	 
	 public DriveSubsystem() {
//		 differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
		 TalonConfig();
	 }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Arcade drive for use with PID control or other non-joystick driving
     * 
     * @param xSpeed    The robot's speed forward (positive) and back from -1.0 to 1.0.
     * @param zRotation The robot's rotation rate around the Z axis from -1.0 to 1.0. Clockwise/rightturn is
     *                  positive.
     */
    public void autoDrive(double xSpeed, double zRotation) {
//    	differentialDrive.arcadeDrive(xSpeed, zRotation, false);
    }
    
    /**
     * Arcade drive for use for teleop
     * 
     * @param xSpeed    The robot's speed forward (positive) and back from -1.0 to 1.0.
     * @param zRotation The robot's rotation rate around the Z axis from -1.0 to 1.0. Clockwise/rightturn is
     *                  positive.
     */
    public void teleDrive(double xSpeed, double zRotation) {
//    	differentialDrive.arcadeDrive(xSpeed, zRotation);
    }
    
    /**
     * 
     * @return Current encoder position on left gearbox shaft in inches
     */
    public double getLeftPosition() {
        return unitsToInches(leftMaster.getSelectedSensorPosition(0));
    }
    
    public void stop()
    {
    	leftMaster.set(ControlMode.PercentOutput, 0.0);
    	rightMaster.set(ControlMode.PercentOutput, 0.0);
    }
    
	/**
	 * 
	 * @return Current encoder position on right gearbox shaft in inches
	 */
    public double getRightPosition() {
        return -unitsToInches(rightMaster.getSelectedSensorPosition(0));
    }
    
    public void resetEncoders() {
    	leftMaster.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
    	rightMaster.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);

    	leftMaster.getSensorCollection().setQuadraturePosition(RobotMap.kVelocityControlSlot, RobotMap.kTimeoutMs);
    	rightMaster.getSensorCollection().setQuadraturePosition(RobotMap.kVelocityControlSlot, RobotMap.kTimeoutMs);
    }
    
    public void setMotionMagicLeft(double setpoint) {
    	System.out.println("Left setpoint: " + inchesToUnits(setpoint));
    	leftMaster.set(ControlMode.MotionMagic, inchesToUnits(setpoint));
    }
    
    public void setMotionMagicRight(double setpoint) {
    	System.out.println("Right setpoint: " + inchesToUnits(setpoint));
    	rightMaster.set(ControlMode.MotionMagic, inchesToUnits(setpoint));
    }
    
    public double getErrorLeft() {
    	return leftMaster.getClosedLoopError(0);
    }
    
    public double getErrorRight() {
    	return rightMaster.getClosedLoopError(0);
    }
    
    public void configGains(double f, double p, double i, double d) {
    	leftMaster.selectProfileSlot(0, 0);
		leftMaster.config_kF(0, f, RobotMap.kTimeoutMs);
		leftMaster.config_kP(0, p, RobotMap.kTimeoutMs);
		leftMaster.config_kI(0, i, RobotMap.kTimeoutMs);
		leftMaster.config_kD(0, d, RobotMap.kTimeoutMs);
		
		rightMaster.selectProfileSlot(0, 0);
		rightMaster.config_kF(0, f, RobotMap.kTimeoutMs);
		rightMaster.config_kP(0, p, RobotMap.kTimeoutMs);
		rightMaster.config_kI(0, i, RobotMap.kTimeoutMs);
		rightMaster.config_kD(0, d, RobotMap.kTimeoutMs);
    }
    
    private static double unitsToInches(double units) {
        return units * RobotMap.kDriveWheelCircumference / RobotMap.kDriveEncoderFactor;
    }
    
    private static double inchesToUnits(double inches) {
    	return inches * RobotMap.kDriveEncoderFactor / RobotMap.kDriveWheelCircumference; 
    }
    
    /*Called in constructor to ensure correct drive talon settings*/
    private void TalonConfig() {
    	leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.kTimeoutMs);
    	rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.kTimeoutMs);
		
    	leftMaster.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
    	rightMaster.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
    	
    	leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
    	leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
    	
    	rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
    	rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
		
    	leftMaster.setSensorPhase(true);
    	rightMaster.setSensorPhase(true);
    	
    	leftMaster.setInverted(false);
    	leftSlave.setInverted(false);
    	
    	rightMaster.setInverted(true);
    	rightSlave.setInverted(true);
    	
    	//Makes talons force zero voltage across when zero output to resist motion
    	leftMaster.setNeutralMode(NeutralMode.Brake);
    	leftSlave.setNeutralMode(NeutralMode.Brake);
    	rightMaster.setNeutralMode(NeutralMode.Brake);
    	rightSlave.setNeutralMode(NeutralMode.Brake);
    	
    	leftSlave.set(ControlMode.Follower, RobotMap.DRIVELEFTMASTER);
    	rightSlave.set(ControlMode.Follower, RobotMap.DRIVERIGHTMASTER);
    	
    	//Closed Loop Voltage Limits
    	leftMaster.configNominalOutputForward(0.0, RobotMap.kTimeoutMs);
    	rightMaster.configNominalOutputForward(0.0, RobotMap.kTimeoutMs);
    	
    	leftMaster.configNominalOutputReverse(-0.0, RobotMap.kTimeoutMs);
    	rightMaster.configNominalOutputReverse(-0.0, RobotMap.kTimeoutMs);
    	
    	leftMaster.configPeakOutputForward(1.0, RobotMap.kTimeoutMs);
    	rightMaster.configPeakOutputForward(1.0, RobotMap.kTimeoutMs);
    	
    	leftMaster.configPeakOutputReverse(-1.0, RobotMap.kTimeoutMs);
    	rightMaster.configPeakOutputReverse(-1.0, RobotMap.kTimeoutMs);
    	
    	leftMaster.configMotionCruiseVelocity(1000, RobotMap.kTimeoutMs);
    	leftMaster.configMotionAcceleration(400, RobotMap.kTimeoutMs);
		
    	rightMaster.configMotionCruiseVelocity(1000, RobotMap.kTimeoutMs);
    	rightMaster.configMotionAcceleration(400, RobotMap.kTimeoutMs);
    }
    
    public void DebugMotionMagic()
    {
    	double motorOutput = rightMaster.getMotorOutputPercent();
		/* prepare line to print */
		_sb.append("\tOut%:");
		_sb.append(motorOutput);
		_sb.append("\tVel:");
		_sb.append(rightMaster.getSelectedSensorVelocity(0));
		
		_sb.append("\terr:");
		_sb.append(rightMaster.getClosedLoopError(0));
		
    	Instrum.Process(rightMaster, _sb);
    }
}