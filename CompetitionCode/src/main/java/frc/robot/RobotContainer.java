
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {

    // IMPORTING STUFF AND STUFF
    private final RobotCommands robotCommands = new RobotCommands();


    // == JOYSTICK & BUTTON BINDINGS == //


    // NEW JOYSTICK
    public final Joystick opController = new Joystick(OPERATOR_CONTROLLER);

    // CONFIG BUTTON BINDINGS (See constants.java to change specific ports etc.)
    // CLIMBER BUTTONS
    private final JoystickButton raiseAndLowerButton = new JoystickButton(opController, LB),
                                 gearClimbButton = new JoystickButton(opController, BACK);

    // ROBOT CONTAINER
    public RobotContainer() {
        configureButtonActions();
    }

    // CONFIG BUTTON ACTIONS
    private void configureButtonActions() {

        // CLIMB BUTTONS
        raiseAndLowerButton.whenHeld(robotCommands.raiseOrLower);
        gearClimbButton.whenHeld(robotCommands.gearClimb);
        
    }
    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return robotCommands.doNothing;
    }
}