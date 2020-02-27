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


/**
 * Controls the gate opening for Shooter consumption
 */
public class Storage extends SubsystemBase {
  // FIELDS
  private final VictorSP motor;
  private final DigitalInput intakeSwitch, hasBallSwitch, overrideSwitch;
  private boolean isOverridden;
  public int numBalls;

  public Storage() {
    motor = new VictorSP(STORAGE_GATE_MOTOR_PORT);
    isOverridden = false;

    intakeSwitch = new DigitalInput(STORAGE_INTAKE_SWITCH_PORT);
    hasBallSwitch = new DigitalInput(STORAGE_BALL_SWITCH_PORT);
    overrideSwitch = new DigitalInput(STORAGE_OVERRIDE_SWITCH_PORT);

  }

  /**
   * intakeSwitchSwitch getter method
   */
  public boolean getIntakeSwitch() {
    return intakeSwitch.get();
  }

  /**
   * hasBallSwitch getter method
   */
  public boolean hasBall() {
    return hasBallSwitch.get();
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
    if(overrideSwitch.get() == true){
      return overrideSwitch.get();
    };
    return isOverridden;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Intake Switch", intakeSwitch.get());
    SmartDashboard.putBoolean("Has Ball Switch", hasBallSwitch.get());
          
  }
}
