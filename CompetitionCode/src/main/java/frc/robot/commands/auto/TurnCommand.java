package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TurnCommand extends CommandBase {

    public static final double IN_TO_UNITS = 3200 / Math.PI;

    private final Drivetrain drivetrain;
    private final double degrees, speed, startDistanceL, startDistanceR;
    private double distanceToTurnL, distanceToTurnR;

    public TurnCommand(Drivetrain drivetrain, double degrees, double speed) {
       
        this.drivetrain = drivetrain;

        drivetrain.resetEncoders();

        this.degrees = degrees;
        this.speed = speed;
        
        // Calculate the distance to turn based off angle (see isFinished).
        this.startDistanceL = drivetrain.getLeftDistance();
        this.startDistanceR = drivetrain.getRightDistance();

        this.distanceToTurnL = Drivetrain.WHEEL_TO_WHEEL_DIAMETER_INCHES * Math.PI * (degrees / 180);
        this.distanceToTurnL *= IN_TO_UNITS;
        this.distanceToTurnL += startDistanceL;

        this.distanceToTurnR = -Drivetrain.WHEEL_TO_WHEEL_DIAMETER_INCHES * Math.PI * (degrees / 180); // IN INCHES
        this.distanceToTurnR *= IN_TO_UNITS;
        this.distanceToTurnR += startDistanceR;

        // Requires a drivetrain to work
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        // See isFinished() for explanation of motor direction.
        if (degrees > 0) { // positive left, negative right
            drivetrain.tankDrive(speed, -speed);
        } else { // negative left, positive right
            drivetrain.tankDrive(-speed, speed);
        }
    }

    @Override
    public void execute() {
        System.out.println("GOAL L: " + (distanceToTurnL - startDistanceL) / IN_TO_UNITS + " GOAL R: " + (distanceToTurnR - startDistanceR) / IN_TO_UNITS);
        System.out.println("L: " + (drivetrain.getLeftDistance() - startDistanceL) / IN_TO_UNITS + " R: " + ((drivetrain.getRightDistance() - startDistanceR) / IN_TO_UNITS));
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
            isFinished = drivetrain.getRightDistance() < distanceToTurnR
                && drivetrain.getLeftDistance() > distanceToTurnL;
        } else { // negative left, positive right
            isFinished = drivetrain.getRightDistance() > distanceToTurnR
                && drivetrain.getLeftDistance() < distanceToTurnL;
        }

        return isFinished;
    }

    @Override
    public void end(boolean interrupted) {
        // Stop motors.
        drivetrain.tankDrive(0, 0); 
    }
}