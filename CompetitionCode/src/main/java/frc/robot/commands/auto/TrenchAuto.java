package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

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

    public TrenchAuto(/* change */) {
        super(
            /* code */
        );
    }
}