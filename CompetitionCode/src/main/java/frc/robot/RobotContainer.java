
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
import frc.robot.commands.EnableShooterCommand;
import frc.robot.commands.GoalTrack;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.StoreBall;
import frc.robot.commands.auto.FailsafeAuto;
import frc.robot.commands.auto


.MoveCommand;
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
  public final JoystickButton modeSwitchButton = new JoystickButton(driver, RIGHT_BUMPER);

  public final JoystickButton motorIntakeButton = new JoystickButton(operator, BUTTON_X),
                               motorOuttakeButton = new JoystickButton(operator, BUTTON_Y);

  public final JoystickButton storageOverrideButton = new JoystickButton(operator, START_BUTTON);
  public final JoystickButton visionTestButton = new JoystickButton(driver, 1);
  public final JoystickButton visionGoalButton = new JoystickButton(driver, 3);

  public JoystickButton toggleShooterButton = new JoystickButton(operator, RIGHT_BUMPER);
  public JoystickButton shootButton = new JoystickButton(operator, RIGHT_TRIGGER);
  public JoystickButton longShotButton = new JoystickButton(operator, 0);
  public JoystickButton shortShotButton = new JoystickButton(operator, 1);
  
  private final JoystickButton raiseButton = new JoystickButton(operator, LEFT_BUMPER),
                                lowerButton = new JoystickButton(operator, RIGHT_BUMPER),
                                 gearClimbButton = new JoystickButton(operator, BACK_BUTTON);

  // SUBSYSTEMS
  public final Drivetrain DRIVETRAIN = new Drivetrain();
  public final Intake INTAKE = new Intake();
  public final Storage STORAGE = new Storage();
  public final Shooter SHOOTER = new Shooter();
  public final Vision VISION = new Vision();
  public final Climber CLIMBER = new Climber();

  // COMMANDS

  public final StartEndCommand shooterPistonOut = new StartEndCommand(
    () -> {
      SHOOTER.setAngleForward();
    },
    () -> {
      SHOOTER.setPistonsOff();
    }, 
    SHOOTER);
  
  public final StartEndCommand revShort = new StartEndCommand(
    () -> {
      SHOOTER.setSpeedWithRPM(Constants.SHORT_DISTANCE_RPM);
    },
    () -> {
      SHOOTER.setSpeedWithRPM(0);
    }, 
    SHOOTER);

  public final StartEndCommand shooterPistonIn = new StartEndCommand(
    () -> {
      SHOOTER.setAngleBack();
    },
    () -> {
      SHOOTER.setPistonsOff();
    }, 
    SHOOTER);

    public final StartEndCommand revLong = new StartEndCommand(
      () -> {
        SHOOTER.setSpeedWithRPM(Constants.LONG_DIST_RPM); //TODO: change to a an rpm based off current distance from target
      },
      () -> {
        SHOOTER.setSpeedWithRPM(0);
      }, 
      SHOOTER);

  // INTAKE //
  public final StartEndCommand modeSwitch = new StartEndCommand(() -> DRIVETRAIN.modeSlow(),
      () -> DRIVETRAIN.modeFast(), DRIVETRAIN);

  public final StartEndCommand deployIntake = new StartEndCommand(() -> { 
    INTAKE.deployPistons();
    },
    () -> { 
      INTAKE.pistonOff();
    },
      INTAKE);

  public final StartEndCommand retractIntake = new StartEndCommand(() -> { 
    INTAKE.retractPistons();
      },
      () -> { 
        INTAKE.pistonOff();
      },
      INTAKE);

  public final StartEndCommand intakeCommand = new StartEndCommand(
    () -> {     
      INTAKE.setSpeed(WHEEL_INTAKE_SPEED); 
      INTAKE.deployPistons();
    },
    () -> { 
      INTAKE.setSpeed(0);
      INTAKE.retractPistons();
    },
    INTAKE
  );

  public final StartEndCommand outtakeCommand = new StartEndCommand(() -> INTAKE.setSpeed(-WHEEL_INTAKE_SPEED),
      () -> INTAKE.setSpeed(0), INTAKE);
  
  // CLIMBER COMMANDS //
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


  // for storage trigger
  public boolean shouldStorageIntake() {
    return STORAGE.getIntakeSwitch() && !STORAGE.isOverridden();
  }

  // STORAGE COMMANDS
  public final StartEndCommand storageOverride = new StartEndCommand(() -> STORAGE.setGateSpeed(), () -> STORAGE.stop(),
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

    toggleShooterButton.toggleWhenActive(new EnableShooterCommand(SHOOTER));
    shootButton.whenHeld(new ShootCommand(SHOOTER, STORAGE, LONG_DIST_RPM));
    //Extend piston to short range setting and begin revving shooter motors
    shortShotButton.whenPressed(shooterPistonOut.withTimeout(1)); //TODO: change to time taken for piston to extend
    shortShotButton.whenHeld(revShort);
    //Extend piston to long range setting and begin revving shooter motors
    longShotButton.whenPressed(shooterPistonIn);
    shortShotButton.whenHeld(revLong);
    
     visionTestButton.whileHeld(new BallTrack(DRIVETRAIN, VISION));
     visionGoalButton.whileHeld(new GoalTrack(DRIVETRAIN, VISION));

    modeSwitchButton.whenHeld(modeSwitch);
    
    motorIntakeButton.whileHeld(new SequentialCommandGroup(intakeCommand, new StartEndCommand(() -> {}, () -> { INTAKE.pistonOff(); }, INTAKE).withTimeout(1)));
    motorOuttakeButton.whileHeld(new SequentialCommandGroup(outtakeCommand, new StartEndCommand(() -> {}, () -> { INTAKE.pistonOff(); }, INTAKE).withTimeout(1)));
    
    // CLIMB BUTTONS
    raiseButton.whenHeld(raiseLifter);
    lowerButton.whenHeld(lowerLifter);
    gearClimbButton.whenHeld(gearClimb);

    // STORAGE
    storageTrigger.whenActive(storeBall);
    // storageOverrideButton.whenPressed(startStorageOverride);
    // storageOverrideButton.whenHeld(storageOverride);
  }

  // /**
  //  * Use this to pass the autonomous command to the main {@link Robot} class.
  //  *
  //  * @return the command to run in autonomous
  //  */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return autoCommandGroup;
  // }

  public void addAutosToChooser(SendableChooser<Command> chooser){
    chooser.setDefaultOption("Do Nothing", doNothing);
    chooser.addOption("Move 20\"", moveForward);
    chooser.addOption("Failsafe", failsafe);
    chooser.addOption("Trench", trench);
    chooser.addOption("Test Auto", debugAuto);
    // TODO: add all autos here

  }

  public Drivetrain getDrivetrain() {
    return this.DRIVETRAIN;
  }
}
