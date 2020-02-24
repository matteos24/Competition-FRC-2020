/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2Video;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.Pixy2Video.RGB;
import io.github.pseudoresonance.pixy2api.links.SPILink;

import static frc.robot.Constants.*;

public class Vision extends SubsystemBase {

  // FIELDS
  Pixy2 pixy;
  Pixy2CCC pixyCCC;
  Pixy2Video pixyVideo;
  SPILink link = new SPILink();

  public Vision() {
    pixy = Pixy2.createInstance(link);
    pixy.init();
    pixyCCC = pixy.getCCC();
    pixyVideo = pixy.getVideo();
  }

  /**
   * Gets blocks of type [Signature]
   */
  public List<Block> getBlocksOfType(int signature) {
    List<Block> output = new ArrayList<>();

    for(Block b: pixyCCC.getBlocks()) {
      if(b.getSignature() == signature) output.add(b);
    }

    return output;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getAnglesOfBlock(Block block, boolean isVertical){
    double coordinateX = block.getX();
    double coordinateY = block.getY();

    //This basically calculates the turn angle needed assuming right is negative and left is positive
    double angleHorizontal = ((1 - (coordinateX / (Constants.CAMERA_X / 2))) * (Constants.HORIZONTAL_TOTAL_INT / 2));
    angleHorizontal =- Constants.DIFFERENCE_BETWEEN_SHOOTER_ANGLE_AND_CAM_ANGLE;
    double angleVertical = ((1 - (coordinateY / (Constants.CAMERA_Y / 2))) * (Constants.VERTICAL_TOTAL_INT / 2));
    double[] temp = new double[2];
    temp[0] = angleHorizontal;
    temp[1] = angleVertical;
    return isVertical ? temp[1] : temp[0];
  }

  public double getDistanceFromObject(Block block){
    double angle = getAnglesOfBlock(block, true);
    return HEIGHT_OF_CAM/(Math.tan(angle));
  }

  public double getOptimalShootVelocityPower(Block block, boolean isAgainstWall){
    double angle = isAgainstWall ? 50 : 20; // ANGLE
    double d = getDistanceFromObject(block) + DISTANCE_DIFFERENCE;
    double x;
    x = -9.8*d*d;
    x = x/(2*((HEIGHT_OF_SHOOTER*Math.cos(angle)*Math.cos(angle))-(d*Math.sin(angle)*Math.cos(angle))));
    x = Math.sqrt(x);
    return x/MAX_VELOCITY_OF_SHOOTER;
  }
}
