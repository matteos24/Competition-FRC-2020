/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

import static frc.robot.Constants.*;

/**
 * For robot commands because RobotContainer is frighteningly messy
 */
public class RobotCommands{

    // CREATE SUBSYSTEMS
    public static final Storage STORAGE = new Storage();
        // for storage trigger
        public static boolean StorageShouldIntake(){return STORAGE.limitPressed(0) && !STORAGE.isOverrided(); }

    
    // STORAGE COMMANDS
    public final StartEndCommand storageOverride = new StartEndCommand(
        () -> STORAGE.setGateSpeed(),
        () -> STORAGE.stop(),
        STORAGE
    );
    public final InstantCommand startStorageOverride = new InstantCommand(
        () -> STORAGE.override()
    );

    public final StoreBall storeBall = new StoreBall(STORAGE);



    public final InstantCommand doNothing = new InstantCommand();
   



}
