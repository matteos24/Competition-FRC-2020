
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

import static frc.robot.Constants.*;

public class RobotContainer {

    // SUBSYSTEMS
    private final Drivetrain DRIVETRAIN = new Drivetrain();

    public final Joystick driverController = new Joystick(DRIVER_CONTROLLER);

    public final JoystickButton modeSwitchButton = new JoystickButton(driverController, MODE_SWITCH_BUTTON);

    public RobotContainer() {
        configureButtonActions();
    }

     public final StartEndCommand modeSwitch = new StartEndCommand(
         () -> DRIVETRAIN.modeSlow(),
         () -> DRIVETRAIN.modeFast(),
         DRIVETRAIN
     ); 

    /**
     * Config button actions: it changes what does each button do. Don't touch this to change bindings
     */
    private void configureButtonActions() {
        modeSwitchButton.whenHeld(modeSwitch);
    }

    public Drivetrain getDrivetrain() {
        return this.DRIVETRAIN;
    }
}