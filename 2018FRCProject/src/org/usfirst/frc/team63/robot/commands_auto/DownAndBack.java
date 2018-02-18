package org.usfirst.frc.team63.robot.commands_auto;

import org.usfirst.frc.team63.robot.commands_drive.AutoDriveFixedDistance;
import org.usfirst.frc.team63.robot.commands_drive.AutoRotate;
import org.usfirst.frc.team63.robot.commands_lift.AutoSetLiftPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DownAndBack extends CommandGroup {

    public DownAndBack() {
    	addParallel(new AutoSetLiftPosition(30));
    	addSequential(new AutoDriveFixedDistance());
    	addSequential(new AutoRotate());
    	addParallel(new AutoSetLiftPosition(1));
    	addSequential(new AutoDriveFixedDistance());
    }
}
