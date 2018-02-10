package org.usfirst.frc.team63.robot.subsystems;

import org.usfirst.frc.team63.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSubsystem extends Subsystem {
	 private WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.DRIVELEFTMASTER); 
	 private WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.DRIVERIGHTMASTER); 
	 private WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.DRIVELEFTSLAVE); 
	 private WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.DRIVERIGHTSLAVE);
	 private Solenoid leftShifter = new Solenoid(RobotMap.PCMID, RobotMap.LEFTSHIFTER);
	 private Solenoid rightShifter = new Solenoid(RobotMap.PCMID, RobotMap.RIGHTSHIFTER); 
	 
	 private DifferentialDrive differentialDrive;
	 
	 public DriveSubsystem() {
		 differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
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
		differentialDrive.arcadeDrive(xSpeed, zRotation, false);
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
	
	public void resetEncoders() {
		leftMaster.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		rightMaster.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
	
		leftMaster.getSensorCollection().setQuadraturePosition(RobotMap.kVelocityControlSlot, 10);
		rightMaster.getSensorCollection().setQuadraturePosition(RobotMap.kVelocityControlSlot, 10);
	}
	
	public void shiftHigh()
	{
		leftShifter.set(true);
		rightShifter.set(true);
	}
	
	public void shiftLow() 
	{
		leftShifter.set(false);
		rightShifter.set(false);
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
	private static double unitsToInches(double units) {
	    return units * RobotMap.kDriveWheelCircumference / RobotMap.kDriveEncoderFactor;
	}
	private static double inchesToUnits(double inches) {
		return inches * RobotMap.kDriveEncoderFactor / RobotMap.kDriveWheelCircumference; 
	}
	
	/*Called in constructor to ensure correct drive talon settings*/
	private void TalonConfig() {
		//Makes talons force zero voltage across when zero output to resist motion
		leftMaster.setNeutralMode(NeutralMode.Brake);
		leftSlave.setNeutralMode(NeutralMode.Brake);
		rightMaster.setNeutralMode(NeutralMode.Brake);
		rightSlave.setNeutralMode(NeutralMode.Brake);
		
		leftSlave.set(ControlMode.Follower, RobotMap.DRIVELEFTMASTER);
		rightSlave.set(ControlMode.Follower, RobotMap.DRIVERIGHTMASTER);
		
		//Closed Loop Voltage Limits
		leftMaster.configPeakOutputForward(1.0, RobotMap.kTimeoutMs);
		leftMaster.configPeakOutputReverse(-1.0, RobotMap.kTimeoutMs);
		leftMaster.configPeakOutputForward(+1.0, RobotMap.kTimeoutMs);    	    
		leftMaster.configPeakOutputReverse(-1.0, RobotMap.kTimeoutMs);  
		
		leftSlave.configNominalOutputForward(0.0, RobotMap.kTimeoutMs);
		leftSlave.configNominalOutputReverse(-0.0, RobotMap.kTimeoutMs);
		leftSlave.configPeakOutputForward(+1.0, RobotMap.kTimeoutMs);    	    
		leftSlave.configPeakOutputReverse(-1.0, RobotMap.kTimeoutMs);   	    
		
		rightMaster.configNominalOutputForward(0.0, RobotMap.kTimeoutMs);
		rightMaster.configNominalOutputReverse(-0.0, RobotMap.kTimeoutMs);
		rightMaster.configPeakOutputForward(+1.0, RobotMap.kTimeoutMs);    	    
		rightMaster.configPeakOutputReverse(-1.0, RobotMap.kTimeoutMs);   	    
		
		rightSlave.configNominalOutputForward(0.0, RobotMap.kTimeoutMs);
		rightSlave.configNominalOutputReverse(-0.0, RobotMap.kTimeoutMs);
		rightSlave.configPeakOutputForward(+1.0, RobotMap.kTimeoutMs);    	    
		rightSlave.configPeakOutputReverse(-1.0, RobotMap.kTimeoutMs);  
		
		//Configure Quadrature Encoders
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.kTimeoutMs);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.kTimeoutMs);
    	leftMaster.setSensorPhase(true);
    	rightMaster.setSensorPhase(true);
	}
}