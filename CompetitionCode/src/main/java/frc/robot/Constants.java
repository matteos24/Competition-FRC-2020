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


  // CLIMBER MOTORS
  public static final int LIFTER_MOTOR = 9;
  public static final int GEAR_MOTOR1 = 7;
  public static final int GEAR_MOTOR2 = 8;

  public static final int OPERATOR_CONTROLLER = 0;

  public static final int LB = 5;
  public static final int RB = 6;
  public static final int BACK = 7;

  // === SPEED CONSTANTS === //
  public static final double LIFTER_SPEED = 1;
  public static final double LIFTER_SPEED_REVERSE = -1;
  public static final double GEAR_SPEED = 0.75;
  
}

