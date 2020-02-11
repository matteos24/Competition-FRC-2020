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

// Ball storage: this class controls the gate opening onto the shooter. 
// It could also be an extra motor: that much is unclear. 

  // TODO: make gate button part of shooter?

public class Storage extends SubsystemBase {
  // FIELDS
  private final VictorSP motor;
  private final DigitalInput[] limit;
  private boolean isOverrided;

  public Storage() {
    motor = new VictorSP(STORAGE_GATE_MOTOR);
    isOverrided = false;

    limit = new DigitalInput[6];
    for(int i=0; i<=5; i++){
      limit[i] = new DigitalInput(i);
    }
 
  }

  public boolean limitPressed(final int index) {
    return limit[index].get();
  }
  
  // public DigitalInput getStartLimit(){
  //   return limit[0];
  // }

  public int getCurrentStage(){
    for(int i=5; i>0; i--){
      if(limit[i].get()) return i;
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

  public void override(){
    isOverrided = true;
  }
  public boolean isOverrided(){
    return isOverrided;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("intake start", limit[0].get());
    for(int i=1; i<=5; i++) SmartDashboard.putBoolean("has ball "+i, limit[i].get());
  }
}
