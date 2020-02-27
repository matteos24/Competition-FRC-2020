/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.commands.*;
import frc.robot.commands.DisableShooter;
import frc.robot.commands.EnableShooter;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;

import static frc.robot.Constants.*;

public class TrenchAuto extends SequentialCommandGroup {
  /**
   * Creates a new TrenchAuto.
   */
  public TrenchAuto(Drivetrain drivetrain, Shooter shooter, Storage storage, Intake intake) {
    
    // move forward 160 inches and intake
    // enable shooter
    // turn 165 right
    // shoot
    // party

    super(
      parallel(
        new MoveCommand(drivetrain, 160, 0.75).alongWith(
          new RunCommand(() -> { intake.setSpeed(WHEEL_INTAKE_SPEED); intake.deployPistons(); }, intake)
        )
      ),
      parallel(new EnableShooter(shooter), new StartEndCommand(() -> { shooter.setAngleForward(); }, () -> { shooter.setPistonsOff(); }, shooter).withTimeout(1)),
      new TurnCommand(drivetrain, 165, 0.75),
      new ShootCommand(shooter, storage, 5500).withTimeout(3 + 5 + 2), // 3 to spool, 1 per ball, 3 for safety
      new DisableShooter(shooter)
    );
  }
}
