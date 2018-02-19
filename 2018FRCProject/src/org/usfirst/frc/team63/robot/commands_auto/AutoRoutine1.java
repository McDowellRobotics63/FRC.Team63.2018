package org.usfirst.frc.team63.robot.commands_auto;

import org.usfirst.frc.team63.robot.RobotMap;
import org.usfirst.frc.team63.robot.commands_claw.AutoBoxObtain;
import org.usfirst.frc.team63.robot.commands_claw.BoxShoot;
import org.usfirst.frc.team63.robot.commands_drive.AutoDriveFixedDistance;
import org.usfirst.frc.team63.robot.commands_drive.AutoRotate;
import org.usfirst.frc.team63.robot.commands_lift.AutoSetLiftPosition;
import org.usfirst.frc.team63.robot.commands_lift.MoveLiftMaxHeight;
import org.usfirst.frc.team63.robot.commands_lift.MoveLiftMinHeight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRoutine1 extends CommandGroup {
	private char botPos; //l, m, or r
	
    public AutoRoutine1(String fieldSetup, int switches) {
    	if(switches == 1) botPos = 'l';
    	if(switches == 2) botPos = 'm';
    	if(switches == 4) botPos = 'r';
    	
    	if(botPos == 'm') {
    		//try going for switch
//    		addParallel(new AutoSetLiftPosition(RobotMap.SWITCH_HEIGHT));
//    		addSequential(new AutoDriveFixedDistance(RobotMap.DIST_UNTIL_TURN));
    		if(fieldSetup.charAt(0) == 'l'){
//    			addSequential(new AutoRotate(-90));
    		} else {
//    			addSequential(new AutoRotate(90));
    		}
//    		addSequential(new AutoDriveFixedDistance(RobotMap.DIST_HALF_SWITCH_WIDTH));
    		if(fieldSetup.charAt(0) == 'l') {
//    			addSequential(new AutoRotate(90));
    		} else {
//    			addSequential(new AutoRotate(-90));
    		}
//    		addSequential(new AutoDriveFixedDistance(RobotMap.DIST_SWITCH-RobotMap.DIST_UNTIL_TURN));
//    		addSequential(new BoxShoot());
    	} else if (fieldSetup.charAt(1) == botPos) {
    		//go for scale
//    		addSequential(new AutoDriveFixedDistance(RobotMap.DIST_SCALE));
//    		addParallel(new MoveLiftMaxHeight());
    		if(botPos == 'l') {
//    			addSequential(new AutoRotate(90));
    		}
    		else{
//    			addSequential(new AutoRotate(-90));
    		}
//        	addSequential(new AutoDriveFixedDistance(24));
//    		addSequential(new BoxShoot());
    		
    		if (fieldSetup.charAt(0) == botPos) {
    			//continue for two cube auto
//    			addParallel(new MoveLiftMinHeight());
        		if(botPos == 'l') {
//        			addSequential(new AutoRotate(-90));
        		}
        		else{
//        			addSequential(new AutoRotate(90));
        		}
//        		addSequential(new AutoDriveFixedDistance(RobotMap.DIST_RETURN));
//        		addSequential(new AutoBoxObtain());
//        		addParallel(new AutoSetLiftPosition(RobotMap.SWITCH_HEIGHT));
//        		addSequential(new AutoDriveFixedDistance(6));
//        		addSequential(new BoxShoot());
    		}
    	}
    	else if (fieldSetup.charAt(0) == botPos) {
    		//just switch
//    		addSequential(new AutoDriveFixedDistance(RobotMap.DIST_SWITCH));
    		
    		if(botPos == 'l')// addSequential(new AutoRotate(90));
    		else //addSequential(new AutoRotate(-90));
    		
//    		addSequential(new AutoSetLiftPosition(RobotMap.SWITCH_HEIGHT));
//    		addSequential(new BoxShoot());
    	}
    	else {
    		// basic drive forward
//    		addSequential(new AutoDriveFixedDistance(RobotMap.DIST_LINE));
    	}
//    	addSequential(new MoveLiftMinHeight());
    }
}
