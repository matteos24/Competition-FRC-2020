package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.MoveCommand;
import frc.robot.commands.auto.TurnCommand;
import frc.robot.subsystems.Drivetrain;

// TestAutoCommandGroup: For testing
public class TestAutoCommandGroup extends SequentialCommandGroup {

    public TestAutoCommandGroup(Drivetrain drivetrain) {
        super(
                // new MoveCommand(drivetrain, 24, 0.5),
                 new TurnCommand(drivetrain, 90, 0.15));
                //new MoveCommand(drivetrain, 24, 0.15));
    }
}