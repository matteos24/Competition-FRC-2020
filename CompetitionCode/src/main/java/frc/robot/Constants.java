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

  // INTAKE MOTORS
  public static final int WHEEL_INTAKE_MOTOR = 0;
  
  // INTAKE PISTONS
  public static final int INTAKE_PISTON_1 = 2;
  public static final int INTAKE_PISTON_2 = 3;

  // CONTROLLERS
  public static final int OPERATOR_CONTROLLER = 1;

  // TODO: MAKE TOGGLEABLE???
  public static final int INTAKE_PISTON_BUTTON= 1;
  public static final int INTAKE_MOTOR_BUTTON = 2;
  public static final int OUTTAKE_MOTOR_BUTTON = 3;
  
  // === SPEED CONSTANTS === //
  
  // INTAKE
  public static final double DEPLOY_INTAKE_SPEED = 0.5;
  public static final double WHEEL_INTAKE_SPEED = 0.5;


}

