/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//hi
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Drivetrain extends SubsystemBase {

  public static final double MM_TO_IN = 0.0393701;
  public static final double WHEEL_TO_WHEEL_DIAMETER_INCHES = 320 * MM_TO_IN;
  public static final double WHEEL_DIAMETER_INCHES = 4;

  public static final double PULSES_PER_ROTATION = 256;
  
  private WPI_TalonFX frontLeft, frontRight, backLeft, backRight;

  private SpeedControllerGroup left, right;

  private double speedMultiplier = 1;
  private boolean isFast = true;


  public Drivetrain() {
    frontLeft = new WPI_TalonFX(FRONT_LEFT_DRIVE_MOTOR);
    backLeft = new WPI_TalonFX(BACK_LEFT_DRIVE_MOTOR);
    frontRight = new WPI_TalonFX(FRONT_RIGHT_DRIVE_MOTOR);
    backRight = new WPI_TalonFX(BACK_RIGHT_DRIVE_MOTOR);

    left = new SpeedControllerGroup(frontLeft, backLeft);
    right = new SpeedControllerGroup(frontRight, backRight);
  }

  public void modeSlow(){
    speedMultiplier = 0.25;
    isFast = false;
  }

  public void modeFast(){
    speedMultiplier = 1;
    isFast = true;
  }

  public void tankDrive(double leftSpeed, double rightSpeed){
    setLeftSpeed(leftSpeed);
    setRightSpeed(-rightSpeed);
  }

  /**
   * X is horizontal, Z is vertical
   */
  public void arcadeDrive(double x, double z){
    x *= Math.abs(x * x);
    z *= Math.abs(z * z);
    x *= (isFast ? 0.35 : 0.9);
    z *= 0.3;
    tankDrive(x + z, x - z);
  }

  private void setLeftSpeed(double speed) {
    left.set(speed * speedMultiplier);
  }

  private void setRightSpeed(double speed) {
    right.set(speed * speedMultiplier);
  }

  public double getLeftDistance() {
    return (frontLeft.getSelectedSensorPosition() + backLeft.getSelectedSensorPosition()) / 2;
  }

  public double getRightDistance() {
    return (frontRight.getSelectedSensorPosition() + backRight.getSelectedSensorPosition()) / 2;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}