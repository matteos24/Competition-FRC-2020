/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public final class Constants {

  // === ROBOT PORTS === //

  // DRIVE MOTORS
  public static final int FRONT_LEFT_DRIVE_MOTOR = 1;
  public static final int BACK_LEFT_DRIVE_MOTOR = 3;
  public static final int FRONT_RIGHT_DRIVE_MOTOR = 2;
  public static final int BACK_RIGHT_DRIVE_MOTOR = 0;
  
  // INTAKE MOTORS
  public static final int WHEEL_INTAKE_MOTOR = 0;
  
  // INTAKE PISTONS
  public static final int INTAKE_PISTON_1 = 2;
  public static final int INTAKE_PISTON_2 = 3;

  public static final int INTAKE_PISTON_BUTTON= 1;
  public static final int INTAKE_MOTOR_BUTTON = 2;
  public static final int OUTTAKE_MOTOR_BUTTON = 3;
  
  // STORAGE MOTOR
  public static final int STORAGE_GATE_MOTOR = 13;

  // STORAGE
  public static final double GATE_SPEED = 0.2;
  

  // === SPEED CONSTANTS === //
  
  // INTAKE
  public static final double DEPLOY_INTAKE_SPEED = 0.5;
  public static final double WHEEL_INTAKE_SPEED = 0.5;

   // === CONTROLLERS === //

  // CONTROLLERS
  public static final int DRIVER_CONTROLLER = 0;
  public static final int OPERATOR_CONTROLLER = 1;

  // STICKS (for xbox controler)
  public static final int HORIZ_AXIS_LEFT = 0;
  public static final int FORWARD_AXIS_LEFT = 1;
  public static final int HORIZ_AXIS_RIGHT = 4;
  public static final int FORWARD_AXIS_RIGHT = 5;

  public static final int LEFT_TRIGGER_AXIS = 2;
  public static final int RIGHT_TRIGGER_AXIS = 3;

  // CONTROLLER BUTTONS (for xbox controller)
  public static final int BUTTON_A = 1;
  public static final int BUTTON_B = 2;
  public static final int BUTTON_X = 3;
  public static final int BUTTON_Y = 4;
  public static final int LEFT_BUMPER = 5;
  public static final int RIGHT_BUMPER = 6;
  public static final int BACK_BUTTON = 7;
  public static final int START_BUTTON = 8;
  public static final int LEFT_STICK_BUTTON = 9;
  public static final int RIGHT_STICK_BUTTON = 10;
  
}

