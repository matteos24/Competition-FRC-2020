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

  private WPI_TalonFX frontLeft, frontRight, backLeft, backRight;

  private SpeedControllerGroup left, right;

  private double speedMultiplier = 1;

  private boolean isFast = true;

  public Drivetrain() {
    frontLeft = new WPI_TalonFX(FRONT_LEFT_DRIVE_PORT);
    backLeft = new WPI_TalonFX(BACK_LEFT_DRIVE_PORT);
    frontRight = new WPI_TalonFX(FRONT_RIGHT_DRIVE_PORT);
    backRight = new WPI_TalonFX(BACK_RIGHT_DRIVE_PORT);

    left = new SpeedControllerGroup(frontLeft, backLeft);
    right = new SpeedControllerGroup(frontRight, backRight);
  }

  public void resetEncoders() {
    frontLeft.setSelectedSensorPosition(0, 0, 0);
    backLeft.setSelectedSensorPosition(0, 0, 0);
    frontRight.setSelectedSensorPosition(0, 0, 0);
    backRight.setSelectedSensorPosition(0, 0, 0);
  }

  // SPEED MODES

  public void modeSlow() {
    //speedMultiplier = 0.25;
    isFast = false;
  }

  public void modeFast() {
    //speedMultiplier = 1;
    isFast = true;
  }

  // MOTOR SPEEDS

  public void tankDrive(double leftSpeed, double rightSpeed) {
    setLeftSpeed(leftSpeed);
    setRightSpeed(-rightSpeed);
  }

  /**
   * X is horizontal, Z is vertical
   */
  public void arcadeDrive(double x, double z) {
    x *= Math.abs(x * x) * (isFast ? 1.0 : 0.35);
    z *= Math.abs(z * z) * 0.3;

    tankDrive(x + z, x - z);
  }

  private void setLeftSpeed(double speed) {
    left.set(speed * speedMultiplier);
    //System.out.println(speed * speedMultiplier);
  }

  private void setRightSpeed(double speed) {
    right.set(speed * speedMultiplier);
  }

  public double getLeftDistance() {
    return (frontLeft.getSelectedSensorPosition(0) + backLeft.getSelectedSensorPosition(0)) / 2.;
  }

  public double getRightDistance() {
    return -(frontRight.getSelectedSensorPosition(0) + backRight.getSelectedSensorPosition(0)) / 2.;
  }

  public double getAverageDistance () {
    return (getLeftDistance() + getRightDistance()) / 2.;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}