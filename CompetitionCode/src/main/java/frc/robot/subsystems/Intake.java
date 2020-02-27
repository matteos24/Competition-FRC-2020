/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake extends SubsystemBase {

  private VictorSP wheelMotor;
  private DoubleSolenoid intakePiston;

  public Intake() {
    wheelMotor = new VictorSP(WHEEL_INTAKE_PORT);
    //intakePiston = new DoubleSolenoid(INTAKE_PISTON_PORT_1, INTAKE_PISTON_PORT_2);
  }

  // MOTORS & PISTONS

  public void setSpeed(double speed) {
    wheelMotor.set(speed);
  }

  public void deployPiston() {
    intakePiston.set(DoubleSolenoid.Value.kForward);
  }

  public void retractPiston() {
    intakePiston.set(DoubleSolenoid.Value.kReverse);
  }

  public void pistonOff() {
    intakePiston.set(DoubleSolenoid.Value.kOff);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
