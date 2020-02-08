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
    // == VISION TRACKING CONSTANTS == //
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
    public static final Color GOAL_COLOR = new Color(0, 1, 0);

    // === SHOOTER === //

    public static final int DEFAULT_TARGET_RPM = 5000;

    // RESERVED SHOOTER MOTOR PORTS: [0, 0]
    public static final int SHOOTER_MOTOR_1 = 0;
    public static final int SHOOTER_MOTOR_2 = 1;

    // RESERVED SHOOTER SOLENOID PORTS: [0, 0 + 1]
    public static final int SHOOTER_PISTON_A = 0;
    public static final int SHOOTER_PISTON_B = SHOOTER_PISTON_A + 1;

    // == BUTTONS == //
    public static final int MODE_SWITCH_BUTTON = 6; // RIGHT BUMPER

    // === ROBOT PORTS === //

    // DRIVE MOTORS
    public static final int FRONT_LEFT_DRIVE_MOTOR = 1;
    public static final int BACK_LEFT_DRIVE_MOTOR = 3;
    public static final int FRONT_RIGHT_DRIVE_MOTOR = 2;
    public static final int BACK_RIGHT_DRIVE_MOTOR = 0;

    // === CONTROLLERS === //
    
    // STICKS
    public static final int FORWARD_AXIS_LEFT = 1;
    public static final int HORIZ_AXIS_RIGHT = 4;

    // CONTROLLERS
    public static final int DRIVER_CONTROLLER = 0;
    public static final int OPERATOR_CONTROLLER = 1;

    // BUTTONS
    public static final int BUTTON_A = 1, BUTTON_B = 2, BUTTON_Y = 4, BUTTON_X = 3; 
    public static final int LEFT_BUMPER = 5, RIGHT_BUMPER = 6;
  
}