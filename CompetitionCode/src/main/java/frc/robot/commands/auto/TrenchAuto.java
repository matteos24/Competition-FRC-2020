package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * READ ME IMPORTANT
 * This auto will shoot 3 preloaded power cells by auto aligning to the
 * target zone and calculating the optimal velocity to shoot at.
 * Then go towards the trench and pick up 3 power cells using ball tracking code
 * and auto aligning, then go back to the target zone and shoot the power cells
 * by auto aligning and calculating the optimal velocity.
 * 
 * THINK about where we start on the starting line, as what your code
 * may depend on it, but try to make your code so that it can start
 * anywhere on the starting line.
 */
public class TrenchAuto extends SequentialCommandGroup {

    public TrenchAuto(/* change */) {
        /** 
         * MEASUREMENTS
         * Middle ball (around 2 other ones) is 114.17in away from starting line (straight ahead).
         * Middle ball is 65.84in away from the target zone (straight up).
         * Other measurements can be found here: https://firstfrc.blob.core.windows.net/frc2020/PlayingField/LayoutandMarkingDiagram.pdf
         */
        super(
            /* code */
        );
    }
}