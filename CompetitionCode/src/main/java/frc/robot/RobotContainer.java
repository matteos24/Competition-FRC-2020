
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

import static frc.robot.Constants.*;

public class RobotContainer {

    // SUBSYSTEMS
    private final Intake INTAKE = new Intake();

    // INTAKE MOTOR COMMANDS
    private final StartEndCommand intakeCommand = new StartEndCommand(
        () -> INTAKE.wheelSpeed(WHEEL_INTAKE_SPEED),
        () -> INTAKE.wheelOff(),  
        INTAKE
    );

    public final StartEndCommand outtakeCommand = new StartEndCommand(
        () -> INTAKE.wheelReverseSpeed(WHEEL_INTAKE_SPEED), 
        () -> INTAKE.wheelOff(), 
        INTAKE
    );

    // PISTON INTAKE

    private final StartEndCommand pistonMove = new StartEndCommand(
        () -> INTAKE.deployPiston(),
        () -> INTAKE.retractPiston(),
        INTAKE

    );

    private final InstantCommand pistonOffCommand = new InstantCommand(
      () -> INTAKE.pistonOff(),
      INTAKE
    );

    // MAKE A NEW JOYSTICK

    public final Joystick opController = new Joystick(OPERATOR_CONTROLLER);
  
    // CONFIG BUTTON BINDINGS (See constants.java to change specific ports etc.)

    // PISTON-Y INTAKE BUTTONS
    // TODO: piston/motor change

    private final JoystickButton pistonButton = new JoystickButton(opController, INTAKE_PISTON_BUTTON),
                                 motorIntakeButton = new JoystickButton(opController, INTAKE_MOTOR_BUTTON),
                                 motorOuttakeButton = new JoystickButton(opController, OUTTAKE_MOTOR_BUTTON);
    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */

    public RobotContainer() {
        configureButtonActions();
    }

    /**
     * Config button actions: it changes what does each button do. Don't touch this to change bindings
     */
    private void configureButtonActions() {
        // PISTON-Y INTAKE BUTTONS
        pistonButton.toggleWhenPressed(pistonMove.withTimeout(2).andThen(pistonOffCommand));
        motorIntakeButton.whenHeld(intakeCommand);
        motorOuttakeButton.whenHeld(outtakeCommand);
        /* CODE FOR TESTING
        wheelOnFore.whenHeld(fore);
        wheelOnBack.whenHeld(back);
        */

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;

    }

}