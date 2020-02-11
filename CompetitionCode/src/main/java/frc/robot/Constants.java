/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

  // === ROBOT PORTS === //

  // INTAKE MOTORS
  public static final int WHEEL_INTAKE_MOTOR = 0;
  
  // INTAKE PISTONS
  public static final int INTAKE_PISTON_1 = 2;
  public static final int INTAKE_PISTON_2 = 3;

  public static final int INTAKE_PISTON_BUTTON= 1;
  public static final int INTAKE_MOTOR_BUTTON = 2;
  public static final int OUTTAKE_MOTOR_BUTTON = 3;
  
  // === SPEED CONSTANTS === //
  
  // INTAKE
  public static final double DEPLOY_INTAKE_SPEED = 0.5;
  public static final double WHEEL_INTAKE_SPEED = 0.5;

  // == BUTTONS == //
  public static final int BUTTON_A = 1, BUTTON_B = 2, BUTTON_Y = 4, BUTTON_X = 3; 
  public static final int LEFT_BUMPER = 5, RIGHT_BUMPER = 6;

  // === ROBOT PORTS === //

  // DRIVE MOTORS
  public static final int FRONT_LEFT_DRIVE_MOTOR = 1;
  public static final int BACK_LEFT_DRIVE_MOTOR = 3;
  public static final int FRONT_RIGHT_DRIVE_MOTOR = 2;
  public static final int BACK_RIGHT_DRIVE_MOTOR = 0;

  // === CONTROLLERS === //
  
  // STICKS
  public static final int FORWARD_AXIS_LEFT = 4;
  public static final int HORIZ_AXIS_RIGHT = 1;

  // CONTROLLERS
  public static final int DRIVER_CONTROLLER = 0;
  public static final int OPERATOR_CONTROLLER = 1;
  
}

