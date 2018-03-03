
package org.usfirst.frc.team63.robot.commands_auto;

import org.usfirst.frc.team63.robot.RobotMap;
import org.usfirst.frc.team63.robot.commands_claw.BoxObtainFoSho;
import org.usfirst.frc.team63.robot.commands_claw.BoxShootForTime;
import org.usfirst.frc.team63.robot.commands_drive.AutoDriveFixedDistance;
import org.usfirst.frc.team63.robot.commands_drive.AutoRotate;
import org.usfirst.frc.team63.robot.commands_lift.AutoSetLiftPosition;
import org.usfirst.frc.team63.robot.commands_lift.MoveLiftMaxHeight;
import org.usfirst.frc.team63.robot.commands_lift.MoveLiftMinHeight;
import org.usfirst.frc.team63.robot.commands_lift.MoveLiftOneBoxHeight;
import org.usfirst.frc.team63.robot.subsystems.LiftSubsystem.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoRoutine1 extends CommandGroup {
    //Das Auto, all inches
	public static final double SWITCH_HEIGHT = 30;
    public static final double DIST_SCALE0 = 240; //main bit
    public static final double DIST_SCALE1 = 6; //diagonal bit
    public static final double DIST_SCALE2 = 8; //creep forward
    public static final double DIST_SWITCH0 = 140; //Distance from the starting point to the switch
    public static final double DIST_SWITCH1 = 6; //Distance from the switch after the turn towards the switch
    public static final double DIST_WE_REKT_BOIS = 120; //line to cross in auto for points
    public static final double DIST_UNTIL_TURN = 24; //how far to go before turning when in middle
	public static final double DIST_HALF_SWITCH_WIDTH = 50; //horizontal distance for robot in middle to got
	public static final double DIST_SWITCH_FUDGE = 44;
	public static final double DIST_SWITCH_OFFSET = 6; //how off center we are when in center
	public static final double DIST_TWOCUBE0 = 48; //distance to come back for switch before turning
	public static final double DIST_TWOCUBE1 = 90.50966799187808; //slanted bit
	public static final double DIST_TWOCUBE2 = 32; //drive forward for cube
	private static final double DIST_SHAKE = 10;
	
	private char botPos = 'z'; //l, m, or r
	
    public AutoRoutine1(String fieldSetup, int switches) {
    	System.out.println("Switches: " + switches);
    	if(switches == 1) botPos = 'r';
    	if(switches == 2) botPos = 'm';
    	if(switches == 4) botPos = 'l';
    	System.out.println("botPos = " + botPos);
    	addSequential(new AutoDriveFixedDistance(DIST_SHAKE));
    	addSequential(new AutoDriveFixedDistance(-DIST_SHAKE));
    	addSequential(new AutoSetLiftPosition(17));
    	if(botPos == 'm') {
    		//try going for switch
    		addParallel(new AutoSetLiftPosition(SWITCH_HEIGHT));
    		addSequential(new AutoDriveFixedDistance(DIST_UNTIL_TURN));
    		if(fieldSetup.charAt(0) == 'l'){
    			addSequential(new AutoRotate(-90));
    			addSequential(new AutoDriveFixedDistance(DIST_HALF_SWITCH_WIDTH + DIST_SWITCH_OFFSET+12));
    		} else {
    			addSequential(new AutoRotate(90));
    			addSequential(new AutoDriveFixedDistance(DIST_HALF_SWITCH_WIDTH - DIST_SWITCH_OFFSET));
    		}
    		if(fieldSetup.charAt(0) == 'l') {
    			addSequential(new AutoRotate(90));
    			addSequential(new AutoDriveFixedDistance(DIST_SWITCH0-DIST_UNTIL_TURN-DIST_SWITCH_FUDGE-12));
    		} else {
    			addSequential(new AutoRotate(-90));
    			addSequential(new AutoDriveFixedDistance(DIST_SWITCH0-DIST_UNTIL_TURN-DIST_SWITCH_FUDGE));
    		}
    		
    		addSequential(new BoxShootForTime());
    	} else if (fieldSetup.charAt(0) == botPos) { //only switch in on same side
    		//just switch
    		addSequential(new AutoDriveFixedDistance(DIST_SWITCH0));
    		
    		turnCalc(90);
    		
    	//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    		addSequential(new AutoSetLiftPosition(SWITCH_HEIGHT));
    		addSequential(new AutoDriveFixedDistance(DIST_SWITCH1));
    		addSequential(new BoxShootForTime());
    		addSequential(new AutoDriveFixedDistance(-12));
    		addSequential(new MoveLiftMinHeight());
    	} else if(fieldSetup.charAt(1) == botPos) { //scale on our bot's side
    		//go for scale
    		System.out.println("Goooooooooooo");
    		addSequential(new AutoDriveFixedDistance(DIST_SCALE0));
    		System.out.println("Already Went");
    		turnCalc(30);
    		addParallel(new AutoDriveFixedDistance(DIST_SCALE1));
        	addSequential(new AutoSetLiftPosition(SmartDashboard.getNumber("max_lift_inches", 79)-2));
        	addSequential(new AutoDriveFixedDistance(DIST_SCALE2));
    		addSequential(new BoxShootForTime());
    		addSequential(new AutoDriveFixedDistance(-DIST_SCALE2));
    		addSequential(new MoveLiftMinHeight());
//    		if (fieldSetup.charAt(0) == botPos) { //switch is also on our bot's side
//    			//continue for two cube auto
//        		turnCalc(90);
//        		addSequential(new AutoDriveFixedDistance(DIST_TWOCUBE0));
//        		turnCalc(-45);
//            	addParallel(new MoveLiftMinHeight());
//        		addSequential(new AutoDriveFixedDistance(DIST_TWOCUBE1));
//        		turnCalc(45);
//        		addSequential(new AutoDriveFixedDistance(DIST_TWOCUBE2));
//        		addSequential(new BoxObtainFoSho());
//        		addParallel(new AutoSetLiftPosition(SWITCH_HEIGHT));
//        		addSequential(new AutoDriveFixedDistance(26));
//        		addSequential(new BoxShootForTime());
//    		}
    	}
    	else { //we rekt bois
    		//just drive forward
    		addSequential(new AutoDriveFixedDistance(DIST_WE_REKT_BOIS));
    		addSequential(new MoveLiftMinHeight());
    	}
    	
    }
    
    //specify way to go when on left side
    private void turnCalc(double angle) {
		if(botPos == 'l') {
			addSequential(new AutoRotate(angle));
		}
		else{
			addSequential(new AutoRotate(-angle));
		}
    }
}
