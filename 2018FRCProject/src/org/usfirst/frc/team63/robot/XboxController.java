package org.usfirst.frc.team63.robot;

import org.usfirst.frc.team63.robot.XboxDPadButton.DPAD_BUTTON;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class XboxController extends Joystick {	
	//Controller Map
	private static final int XBOX_LEFT_X_AXIS = 0;
	private static final int XBOX_LEFT_Y_AXIS = 1;
	private static final int XBOX_LEFT_TRIGGER_AXIS = 2;
	private static final int XBOX_RIGHT_TRIGGER_AXIS = 3;
	private static final int XBOX_RIGHT_X_AXIS = 4;
	private static final int XBOX_RIGHT_Y_AXIS = 5;
	private static final int XBOX_A = 1;
	private static final int XBOX_B = 2;
	private static final int XBOX_X = 3;
	private static final int XBOX_Y = 4;
	private static final int XBOX_LEFT_BUMPER = 5;    
	private static final int XBOX_RIGHT_BUMPER = 6;
	private static final int XBOX_BACK = 7;
	private static final int XBOX_START = 8;
    
	private Button btnX;
	private Button btnY;
	private Button btnA;
	private Button btnB;
	private Button btnLB;
	private Button btnRB;
	private Button btnStart;
	private Button btnBack;
	private Button btnDpadUp;
	private Button btnDpadRight;
	private Button btnDpadDown;
	private Button btnDpadLeft;

	public XboxController(final int port)
	{
		super(port);
		
		btnX = new JoystickButton(this, XBOX_X);
		btnY = new JoystickButton(this, XBOX_Y);
		btnA = new JoystickButton(this, XBOX_A);
		btnB = new JoystickButton(this, XBOX_B);
		btnLB = new JoystickButton(this, XBOX_LEFT_BUMPER);
		btnRB = new JoystickButton(this, XBOX_RIGHT_BUMPER);
		btnStart = new JoystickButton(this, XBOX_START);
		btnBack = new JoystickButton(this, XBOX_BACK);
		btnDpadUp = new XboxDPadButton(this, DPAD_BUTTON.DPAD_UP);
		btnDpadRight = new XboxDPadButton(this, DPAD_BUTTON.DPAD_RIGHT);
		btnDpadDown = new XboxDPadButton(this, DPAD_BUTTON.DPAD_DOWN);
		btnDpadLeft = new XboxDPadButton(this, DPAD_BUTTON.DPAD_LEFT);
	}
	
	public double LeftStickY(){   	
		//return -this.getRawAxis(XBOX_LEFT_Y_AXIS);
		return conditionAxis(-this.getRawAxis(XBOX_LEFT_Y_AXIS), 0.18, 1, 0.6, 2, -1.0, 1.0);
	}
	
	public double LeftStickX(){
		//return this.getRawAxis(XBOX_LEFT_X_AXIS);
		return conditionAxis(this.getRawAxis(XBOX_LEFT_X_AXIS), 0.18, 1, 0.8, 5, -0.5, 0.5);
	}
	
	public double RightStickY(){
		//return -this.getRawAxis(XBOX_RIGHT_Y_AXIS);
		return conditionAxis(-this.getRawAxis(XBOX_RIGHT_Y_AXIS), 0.18, 1, 0.8, 2, -1.0, 1.0);
	}
	
	public double RightStickX(){
		//return this.getRawAxis(XBOX_RIGHT_X_AXIS);
		return conditionAxis(this.getRawAxis(XBOX_RIGHT_X_AXIS), 0.18, 1, 0.8, 5, -1.0, 1.0);
	}
	
	public Button X()
	{
		return btnX;
	}
	
	public Button Y()
	{
		return btnY;
	}
	
	public Button A()
	{
		return btnA;
	}
	
	public Button B()
	{
		return btnB;
	}
	
	public Button LeftBumper()
	{
		return btnLB;
	}
	
	public Button RightBumper()
	{
		return btnRB;
	}
	
	public Button Start()
	{
		return btnStart;
	}
	
	public Button Back()
	{
		return btnBack;
	}
	
	public Button DpadUp()
	{
		return btnDpadUp;
	}
	
	public Button DpadDown()
	{
		return btnDpadDown;
	}
	
	public Button DpadLeft()
	{
		return btnDpadLeft;
	}
	
	public Button DpadRight()
	{
		return btnDpadRight;
	}
	
	private double conditionAxis(double axis, double deadband, double rate, double expo, double power, double min, double max)
	{		
		deadband = Math.min(Math.abs(deadband), 1.0);
		rate = Math.max(0.1, Math.min(Math.abs(rate), 10));
		expo = Math.max(0.0, Math.min(Math.abs(expo), 1.0));
		power = Math.max(1.0, Math.min(Math.abs(power), 10.0));
				
		//System.out.println("[INPUT] axis: " + axis + ", deadband: " + deadband + ", rate: " + rate + ", expo: " + expo + ", power: " + power);
		
    	if(axis > -deadband && axis < deadband)
    	{
    		axis = 0;
    	}
    	    	    	
    	axis = rate * (Math.signum(axis) * ((Math.abs(axis) - deadband) / (1.0 - deadband)));
    	
    	//System.out.println("[SCALE] axis: " + axis + ", deadband: " + deadband + ", rate: " + rate + ", expo: " + expo + ", power: " + power);
    	
    	if(expo > 0.01)
    	{
    		axis = (axis * Math.pow(Math.abs(axis), power) * expo) + (axis * (1-expo));
    		//System.out.println("[EXPO] axis: " + axis + ", deadband: " + deadband + ", rate: " + rate + ", expo: " + expo + ", power: " + power);
    	}
    	
    	Math.max(Math.min(axis, max), min);
    	
    	return axis;
	}
}
