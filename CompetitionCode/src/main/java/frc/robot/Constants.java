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
    public static final int SHORT_DISTANCE_RPM = 5500; //TODO: Change the numbers
    public static final int LONG_DIST_RPM = 5500; //TODO: Change the numbers
    public static final int SHOOTER_REV_TIME = 3000; // in ms

    // RESERVED SHOOTER MOTOR PORTS: [0, 1]
    public static final int SHOOTER_MOTOR_1 = 0;
    public static final int SHOOTER_MOTOR_2 = 1;

    // RESERVED SHOOTER SOLENOID PORTS: [0, 0 + 1]
    public static final int SHOOTER_PISTON_A = 0;
    public static final int SHOOTER_PISTON_B = SHOOTER_PISTON_A + 1;

  // === VISION TRACKING CONSTANTS === //

  //THE CONSTANTS WITH VALUE 0 ARE CURRENTLY UNKNOWN 
  public static final int DIFFERENCE_BETWEEN_SHOOTER_ANGLE_AND_CAM_ANGLE = 0;
  public static final int HORIZONTAL_FOV = 60;
  public static final int VERTICAL_FOV = 40;
  public static final int HEIGHT_OF_SHOOTER = 0;
  public static final int DISTANCE_DIFFERENCE = 0;
  public static final double HEIGHT_OF_CAM = 24.7401; // IN INCHES
  public static final int MAX_VELOCITY_OF_SHOOTER = 1;

  // === PIXY SIGs === //

  public static enum Signature {

    GOAL_BOTTOM_LINE(2), POWER_CELL(-1);

    int sig = -1;
    Signature(int sig) {
      this.sig = sig;
    }

    public int value() { return sig; }
  }

  // === MOTOR PORTS === //

      // DRIVE MOTORS NON COMMENTED- 1797 COMMENTED - 1884 
  public static final int FRONT_LEFT_DRIVE_PORT = 1; // 2
  public static final int BACK_LEFT_DRIVE_PORT = 3; // 1
  public static final int FRONT_RIGHT_DRIVE_PORT = 2; // 0
  public static final int BACK_RIGHT_DRIVE_PORT = 0; // 3

      // INTAKE MOTORS
  public static final int WHEEL_INTAKE_PORT = 0;
  
    // CLIMBER MOTORS
  public static final int LIFTER_MOTOR = 9;
  public static final int GEAR_MOTOR1 = 7;
  public static final int GEAR_MOTOR2 = 8;


  // === PISTON PORTS === //

      // INTAKE PISTONS
  public static final int INTAKE_PISTON_PORT_1 = 2;
  public static final int INTAKE_PISTON_PORT_2 = 3;

  public static final int SHOOTER_PISTON_PORT_1 = 4;
  public static final int SHOOTER_PISTON_PORT_2 = 5;

      // STORAGE MOTORS
  public static final int STORAGE_GATE_MOTOR_PORT = 13;



  // === SENSOR PORTS === //

      // STORAGE LIMIT SWITCHES
  public static final int STORAGE_INTAKE_SWITCH_PORT = 0;
  public static final int STORAGE_BALL_SWITCH_PORT = 1;
  public static final int STORAGE_OVERRIDE_SWITCH_PORT = 2;




  // === SPEED CONSTANTS === //

  // INTAKE
  public static final double DEPLOY_INTAKE_SPEED = 0.5;
  public static final double WHEEL_INTAKE_SPEED = 0.75;

  // STORAGE
  public static final double FEED_SPEED = 0.2;

  // CLIMBER
  public static final double LIFTER_SPEED = 1;
  public static final double LIFTER_SPEED_REVERSE = -1;
  public static final double GEAR_SPEED = 0.75;


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

