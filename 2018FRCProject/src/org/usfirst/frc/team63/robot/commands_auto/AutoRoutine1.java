package org.usfirst.frc.team63.robot.commands_auto;

import org.usfirst.frc.team63.robot.RobotMap;
import org.usfirst.frc.team63.robot.commands_claw.BoxShoot;
import org.usfirst.frc.team63.robot.commands_drive.AutoDriveFixedDistance;
import org.usfirst.frc.team63.robot.commands_drive.AutoRotate;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRoutine1 extends CommandGroup {
	private char botPos; //l, m, or r
	
    public AutoRoutine1(String fieldSetup, int switches) {
    	
    	if (fieldSetup.charAt(1) == botPos) {
    		addSequential(new AutoDriveFixedDistance(RobotMap.DIST_SCALE));
    		
    		if(botPos == 'l') addSequential(new AutoRotate(90));
    		else addSequential(new AutoRotate(-90));
    		
    		addSequential(new BoxShoot());
    	}
    	else if (fieldSetup.charAt(0) == botPos) {
    		addSequential(new AutoDriveFixedDistance(RobotMap.DIST_SWITCH));
    		
    		if(botPos == 'l') addSequential(new AutoRotate(90));
    		else addSequential(new AutoRotate(-90));
    		
    		addSequential(new BoxShoot());
    	}
    	else {
    		addSequential(new AutoDriveFixedDistance(RobotMap.DIST_LINE));
    	}
    }
}
