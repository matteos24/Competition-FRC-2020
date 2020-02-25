/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class Shooter extends SubsystemBase {

  public enum State {
    SPIN_UP, TUNE, HOLD
  }

  // TWO MOTORS IN GEARBOX- SAME DIRECTION
  // TWO PISTONS IN PARALLEL- PRESUMABLY ONE D.SOLENOID

  private WPI_TalonSRX motor1, motor2;
  private DoubleSolenoid anglePiston;

  // LOCAL VARIABLES
  private double speed = 0.0;
  private State state = State.SPIN_UP;

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    motor1 = new WPI_TalonSRX(SHOOTER_MOTOR_1);
    motor2 = new WPI_TalonSRX(SHOOTER_MOTOR_2);

    motor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    motor2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 1, 10);
  }

  /**
   * Sets motor speed
   * @param speed [-1.0, 1.0]
   */
  public void setSpeed(double speed) {
    motor1.set(speed);
    motor2.set(speed);
  }

  /**
   * Sets the speed of the Shooter System (good for disabling the shooter)
   * Please ensure no other command is calling setSpeedWithRPM().
   * @param speed [-1.0, 1.0]
   */
  public void setSystemSpeed(double speed) {
    this.speed = speed;
  }

  /**
   * Gets motor speed
   * @return speed [-1.0, 1.0]
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * Gets current state. [SPIN_UP, TUNE, HOLD]
   */
  public State getCurrentState() {
    return this.state;
  }

  /**
   * Changes motor state based off RPM
   * @param targetRPM
   */
  private void checkState(double targetRPM) {
    double diff = Math.abs(getMotorSpeed() - targetRPM);

    if (diff > 300)
      state = State.SPIN_UP;
    if (diff < 300 && diff > 100)
      state = State.TUNE;
    if (diff < 100)
      state = State.HOLD;
  }

  /**
   * Sets the motor speeds based on target speed.
   * @param targetRPM Target RPM
   */
  public void setSpeedWithRPM(double targetRPM) {
    double rpm = getMotorSpeed();
    checkState(targetRPM);

    if (state == State.SPIN_UP) {
      speed += ((targetRPM - rpm) / targetRPM) / 50;
    } else if (state == State.TUNE) {
      speed += ((targetRPM - rpm) / targetRPM) / 100;
    } else if (state == State.HOLD) {
      speed += ((targetRPM - rpm) / targetRPM) / 200;
    }

    if (speed > 1)
      speed = 1;
    if (speed < -1)
      speed = -1;
    setSpeed(-speed);
  }

  /**
   * Gets the current motor speed in RPM.
   */
  public double getMotorSpeed() {
    return 600. * (Math.abs((motor1.getSelectedSensorVelocity(0) + motor2.getSelectedSensorVelocity(1)) / 2.) / 4096.);
  }

  public void setPistonsForward(){ anglePiston.set(DoubleSolenoid.Value.kForward);  }
  public void setPistonsReverse(){ anglePiston.set(DoubleSolenoid.Value.kReverse);  }
  public void setPistonsOff(){ anglePiston.set(DoubleSolenoid.Value.kOff);  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
