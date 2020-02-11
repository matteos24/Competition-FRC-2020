package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

// COMMAND to move the robot by a specified amount
public class MoveCommand extends CommandBase {
    
    // Define variables
    private final Drivetrain drivetrain;
    private final double goal;
    private final double speed;

    public MoveCommand(Drivetrain drivetrain, double goalInInches, double speed) {
       
        // Intialise variables as per constructor
        this.drivetrain = drivetrain;
        this.goal = goalInInches;
        this.speed = speed;

        // Requires a drivetrain to work
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetEncoders();
        drivetrain.tankDrive(speed, speed);
    }


    @Override
    public void execute() {
        correctHeading();
    }

    private void correctHeading() {
        // Assuming no wheel slip, the difference in encoder distances is proportional to the heading error
        double error = drivetrain.getLeftDistance() - drivetrain.getRightDistance();

        // Drives forward continuously at half speed, using the encoders to stabilize the heading
        drivetrain.tankDrive(speed + 1 * error, speed - 1 * error);
    }

    @Override
    public boolean isFinished() {
        // If the average of the encoders is greater than the goal, stop.
        return (drivetrain.getLeftDistance() + drivetrain.getRightDistance()) / 2 >= goal;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
    }
}
