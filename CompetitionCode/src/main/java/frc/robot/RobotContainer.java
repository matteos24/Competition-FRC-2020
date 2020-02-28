
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.BallTrack;
import frc.robot.commands.EnableShooter;
import frc.robot.commands.GoalTrack;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.StoreBall;
import frc.robot.commands.auto.FailsafeAuto;
import frc.robot.commands.auto.MoveCommand;
import frc.robot.commands.auto.TestAutoCommandGroup;
import frc.robot.commands.auto.TrenchAuto;
import frc.robot.subsystems.*;
import frc.robot.triggers.StorageLimitSwitchTrigger;

import static frc.robot.Constants.*;

public class RobotContainer {

  // JOYSTICKS
  public final Joystick driver = new Joystick(DRIVER_CONTROLLER);
  public final Joystick operator = new Joystick(OPERATOR_CONTROLLER);

  // BUTTONS

  // Drivetrain
  public final JoystickButton modeSwitchButton = new JoystickButton(driver, RIGHT_BUMPER); // FAST SLOW

  // Intake
  public final JoystickButton intakeButton = new JoystickButton(operator, BUTTON_A),
      outttakeButton = new JoystickButton(operator, BUTTON_Y),
      raiseIntakeButton = new JoystickButton(operator, BUTTON_X);

  // Storage
  public final JoystickButton storageOverrideButton = new JoystickButton(operator, START_BUTTON);

  // Vision
  public final JoystickButton visionAlignBall = new JoystickButton(driver, 1);
  public final JoystickButton visionAlignGoal = new JoystickButton(driver, 3);

  // Shooter
  public JoystickButton toggleShooterButton = new JoystickButton(operator, RIGHT_BUMPER);

  public JoystickButton shootButton = new JoystickButton(operator, RIGHT_TRIGGER);

  public JoystickButton longShotButton = new JoystickButton(operator, 0); // TODO- find button for this and make
                                                                          // automatic
  public JoystickButton shortShotButton = new JoystickButton(operator, LEFT_TRIGGER);

  // SUBSYSTEMS
  public final Drivetrain DRIVETRAIN = new Drivetrain();
  public final Intake INTAKE = new Intake();
  public final Storage STORAGE = new Storage();
  public final Shooter SHOOTER = new Shooter();
  public final Vision VISION = new Vision();
  public final Climber CLIMBER = new Climber();

  // COMMANDS

  // Shooter
  public final StartEndCommand setShooterFar = new StartEndCommand(() -> {
    SHOOTER.setAngleForward();
  }, () -> {
    SHOOTER.setPistonsOff();
  }, SHOOTER);

  public final StartEndCommand setShooterClose = new StartEndCommand(() -> {
    SHOOTER.setAngleBack();
  }, () -> {
    SHOOTER.setPistonsOff();
  }, SHOOTER);

  public final ShootCommand revShort = new ShootCommand(SHOOTER, STORAGE, SHORT_DISTANCE_RPM);
  public final ShootCommand revLong = new ShootCommand(SHOOTER, STORAGE, LONG_DIST_RPM);

  // Drivetrain
  public final StartEndCommand modeSwitch = new StartEndCommand(() -> DRIVETRAIN.modeSlow(),
      () -> DRIVETRAIN.modeFast(), DRIVETRAIN);

  // Intake
  public final StartEndCommand deployIntake = new StartEndCommand(() -> {
    INTAKE.deployPistons();
  }, () -> {
    INTAKE.pistonOff();
  }, INTAKE);

  public final StartEndCommand retractIntake = new StartEndCommand(() -> {
    INTAKE.retractPistons();
  }, () -> {
    INTAKE.pistonOff();
  }, INTAKE);

  public final StartEndCommand intakeCommand = new StartEndCommand(() -> {
    INTAKE.setSpeed(WHEEL_INTAKE_SPEED);
    INTAKE.deployPistons();
  }, () -> {
    INTAKE.setSpeed(0);
    INTAKE.retractPistons();
  }, INTAKE);

  public final StartEndCommand outtakeCommand = new StartEndCommand(() -> INTAKE.setSpeed(-WHEEL_INTAKE_SPEED),
      () -> INTAKE.setSpeed(0), INTAKE);

  // for storage trigger
  public boolean shouldStorageIntake() {
    return STORAGE.getIntakeSwitch() && !STORAGE.isOverridden();
  }

  // Storage
  public final StartEndCommand storageOverride = new StartEndCommand(() -> STORAGE.startFeeding(), () -> STORAGE.stopFeeding(),
      STORAGE);
  public final InstantCommand startStorageOverride = new InstantCommand(() -> STORAGE.override());

  public final StoreBall storeBall = new StoreBall(STORAGE);

  // STORAGE TRIGGER
  private final StorageLimitSwitchTrigger storageTrigger = new StorageLimitSwitchTrigger(this);

  // === AUTO === //
  private final InstantCommand doNothing = new InstantCommand();
  private final Command moveForward = new MoveCommand(DRIVETRAIN, 20, .5);
  private final TestAutoCommandGroup debugAuto = new TestAutoCommandGroup(DRIVETRAIN, VISION);
  private final FailsafeAuto failsafe = new FailsafeAuto(DRIVETRAIN, SHOOTER, STORAGE);
  private final TrenchAuto trench = new TrenchAuto(DRIVETRAIN, SHOOTER, STORAGE, INTAKE);

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

    // Shooter
    toggleShooterButton.toggleWhenActive(new EnableShooter(SHOOTER, STORAGE));
    shootButton.whenHeld(new ShootCommand(SHOOTER, STORAGE, LONG_DIST_RPM));

    shortShotButton.whenPressed(setShooterFar.withTimeout(1));
    shortShotButton.whenHeld(revShort);

    longShotButton.whenPressed(setShooterClose.withTimeout(1));
    shortShotButton.whenHeld(revLong);

    // Vision
    visionAlignBall.whenHeld(new BallTrack(DRIVETRAIN, VISION));
    visionAlignGoal.whenHeld(new GoalTrack(DRIVETRAIN, VISION));

    // Drivetrain
    modeSwitchButton.whenHeld(modeSwitch);

    // Intake
    intakeButton.whileHeld(new SequentialCommandGroup(intakeCommand, new StartEndCommand(() -> {
    }, () -> {
      INTAKE.pistonOff();
    }, INTAKE).withTimeout(1)));

    outttakeButton.whileHeld(outtakeCommand);

    raiseIntakeButton.whenPressed(retractIntake.withTimeout(1));

    // STORAGE
    storageTrigger.whenActive(storeBall);
    storageOverrideButton.whenPressed(startStorageOverride);
    storageOverrideButton.whenHeld(storageOverride);
  }

  public void addAutosToChooser(SendableChooser<Command> chooser) {
    chooser.setDefaultOption("Do Nothing", doNothing);
    chooser.addOption("Move 20\"", moveForward);
    chooser.addOption("Failsafe", failsafe);
    chooser.addOption("Trench", trench);
    chooser.addOption("Test Auto", debugAuto);
  }
}
