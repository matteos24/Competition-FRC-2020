/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.*;

import frc.robot.subsystems.*;

import static frc.robot.Constants.*;

/**
 * For robot commands because RobotContainer is frighteningly messy
 */
public class RobotCommands{

    // CREATE SUBSYSTEMS
    public final Storage STORAGE = new Storage();




    
    
    // STORAGE COMMANDS
    public final StartEndCommand storageGate = new StartEndCommand(
        () -> STORAGE.gateSpeed(),
        () -> STORAGE.gateZero(),
        STORAGE
    );

    public final InstantCommand doNothing = new InstantCommand();
   



}
