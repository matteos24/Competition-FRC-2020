package frc.robot.triggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.RobotCommands;

public class StorageLimitSwitchTrigger extends Trigger {

    @Override
    public boolean get(){
        return  RobotCommands.StorageShouldIntake();
    }
}