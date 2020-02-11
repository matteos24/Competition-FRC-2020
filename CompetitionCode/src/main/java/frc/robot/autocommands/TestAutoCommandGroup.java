package frc.robot.autocommands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autocommands.MoveCommand;
import frc.robot.autocommands.TurnCommand;
import frc.robot.subsystems.Drivetrain;

// TestAutoCommandGroup: For testing
public class TestAutoCommandGroup extends SequentialCommandGroup {
  
    public TestAutoCommandGroup(Drivetrain drivetrain) {
        super(
            new MoveCommand(drivetrain, 24, 0.5),
            new TurnCommand(drivetrain, 180, 0.5),
            new MoveCommand(drivetrain, 24, 0.5));
    }
}