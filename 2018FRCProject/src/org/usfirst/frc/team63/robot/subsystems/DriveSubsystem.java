package org.usfirst.frc.team63.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team63.robot.RobotMap;
import org.usfirst.frc.team63.robot.commands_drive.TeleopDriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSubsystem extends Subsystem {
	private WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.DRIVELEFTMASTER); 
	private WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.DRIVERIGHTMASTER); 
	private WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.DRIVELEFTSLAVE); 
	private WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.DRIVERIGHTSLAVE);
	private Solenoid shifter_high = new Solenoid(RobotMap.PCM2_CANID, RobotMap.SHIFTER_HIGH);
	private Solenoid shifter_low = new Solenoid(RobotMap.PCM1_CANID, RobotMap.SHIFTER_LOW);
	 
	private DifferentialDrive differentialDrive;
	 
	public enum Shift
	{
		HIGH, LOW
	}
	 
	 public DriveSubsystem() {
		 differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
		 TalonConfig();
		 shiftLow();
	 }
	
	 public void initDefaultCommand() {
	    setDefaultCommand(new TeleopDriveCommand());
	 }

	/**
	 * Arcade drive for use for teleop
	 * 
	 * @param xSpeed    The robot's speed forward (positive) and back from -1.0 to 1.0.
	 * @param zRotation The robot's rotation rate around the Z axis from -1.0 to 1.0. Clockwise/rightturn is
	 *                  positive.
	 */
	public void teleDrive(double xSpeed, double zRotation) {
		differentialDrive.arcadeDrive(xSpeed, zRotation);
	}
	
	/**
	 * 
	 * @return Current encoder position on left gearbox shaft in inches
	 */
	public double getLeftPosition() {
	    return unitsToInches(leftMaster.getSelectedSensorPosition(0));
	}
	
	/**
	 * 
	 * @return Current encoder position on right gearbox shaft in inches
	 */
	public double getRightPosition() {
	    return -unitsToInches(rightMaster.getSelectedSensorPosition(0));
	}
	
    public double getErrorLeft() {
    	return leftMaster.getClosedLoopError(0);
    }
    
    public double getErrorRight() {
    	return rightMaster.getClosedLoopError(0);
    }
	
	public void resetEncoders() {
		leftMaster.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);
		rightMaster.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);
	
		leftMaster.getSensorCollection().setQuadraturePosition(RobotMap.VELOCITY_CONTROL_SLOT, RobotMap.TIMOUT_MS);
		rightMaster.getSensorCollection().setQuadraturePosition(RobotMap.VELOCITY_CONTROL_SLOT, RobotMap.TIMOUT_MS);
	}
	
    public void setMotionMagicLeft(double setpoint) {
    	leftMaster.set(ControlMode.MotionMagic, inchesToUnits(setpoint));
    }
    
    public void setMotionMagicRight(double setpoint) {
    	rightMaster.set(ControlMode.MotionMagic, inchesToUnits(setpoint));
    }
	
	public void shiftHigh()
	{
		shifter_high.set(true);
		shifter_low.set(false);
	}
	
	public void shiftLow() 
	{
		shifter_high.set(false);
		shifter_low.set(true);
	}
	
	/**
	 * Sets output of master talons to 0V
	 */
	public void stop()
	{
		leftMaster.set(ControlMode.PercentOutput, 0.0);
		rightMaster.set(ControlMode.PercentOutput, 0.0);
	}
	
	/*Units are native units of encoder*/
	private static double unitsToInches(int units) {
	    return units * RobotMap.DRIVE_WHEEL_CIRCUMFERENCE / RobotMap.DRIVE_ENCODER_PPR;
	}
	private static int inchesToUnits(double inches) {
		return (int)(inches * RobotMap.DRIVE_ENCODER_PPR / RobotMap.DRIVE_WHEEL_CIRCUMFERENCE); 
	}
	
	public void autoInit() {
		differentialDrive.setSafetyEnabled(false);
	}
	
	public void teleInit() {
		differentialDrive.setSafetyEnabled(true);
	}
	
	/*Called in constructor to ensure correct drive talon settings*/
	private void TalonConfig() {
    	leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.TIMOUT_MS);
    	rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.TIMOUT_MS);
		
    	leftMaster.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);
    	rightMaster.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);
    	
    	leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.TIMOUT_MS);
    	leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.TIMOUT_MS);
    	
    	rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.TIMOUT_MS);
    	rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.TIMOUT_MS);
		
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
    	leftMaster.configNominalOutputForward(0.0, RobotMap.TIMOUT_MS);
    	rightMaster.configNominalOutputForward(0.0, RobotMap.TIMOUT_MS);
    	
    	leftMaster.configNominalOutputReverse(-0.0, RobotMap.TIMOUT_MS);
    	rightMaster.configNominalOutputReverse(-0.0, RobotMap.TIMOUT_MS);
    	
    	leftMaster.configPeakOutputForward(1.0, RobotMap.TIMOUT_MS);
    	rightMaster.configPeakOutputForward(1.0, RobotMap.TIMOUT_MS);
    	
    	leftMaster.configPeakOutputReverse(-1.0, RobotMap.TIMOUT_MS);
    	rightMaster.configPeakOutputReverse(-1.0, RobotMap.TIMOUT_MS);
	}
	
    public void configGains(double f, double p, double i, double d, int izone, int cruise, int accel) {
    	leftMaster.selectProfileSlot(0, 0);
		leftMaster.config_kF(0, f, RobotMap.TIMOUT_MS);
		leftMaster.config_kP(0, p, RobotMap.TIMOUT_MS);
		leftMaster.config_kI(0, i, RobotMap.TIMOUT_MS);
		leftMaster.config_kD(0, d, RobotMap.TIMOUT_MS);
		leftMaster.config_IntegralZone(0, izone, RobotMap.TIMOUT_MS);
		
		rightMaster.selectProfileSlot(0, 0);
		rightMaster.config_kF(0, f, RobotMap.TIMOUT_MS);
		rightMaster.config_kP(0, p, RobotMap.TIMOUT_MS);
		rightMaster.config_kI(0, i, RobotMap.TIMOUT_MS);
		rightMaster.config_kD(0, d, RobotMap.TIMOUT_MS);
		rightMaster.config_IntegralZone(0, izone, RobotMap.TIMOUT_MS);
		
    	leftMaster.configMotionCruiseVelocity(cruise, RobotMap.TIMOUT_MS);
    	leftMaster.configMotionAcceleration(accel, RobotMap.TIMOUT_MS);
		
    	rightMaster.configMotionCruiseVelocity(cruise, RobotMap.TIMOUT_MS);
    	rightMaster.configMotionAcceleration(accel, RobotMap.TIMOUT_MS);
    }
    
    public List<Double> DebugMotionMagicRight()
    {
		List<Double> resulterino = new ArrayList<Double>();
		resulterino.add((double) rightMaster.getSelectedSensorPosition(0));
		resulterino.add((double) rightMaster.getSelectedSensorVelocity(0));
		resulterino.add((double) rightMaster.getActiveTrajectoryPosition());
		resulterino.add((double) rightMaster.getActiveTrajectoryVelocity());
		resulterino.add(rightMaster.getMotorOutputPercent());
		resulterino.add((double) rightMaster.getClosedLoopError(0));
		return resulterino;
	}
    
    public List<Double> DebugMotionMagicLeft()
    {
		List<Double> resulterino = new ArrayList<Double>();
		resulterino.add((double) leftMaster.getSelectedSensorPosition(0));
		resulterino.add((double) leftMaster.getSelectedSensorVelocity(0));
		resulterino.add((double) leftMaster.getActiveTrajectoryPosition());
		resulterino.add((double) leftMaster.getActiveTrajectoryVelocity());
		resulterino.add(leftMaster.getMotorOutputPercent());
		resulterino.add((double) leftMaster.getClosedLoopError(0));
		return resulterino;
	}
}