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

    // === CONTROLLERS === //
    public static final int OPERATOR_CONTROLLER_ID = 2;

    // === BUTTONS === //
    public static final int BUTTON_A = 1, BUTTON_B = 1, BUTTON_Y = 1, BUTTON_X = 1; //TODO- Add proper buttons
    public static final int LEFT_TRIGGER = 1, RIGHT_TRIGGER = 1, LEFT_BUMPER = 1, RIGHT_BUMPER = 1;

    // === SHOOTER === //

    public static final int DEFAULT_TARGET_RPM = 5000;

    // RESERVED SHOOTER MOTOR PORTS: [0, 0]
    public static final int SHOOTER_MOTOR_1 = 0;
    public static final int SHOOTER_MOTOR_2 = 1;

    // RESERVED SHOOTER SOLENOID PORTS: [0, 0 + 1]
    public static final int SHOOTER_PISTON_A = 0;
    public static final int SHOOTER_PISTON_B = SHOOTER_PISTON_A + 1;

}
