package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.Constants;
import frc.robot.Constants.*;
import frc.robot.commands.EnableShooterCommand;

/**
 * READ ME IMPORTANT
 * This auto will shoot 3 preloaded power cells by auto aligning to the
 * target zone and calculating the optimal velocity to shoot at.
 * Then go towards the trench and pick up 3 power cells using ball tracking code
 * and auto aligning, then go back to the target zone and shoot the power cells
 * by auto aligning and calculating the optimal velocity.
 * Where we start on the starting line shouldn't matter if we use vision
 */
public class TrenchAuto extends SequentialCommandGroup {
    public TrenchAuto(Drivetrain drivetrain, Vision vision) {
        super(
            new TurnCommand(drivetrain, vision.getAnglesOfBlock(Constants.SHOOTER_TAPE_SIG, false), 0.5), 
            //Shoot command will be here (Soon)
            new TurnCommand(drivetrain, vision.getTotalTrenchAngle(), 0.5),
            //Turn on Intake 
            new MoveCommand(drivetrain, vision.getTotalDistance(), 0.7), 
            new TurnCommand(drivetrain, vision.getTheta2Trench(), 0.5),
            new MoveCommand(drivetrain, 72.5, 0.7), 
            //I think 72.5 should work but just test an be sure
            //Turn of Intake
            new TurnCommand(drivetrain, 180, 0.5),
            new MoveCommand(drivetrain, 72.5, 0.7),
            new TurnCommand(drivetrain, vision.getAnglesOfBlock(Constants.SHOOTER_TAPE_SIG, false), 0.5)
            //Shoot command

        );
    }
}