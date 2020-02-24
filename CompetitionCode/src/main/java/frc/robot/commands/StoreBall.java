/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Storage;

public class StoreBall extends CommandBase {
  /**
   * Creates a new StorageCommand.
   */
  private boolean movedOn;
  private Storage storage;

  public StoreBall(Storage s) {
    // Use addRequirements() here to declare subsystem dependencies.
    storage = s;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(storage.numBalls < 5) storage.setGateSpeed();
    movedOn = false;
    storage.numBalls++;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!storage.hasBall()) movedOn = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    storage.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(storage.numBalls > 5) return true;
    return movedOn && storage.hasBall();
  }
}
