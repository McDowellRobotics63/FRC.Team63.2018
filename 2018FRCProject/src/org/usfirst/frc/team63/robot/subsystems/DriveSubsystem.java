package org.usfirst.frc.team63.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team63.robot.Robot;
import org.usfirst.frc.team63.robot.RobotMap;
import org.usfirst.frc.team63.robot.commands_drive.TeleopDriveLowCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSubsystem extends Subsystem {
	private WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.DRIVELEFTMASTER); 
	private WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.DRIVERIGHTMASTER); 
	private WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.DRIVELEFTSLAVE); 
	private WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.DRIVERIGHTSLAVE);
	private Solenoid shifter_high = new Solenoid(RobotMap.PCM1_CANID, RobotMap.SHIFTER_HIGH);
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
	    //setDefaultCommand(new TeleopDriveLowCommand());
	 }
	 
//	 public void autobalance()
//	 {
//		 double xAxisRate = Robot.m_oi.controller1.getX();
//		 double yAxisRate = Robot.m_oi.controller1.getY();
//		 double pitchAngleDegrees = ahrs.getPitch();
//		 double rollAngleDegrees = ahrs.getRoll();
//		 
//	 }

	/**
	 * Arcade drive for use for teleop
	 * 
	 * @param xSpeed    The robot's speed forward (positive) and back from -1.0 to 1.0.
	 * @param zRotation The robot's rotation rate around the Z axis from -1.0 to 1.0. Clockwise/rightturn is
	 *                  positive.
	 */
	public void teleDrive(double xSpeed, double zRotation) {
		if(Robot.m_oi.controller1.B().get())
		{
			double scale = SmartDashboard.getNumber("creep_mult", 0.3);
			xSpeed = xSpeed * scale;
			zRotation = zRotation * scale;
		}
		differentialDrive.arcadeDrive(xSpeed, zRotation, false);
	}
	
	/**
	 * 
	 * @return Current encoder position on left gearbox shaft in inches
	 */
	public double getLeftPosition() {
	    return leftMaster.getSelectedSensorPosition(0);
	}
	
	/**
	 * 
	 * @return Current encoder position on right gearbox shaft in inches
	 */
	public double getRightPosition() {
	    return rightMaster.getSelectedSensorPosition(0);
	}
	
	public double getRightSpeed() {
	    return rightMaster.getSelectedSensorVelocity(0);
	}
	
	public double getLeftSpeed() {
	    return unitsToInches(leftMaster.getSelectedSensorVelocity(0));
	}
	
	
    public double getErrorLeft() {
    	return leftMaster.getClosedLoopError(0);
    }
    
    public double getErrorRight() {
    	return rightMaster.getClosedLoopError(0);
    }
	
	public boolean isLeftSensorConnected() {
		return leftMaster.getSensorCollection().getPulseWidthRiseToRiseUs() != 0;
	}
	
	public boolean isRightSensorConnected() {
		return rightMaster.getSensorCollection().getPulseWidthRiseToRiseUs() != 0;
	}
	
	public void resetEncoders() {
		leftMaster.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);
		rightMaster.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);
	
		leftMaster.getSensorCollection().setQuadraturePosition(0, RobotMap.TIMOUT_MS);
		rightMaster.getSensorCollection().setQuadraturePosition(0, RobotMap.TIMOUT_MS);
	}
	
    public void setMotionMagicLeft(double setpoint) {
    	SmartDashboard.putNumber("setpoint_left_units", inchesToUnits(setpoint));
    	leftMaster.set(ControlMode.MotionMagic, inchesToUnits(setpoint));
    }
    
    public void setMotionMagicRight(double setpoint) {
    	rightMaster.set(ControlMode.MotionMagic, inchesToUnits(-setpoint));
    }
	
    public boolean isMotionMagicNearTarget() {
    	return (leftMaster.getActiveTrajectoryPosition() == leftMaster.getClosedLoopTarget(0)) &&
    		   (rightMaster.getActiveTrajectoryPosition() == rightMaster.getClosedLoopTarget(0)) &&
    			Math.abs(leftMaster.getClosedLoopError(0)) < 300 &&
    			Math.abs(rightMaster.getClosedLoopError(0)) < 300;
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
		resetEncoders();
    	leftMaster.setNeutralMode(NeutralMode.Brake);
    	leftSlave.setNeutralMode(NeutralMode.Brake);
    	rightMaster.setNeutralMode(NeutralMode.Brake);
    	rightSlave.setNeutralMode(NeutralMode.Brake);
		differentialDrive.setSafetyEnabled(false);
		shiftLow();
	}
	
	public void teleInit() {
    	leftMaster.setNeutralMode(NeutralMode.Coast);
    	leftSlave.setNeutralMode(NeutralMode.Coast);
    	rightMaster.setNeutralMode(NeutralMode.Coast);
    	rightSlave.setNeutralMode(NeutralMode.Coast);
		differentialDrive.setSafetyEnabled(true);
		shiftLow();
	}
	
	/*Called in constructor to ensure correct drive talon settings*/
	private void TalonConfig() {
    	leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.TIMOUT_MS);
    	rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.TIMOUT_MS);
		
    	leftMaster.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);
    	rightMaster.setSelectedSensorPosition(0, 0, RobotMap.TIMOUT_MS);
    	
    	leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.TIMOUT_MS);
    	leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.TIMOUT_MS);
    	
    	rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.TIMOUT_MS);
    	rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.TIMOUT_MS);
		
    	leftMaster.setSensorPhase(false);
    	rightMaster.setSensorPhase(false);
    	
    	leftMaster.setInverted(true);
    	leftSlave.setInverted(true);
    	
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
    
    public List<Double> DebugMotionMagic()
    {
		List<Double> resulterino = new ArrayList<Double>();
		resulterino.add((double) rightMaster.getSelectedSensorPosition(0));
		resulterino.add((double) rightMaster.getSelectedSensorVelocity(0));
		resulterino.add((double) rightMaster.getActiveTrajectoryPosition());
		resulterino.add((double) rightMaster.getActiveTrajectoryVelocity());
		resulterino.add(rightMaster.getMotorOutputPercent());
		resulterino.add((double) rightMaster.getClosedLoopError(0));
		resulterino.add((double) leftMaster.getSelectedSensorPosition(0));
		resulterino.add((double) leftMaster.getSelectedSensorVelocity(0));
		resulterino.add((double) leftMaster.getActiveTrajectoryPosition());
		resulterino.add((double) leftMaster.getActiveTrajectoryVelocity());
		resulterino.add(leftMaster.getMotorOutputPercent());
		resulterino.add((double) leftMaster.getClosedLoopError(0));
		return resulterino;
	}
}