/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.EnableShooterCommand;
import frc.robot.subsystems.*;
public class StraightShootAuto extends SequentialCommandGroup {
  
  public StraightShootAuto(Drivetrain drivetrain, Vision vision, Shooter shooter) {
    super(
            // Shoots from starting line then goes backwards
            parallel(new EnableShooterCommand(shooter) /*, new ShootCommand() */),
            new MoveCommand(drivetrain, -20, 0.5)
            );
}
}
