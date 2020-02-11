/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {  

  // === ROBOT PORTS === //



  // STORAGE MOTOR
  public static final int STORAGE_GATE_MOTOR = 13;


 
  public static final int OPERATOR_CONTROLLER = 1;

  // STICKS (for xbox controler)
  public static final int HORIZ_AXIS_LEFT = 0;
  public static final int FORWARD_AXIS_LEFT = 1;
  public static final int HORIZ_AXIS_RIGHT = 4;
  public static final int FORWARD_AXIS_RIGHT = 5;

  public static final int LEFT_TRIGGER_AXIS = 2;
  public static final int RIGHT_TRIGGER_AXIS = 3;

  // CONTROLLER BUTTONS (for xbox controller)
  public static final int A = 1;
  public static final int B = 2;
  public static final int X = 3;
  public static final int Y = 4;
  public static final int LB = 5;
  public static final int RB = 6;
  public static final int BACK = 7;
  public static final int START = 8;
  public static final int LEFT_STICK_BUTTON = 9;
  public static final int RIGHT_STICK_BUTTON = 10;

  // STORAGE
  public static final double GATE_SPEED = 0.2;
  
  
}

