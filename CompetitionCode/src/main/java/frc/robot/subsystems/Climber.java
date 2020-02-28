/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

/**
 * COMPETITION READY
 * 
 * Two motors in a gearbox (winch) to lift us, and one motor to initally raise the arm.
 */
public class Climber extends SubsystemBase {

    // Initialize fields
    private VictorSP lifterMotor;
    private VictorSP gearBoxMotor1, gearBoxMotor2;

    public Climber() {
        lifterMotor = new VictorSP(LIFTER_MOTOR);
        gearBoxMotor1 = new VictorSP(GEAR_MOTOR1);
        gearBoxMotor2 = new VictorSP(GEAR_MOTOR2);
    }

    /**
     * Sets the speed of the lifter to the desired speed
     */
    public void lifterUp() {
        lifterMotor.set(LIFTER_SPEED);
    }

    /**
     * Sets the speed of the lifter to the set speed
     */
    public void lifterDown() {
        lifterMotor.set(LIFTER_SPEED_REVERSE);
    }

    /**
     * Turns off the lifter motor
     */
    public void lifterZero() {
        lifterMotor.set(0);
    }

    /**
     * Sets the speed of the winch motors to the desired speed
     */
    public void gearOn() {
        gearBoxMotor1.set(GEAR_SPEED);
        gearBoxMotor2.set(GEAR_SPEED);
    }

    /**
     * Turn winch off
     */
    public void gearOff() {
        gearBoxMotor1.set(0);
        gearBoxMotor2.set(0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
