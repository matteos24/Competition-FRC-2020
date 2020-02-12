/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.*;

//TODO- make gate part of shooter command
// It could also be an extra motor: that much is unclear. 

/**
 * Controls the gate opening for Shooter consumption
 */
public class Storage extends SubsystemBase {
  // FIELDS
  private final VictorSP motor;
  private final DigitalInput[] limitSwitches;
  private boolean isOverridden;

  public Storage() {
    motor = new VictorSP(STORAGE_GATE_MOTOR);
    isOverridden = false;

    limitSwitches = new DigitalInput[6];
    for (int i = 0; i <= 5; i++) {
      limitSwitches[i] = new DigitalInput(i);
    }

  }

  public boolean isSwitchPressed(final int index) {
    return limitSwitches[index].get();
  }

  public DigitalInput getFirstLimit() {
    return limitSwitches[0];
  }

  public int getBallsInStorage() {
    for (int i = 5; i > 0; i--) {
      if (limitSwitches[i].get())
        return i;
    }
    return 0;
  }

  /**
   * Sets the storage motor (gate) to 0.3 (GATE_SPEED in constants)
   */
  public void setGateSpeed() {
    motor.set(GATE_SPEED);
  }

  /**
   * Sets the speed of the storage motor (gate) to 0 for stopping
   */
  public void stop() {
    motor.set(0);
  }

  public void override() {
    isOverridden = true;
  }

  public boolean isOverridden() {
    return isOverridden;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Intake Start", limitSwitches[0].get()); //TODO- why?

    int count = 0;
    for (int i = 1; i <= 5; i++) count += limitSwitches[i].get() ? 1 : 0;
    SmartDashboard.putNumber("# Balls in Storage", count);
      
  }
}
