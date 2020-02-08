package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

public class AlignTurn extends CommandBase {

    private final Drivetrain drivetrain;
    private final double degrees;
    private final double speed;
    private final double distanceToTurn;

    public AlignTurn(Drivetrain drivetrain, double speed, Vision v) {
       
        degrees = (v.getHorizontalVerticalAngles(Constants.GOAL_COLOR)[0]);
        this.drivetrain = drivetrain;
        this.speed = speed;
        
        // Calculate the distance to turn based off angle (see isFinished).
        drivetrain.startingAngle(degrees);
        this.distanceToTurn = Drivetrain.WHEEL_TO_WHEEL_DIAMETER_INCHES * 2 * Math.PI * (degrees / 360);

        // Requires a drivetrain to work
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetEncoders();

        // See isFinished() for explanation of motor direction.
        if (degrees > 0) { // positive left, negative right
            drivetrain.tankDrive(speed, -speed);
        } else { // negative left, positive right
            drivetrain.tankDrive(-speed, speed);
        }
    }

    @Override
    public void execute() {

    }


    @Override
    public boolean isFinished() {
        // In order to find the distance to move, we need the robot circumference multiplied by the fraction of the circle.
        // The fraction is degrees / 360.

        // If turning left (-degrees), left motors are -, and right motors are +.
        // If turning right (degrees), left motors are +, and right motors are -.
        boolean isFinished;

        // If distance is greater than distance to turn (w/ respect to direction), end.
        if (degrees > 0) { // positive left, negative right
            isFinished = drivetrain.getLeftDistance() > distanceToTurn && drivetrain.getRightDistance() < -distanceToTurn;
        } else { // negative left, positive right
            isFinished = drivetrain.getLeftDistance() < -distanceToTurn && drivetrain.getRightDistance() > distanceToTurn;
        }

        return isFinished;
    }

    @Override
    public void end(boolean interrupted) {
        // Stop motors.
        drivetrain.tankDrive(0, 0); 
    }
}
