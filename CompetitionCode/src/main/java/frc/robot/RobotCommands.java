/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.*;

import frc.robot.subsystems.*;

/**
 * For robot commands because RobotContainer is frighteningly messy
 */
public class RobotCommands{

    // CREATE SUBSYSTEMS
    public final Climber CLIMBER = new Climber();


    // == COMMANDS == //

    // BASE COMMANDS

    // CLIMBER COMMANDS
    public final StartEndCommand raiseLifter = new StartEndCommand(
        () -> CLIMBER.lifterUp(),
        () -> CLIMBER.lifterZero(),
        CLIMBER
    );
    
    public final StartEndCommand lowerLifter = new StartEndCommand(
        () -> CLIMBER.lifterDown(),
        () -> CLIMBER.lifterZero(),
        CLIMBER
    );

    public final StartEndCommand gearClimb = new StartEndCommand(
        () -> CLIMBER.gearOn(),
        () -> CLIMBER.gearOff(),
        CLIMBER
    );

    public final ConditionalCommand raiseOrLower = new ConditionalCommand(
        lowerLifter,
        raiseLifter,
        CLIMBER.hasLiftedSupplier
    );

    public final InstantCommand doNothing = new InstantCommand(
        
    );


}
