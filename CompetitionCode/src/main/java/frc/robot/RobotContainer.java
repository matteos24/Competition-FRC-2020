
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.commands.StoreBall;
import frc.robot.subsystems.*;
import frc.robot.triggers.StorageLimitSwitchTrigger;

import static frc.robot.Constants.*;

public class RobotContainer {

  // BASE INITS
  int timesSpun;

  // JOYSTICKS
  public final Joystick driver = new Joystick(DRIVER_CONTROLLER);
  public final Joystick operator = new Joystick(OPERATOR_CONTROLLER);

  // BUTTONS
  public final JoystickButton toggleShooterButton = new JoystickButton(operator, RIGHT_BUMPER);
  public final JoystickButton shootButton = new JoystickButton(operator, LEFT_BUMPER);
  public final JoystickButton modeSwitchButton = new JoystickButton(driver, RIGHT_BUMPER);

  public final JoystickButton pistonButton = new JoystickButton(operator, INTAKE_PISTON_BUTTON),
      motorIntakeButton = new JoystickButton(operator, INTAKE_MOTOR_BUTTON),
      motorOuttakeButton = new JoystickButton(operator, OUTTAKE_MOTOR_BUTTON);

  public final JoystickButton storageOverrideButton = new JoystickButton(operator, START_BUTTON);

  // SUBSYSTEMS
  public final Drivetrain DRIVETRAIN = new Drivetrain();
  public final Intake INTAKE = new Intake();
  public final Storage STORAGE = new Storage();

  // COMMANDS
  public final StartEndCommand modeSwitch = new StartEndCommand(() -> DRIVETRAIN.modeSlow(),
      () -> DRIVETRAIN.modeFast(), DRIVETRAIN);

  public final StartEndCommand intakeCommand = new StartEndCommand(() -> INTAKE.wheelSpeed(WHEEL_INTAKE_SPEED),
      () -> INTAKE.wheelOff(), INTAKE);

  public final StartEndCommand outtakeCommand = new StartEndCommand(() -> INTAKE.wheelReverseSpeed(WHEEL_INTAKE_SPEED),
      () -> INTAKE.wheelOff(), INTAKE);

  // PISTON INTAKE

  public final StartEndCommand pistonMove = new StartEndCommand(() -> INTAKE.deployPiston(),
      () -> INTAKE.retractPiston(), INTAKE

  );

  public final InstantCommand pistonOffCommand = new InstantCommand(() -> INTAKE.pistonOff(), INTAKE);

  // for storage trigger
  public boolean shouldStorageIntake() {
    return STORAGE.isSwitchPressed(0) && !STORAGE.isOverridden();
  }

  // STORAGE COMMANDS
  public final StartEndCommand storageOverride = new StartEndCommand(() -> STORAGE.setGateSpeed(), () -> STORAGE.stop(),
      STORAGE);
  public final InstantCommand startStorageOverride = new InstantCommand(() -> STORAGE.override());

  public final StoreBall storeBall = new StoreBall(STORAGE);

  // STORAGE TRIGGER
  private final StorageLimitSwitchTrigger storageTrigger = new StorageLimitSwitchTrigger(this);

  // === AUTO === //
  public final InstantCommand doNothing = new InstantCommand();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    modeSwitchButton.whenHeld(modeSwitch);
    pistonButton.toggleWhenPressed(pistonMove.withTimeout(2).andThen(pistonOffCommand));
    motorIntakeButton.whenHeld(intakeCommand);
    motorOuttakeButton.whenHeld(outtakeCommand);

    // STORAGE
    storageTrigger.whenActive(storeBall);
    storageOverrideButton.whenPressed(startStorageOverride);
    storageOverrideButton.whenHeld(storageOverride);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return doNothing;
  }

  public Drivetrain getDrivetrain() {
    return this.DRIVETRAIN;
  }
}