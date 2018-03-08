
package org.usfirst.frc.team63.robot.commands_auto;

import org.usfirst.frc.team63.robot.RobotMap;
import org.usfirst.frc.team63.robot.commands_claw.ObtainBox;
import org.usfirst.frc.team63.robot.commands_claw.AutoShootBox;
import org.usfirst.frc.team63.robot.commands_drive.AutoDriveFixedDistance;
import org.usfirst.frc.team63.robot.commands_drive.AutoRotate;
import org.usfirst.frc.team63.robot.commands_lift.AutoSetLiftPosition;
import org.usfirst.frc.team63.robot.commands_lift.MoveLiftOneBoxHeight;
import org.usfirst.frc.team63.robot.subsystems.LiftSubsystem.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoRoutine extends CommandGroup {
    //All inches
	private static final double DEFAULT_HEIGHT = 17; //lift height for moving
	private static final double SWITCH_HEIGHT = 30;
	private static final double LINE = 120; //distance to auto line
	private static final double SHAKE = 6;
    private static final double SCALE0 = 240;
    private static final double SCALE1 = 6; //diagonal bit
    private static final double SCALE2 = 8; //creep forward
    private static final double SCALE_TURN = 30;
    private static final double TWOCUBE0 = 48; //distance to come back for switch before turning
	private static final double TWOCUBE1 = 90.50966799187808; //slanted bit
	private static final double TWOCUBE2 = 32; //drive forward for cube
    private static final double SWITCH0 = 140; //distance from middle of robot to middle of switch
    private static final double SWITCH1 = 6; //left/right distance from switch
	private static final double MIDDLE1 = 54; //half of switch width
	private static final double MIDDLE_FUDGE = 24; //amount to subtract from switch0 to go forward
	private static final double MIDDLE_OFFSET = 16; //offcenteredness to correct when in middle
	private static final double MIDDLE_CREEP = 6;
	
	private char botPos = 'z'; //l, m, or r
	private String m_fieldSetup;
    public AutoRoutine(String fieldSetup, int switches) {
    	if(switches == 1) botPos = 'r';
    	if(switches == 2) botPos = 'm';
    	if(switches == 4) botPos = 'l';
    	m_fieldSetup = fieldSetup;
    	drive(SHAKE);
    	parallelLift(DEFAULT_HEIGHT);
    	if(botPos == 'm') {
    		middle();
    	} else if (fieldSetup.charAt(0) == botPos) { //only switch in on same side
    		switches();
    	} else if(fieldSetup.charAt(1) == botPos) { //scale on our bot's side
    		scale();
    		if (fieldSetup.charAt(0) == botPos) { //switch is also on our bot's side
    			twoCube();
    		}
    	}
    	else {
    		weRektBois();
    	}
    	if(botPos!='m') sequentialLift(0);
    }
    
    private void middle() {
		double angle, sideDist;
		double forwardDist = SWITCH0 - MIDDLE_FUDGE - MIDDLE_CREEP - SHAKE;
		if(m_fieldSetup.charAt(0) == 'l'){
			sideDist = MIDDLE1 + MIDDLE_OFFSET;
			angle = -Math.atan(sideDist/forwardDist);;
		} else {
			sideDist = MIDDLE1 - MIDDLE_OFFSET;
			angle = Math.atan(sideDist/forwardDist);
		}
		turn(angle);
		parallelLift(SWITCH_HEIGHT);
		drive(Math.sqrt(sideDist*sideDist + forwardDist*forwardDist));
		turn(-angle);
		drive(MIDDLE_CREEP);
		shoot();
    }
    
    private void scale() {
		drive(SCALE0-SHAKE);
		turnCalc(SCALE_TURN);
		drive(SCALE1);
		parallelLift(SmartDashboard.getNumber("max_lift_inches", 79)-2);
		drive(SCALE2);
		shoot();
		drive(-SCALE2);
    }
    
    private void switches() {
		drive(SWITCH0-SHAKE);
		turnCalc(90);
		parallelLift(SWITCH_HEIGHT);
		drive(SWITCH1);
		shoot();
		drive(-SWITCH1);
    }
    
    //starts from end pos of scale
    private void twoCube() {
		turnCalc(90);
		drive(TWOCUBE0);
		turnCalc(-45);
		parallelLift(0);
		drive(TWOCUBE1);
		turnCalc(45);
		drive(TWOCUBE2);
		sequentialLift(0);
		addSequential(new ObtainBox());
		parallelLift(SWITCH_HEIGHT);
		drive(26);
		shoot();
    }
    
    //autoline
    private void weRektBois() {
		drive(LINE-SHAKE);
    }
    
    //turns angle on left, -angle on right
    private void turnCalc(double angle) {
		if(botPos == 'l') {
			turn(angle);
		}
		else if (botPos == 'r'){
			turn(-angle);
		}
		else {
			System.out.println("BLAME JAKE");
		}
    }
    private void drive(double dist) {
    	addSequential(new AutoDriveFixedDistance(dist));
    }
    private void turn(double angle) {
    	addSequential(new AutoRotate(angle));
    }
    private void shoot() {
    	addSequential(new AutoShootBox());
    }
    private void parallelLift(double height) {
    	addParallel(new AutoSetLiftPosition(height));
    }
    private void sequentialLift(double height) {
    	addSequential(new AutoSetLiftPosition(height));
    }
}
