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

/**
 *
 */
public class AutoRoutine1 extends CommandGroup {
    //Das Auto, all inches
	public static final double SWITCH_HEIGHT = 15;
    public static final double DIST_SCALE0 = 235;
    public static final double DIST_SCALE1 = 18;
    public static final double DIST_SWITCH = 140; //Distance from the starting point to the switch
    public static final double DIST_WE_REKT_BOIS = 120; //line to cross in auto for points
    public static final double DIST_UNTIL_TURN = 70; //how far to go before turning when in middle
	public static final double DIST_HALF_SWITCH_WIDTH = 60; //horizontal distance for robot in middle to go
	public static final double DIST_TWOCUBE0 = 48; //distance to come back for switch before turning
	public static final double DIST_TWOCUBE1 = 90.50966799187808; //slanted bit
	public static final double DIST_TWOCUBE2 = 32; //drive forward for cube
	
	private char botPos = 'z'; //l, m, or r
	
    public AutoRoutine1(String fieldSetup, int switches) {
    	System.out.println("Switches: " + switches);
    	if(switches == 1) botPos = 'r';
    	if(switches == 2) botPos = 'm';
    	if(switches == 4) botPos = 'l';
    	System.out.println("botPos = " + botPos);
    	addSequential(new MoveLiftOneBoxHeight(Direction.UP));
    	if(botPos == 'm') {
    		//try going for switch
    		addParallel(new AutoSetLiftPosition(SWITCH_HEIGHT));
    		addSequential(new AutoDriveFixedDistance(DIST_UNTIL_TURN));
    		if(fieldSetup.charAt(0) == 'l'){
    			addSequential(new AutoRotate(-90));
    		} else {
    			addSequential(new AutoRotate(90));
    		}
    		addSequential(new AutoDriveFixedDistance(DIST_HALF_SWITCH_WIDTH));
    		if(fieldSetup.charAt(0) == 'l') {
    			addSequential(new AutoRotate(90));
    		} else {
    			addSequential(new AutoRotate(-90));
    		}
    		addSequential(new AutoDriveFixedDistance(DIST_SWITCH-DIST_UNTIL_TURN-20));
    		addSequential(new BoxShootForTime());
    	} else if(fieldSetup.charAt(1) == botPos) { //scale on our bot's side
    		//go for scale
    		System.out.println("Goooooooooooo");
    		addSequential(new AutoDriveFixedDistance(DIST_SCALE0));
    		System.out.println("Already Went");
    		turnCalc(40);
    		addParallel(new AutoDriveFixedDistance(DIST_SCALE1));
        	addSequential(new AutoSetLiftPosition(RobotMap.MAX_LIFT_DISPLACEMENT_INCHES-2));
    		addSequential(new BoxShootForTime());
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
    	else if (fieldSetup.charAt(0) == botPos) { //only switch in on same side
    		//just switch
    		addSequential(new AutoDriveFixedDistance(DIST_SWITCH));
    		
    		turnCalc(90);
    		
    		addSequential(new AutoSetLiftPosition(SWITCH_HEIGHT));
    		addSequential(new BoxShootForTime());
    	}
    	else { //we rekt bois
    		//just drive forward
    		addSequential(new AutoDriveFixedDistance(DIST_WE_REKT_BOIS));
    	}
    	addSequential(new MoveLiftMinHeight());
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
