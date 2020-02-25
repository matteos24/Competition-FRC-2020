/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public final class Constants {

    // === SHOOTER === //

    public static final int DEFAULT_TARGET_RPM = 5000;

    // RESERVED SHOOTER MOTOR PORTS: [0, 0]
    public static final int SHOOTER_MOTOR_1 = 0;
    public static final int SHOOTER_MOTOR_2 = 1;

    // RESERVED SHOOTER SOLENOID PORTS: [0, 0 + 1]
    public static final int SHOOTER_PISTON_A = 0;
    public static final int SHOOTER_PISTON_B = SHOOTER_PISTON_A + 1;
  // === VISION TRACKING CONSTANTS === //

  //THE CONSTANTS WITH VALUE 0 ARE CURRENTLY UNKNOWN 
  public static final int DIFFERENCE_BETWEEN_SHOOTER_ANGLE_AND_CAM_ANGLE = 0;
  public static final int CAMERA_X = 315;
  public static final int HORIZONTAL_TOTAL_INT = 0;
  public static final int CAMERA_Y = 207;
  public static final int VERTICAL_TOTAL_INT = 0;
  public static final int HEIGHT_OF_SHOOTER = 0;
  public static final int DISTANCE_DIFFERENCE = 0;
  public static final int HEIGHT_OF_CAM = 0;
  public static final int MAX_VELOCITY_OF_SHOOTER = 1;

  // === PIXY SIGs === //
  public static final int SHOOTER_TAPE_SIG = 3;
  public static final int POWER_CELL_SIG = 2;

  // === MOTOR PORTS === //

      // DRIVE MOTORS NON COMMENTED- 1797 COMMENTED - 1884 
  public static final int FRONT_LEFT_DRIVE_PORT = 1; // 2
  public static final int BACK_LEFT_DRIVE_PORT = 3; // 1
  public static final int FRONT_RIGHT_DRIVE_PORT = 2; // 0
  public static final int BACK_RIGHT_DRIVE_PORT = 0; // 3

      // INTAKE MOTORS
  public static final int WHEEL_INTAKE_PORT = 0;



  // === PISTON PORTS === //

      // INTAKE PISTONS
  public static final int INTAKE_PISTON_PORT_1 = 2;
  public static final int INTAKE_PISTON_PORT_2 = 3;

      // STORAGE MOTORS
  public static final int STORAGE_GATE_MOTOR_PORT = 13;



  // === SENSOR PORTS === //

      // STORAGE LIMIT SWITCHES
  public static final int STORAGE_INTAKE_SWITCH_PORT = 0;
  public static final int STORAGE_BALL_SWITCH_PORT = 1;



  // === SPEED CONSTANTS === //

  // INTAKE
  public static final double DEPLOY_INTAKE_SPEED = 0.5;
  public static final double WHEEL_INTAKE_SPEED = 0.75;

  // STORAGE
  public static final double GATE_SPEED = 0.2;



  // == JOYSTICK CONSTANTS == //

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

  // BUTTONS (for xbox controller)
  public static final int BUTTON_A = 1;
  public static final int BUTTON_B = 2;
  public static final int BUTTON_X = 3;
  public static final int BUTTON_Y = 4;
  public static final int LEFT_BUMPER = 5;
  public static final int RIGHT_BUMPER = 6;
  public static final int LEFT_TRIGGER = 7;
  public static final int RIGHT_TRIGGER = 8;
  public static final int BACK_BUTTON = 9;
  public static final int START_BUTTON = 10;
  public static final int LEFT_STICK_BUTTON = 11;
  public static final int RIGHT_STICK_BUTTON = 12;

}
