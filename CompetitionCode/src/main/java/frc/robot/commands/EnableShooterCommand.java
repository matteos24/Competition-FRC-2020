/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class EnableShooterCommand extends CommandBase {

  private Shooter shooter;
  private double targetRPM = Constants.DEFAULT_TARGET_RPM;

  /**
   * Creates a new EnableShooterCommand.
   */
  public EnableShooterCommand(Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.setSpeedWithRPM(targetRPM);
    SmartDashboard.putNumber("Target RPM", targetRPM);
    SmartDashboard.putNumber("Current RPM", shooter.getMotorSpeed());
    SmartDashboard.putBoolean("Ready To Fire?", Math.abs((shooter.getMotorSpeed() - targetRPM)) < 150);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.setSystemSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
