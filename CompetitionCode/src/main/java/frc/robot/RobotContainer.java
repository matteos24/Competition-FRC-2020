
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autocommands.AlignTurn;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.subsystems.*;

import static frc.robot.Constants.*;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // JOYSTICKS
  public final Joystick driver = new Joystick(DRIVER_CONTROLLER);
  public final Joystick operator = new Joystick(OPERATOR_CONTROLLER);

  // BUTTONS
  public final JoystickButton toggleShooterButton = new JoystickButton(operator, RIGHT_BUMPER);
  public final JoystickButton shootButton = new JoystickButton(operator, LEFT_BUMPER);
  public final JoystickButton modeSwitchButton = new JoystickButton(driverController, RIGHT_BUMPER);

  // SUBSYSTEMS
  public final Shooter SHOOTER = new Shooter();
  private final Drivetrain DRIVETRAIN = new Drivetrain();
  private final Vision VISION = new Vision(GOAL_COLOR);
  
  // COMMANDS
  public final StartEndCommand modeSwitch = new StartEndCommand(
         () -> DRIVETRAIN.modeSlow(),
         () -> DRIVETRAIN.modeFast(),
         DRIVETRAIN
     ); 

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    modeSwitchButton.whenHeld(modeSwitch);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null; //TODO- auto command
  }

  public Drivetrain getDrivetrain() {
    return this.DRIVETRAIN;
  }
}
