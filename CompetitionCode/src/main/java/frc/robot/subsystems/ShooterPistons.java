/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class ShooterPistons extends SubsystemBase {

  // TWO PISTONS IN PARALLEL
  private DoubleSolenoid anglePiston;

  // LOCAL VARIABLES
  private boolean longRange = true;

  /**
   * Creates a new Shooter.
   */
  public ShooterPistons() {
    anglePiston = new DoubleSolenoid(SHOOTER_PISTON_A, SHOOTER_PISTON_B);
  }

  /**
   * Identifies whether the shooter is set to long range or close range
   */

  public boolean getRange(){
    return longRange;
  }

  public void setLongRange(){
    longRange = true;
  }

  public void setShortRange(){
    longRange = false;
  }

  public void setPistonsForward(){ anglePiston.set(DoubleSolenoid.Value.kForward);  }
  public void setPistonsReverse(){ anglePiston.set(DoubleSolenoid.Value.kReverse);  }
  public void setPistonsOff(){ anglePiston.set(DoubleSolenoid.Value.kOff);  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
