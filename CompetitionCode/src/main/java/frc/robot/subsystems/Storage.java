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
import static frc.robot.Constants.*;

// Ball storage: this class controls the gate opening onto the shooter. 
// It could also be an extra motor: that much is unclear. 

  // TODO: make gate button part of shooter?

public class Storage extends SubsystemBase {
  // FIELDS
  private VictorSP motor;
  private DigitalInput limit1, limit2, limit3, limit4, limit5;

  public Storage() {
    motor = new VictorSP(STORAGE_GATE_MOTOR);
    DigitalInput limit1 = new DigitalInput(1);
    DigitalInput limit2 = new DigitalInput(2);
    DigitalInput limit3 = new DigitalInput(3);
    DigitalInput limit4 = new DigitalInput(4);
    DigitalInput limit5 = new DigitalInput(5);
 
  }

  /**
   * Returns Int of the greatest affected limit switch; 0 if none are affected
   */

   public int limitSwitchAffected() {
    if(limit5.get() == true) {
      return 5;
    } else if(limit4.get() == true) {
      return 4;
    } else if(limit3.get() == true) {
      return 3;
    } else if(limit2.get() == true) {
      return 2;
    } else if(limit1.get() == true) {
      return 1;
    }
    return 0;
   }

  /**
   * Sets the storage motor (gate) to 0.3 (GATE_SPEED in constants)
   */
  public void gateSpeed() {
    motor.set(GATE_SPEED);
  }

  /**
   * Sets the speed of the storage motor (gate) to 0 for stopping
   */
  public void gateZero() {
    motor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
