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

  //////////////////////////////////////////////////////////

  // == VISION TRACKING GOAL == //

  /**
   * Gets blocks of type [Signature]
   */
  public List<Block> getBlocksOfType() {
    List<Block> output = new ArrayList<>();

    for(Block b: pixyCCC.getBlocks()) {
      if(b.getSignature() == Constants.SHOOTER_TAPE_SIG) output.add(b);
    }

    return output;
  }

  /**
   * Given a block, this method will return the color surrounding the 5x5 space of the 
   * x and y coords of the block
   * 
   * @param block Block to use x and y coords and find color of
   * @return Returns the color in the 5x5 area of the x and y coords of the block
   */
  public Color getBlockColor(Block block) {
    int x = block.getX();
    int y = block.getY();
    RGB myRGB = pixyVideo.new RGB(0, 0, 0);
    pixyVideo.getRGB(x, y, myRGB, false);
    return myRGB.getColor();
  } 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double[] getAngles(){
    List<Block> blocksOfType = new ArrayList<Block>();
    blocksOfType = getBlocksOfType();
    Block blockGoal = blocksOfType.get(0);
    double coordinateX = blockGoal.getX();
    double coordinateY = blockGoal.getY();

    //This basically calculates the turn angle needed assuming right is negative and left is positive
    double angleHorizontal = ((1 - (coordinateX / (Constants.CAMERA_X / 2))) * (Constants.HORIZONTAL_TOTAL_INT / 2));
    angleHorizontal =- Constants.DIFFERENCE_BETWEEN_SHOOTER_ANGLE_AND_CAM_ANGLE;
    double angleVertical = ((1 - (coordinateY / (Constants.CAMERA_Y / 2))) * (Constants.VERTICAL_TOTAL_INT / 2));
    double[] temp = new double[2];
    temp[0] = angleHorizontal;
    temp[1] = angleVertical;
    return temp;
  }

  public double getDistance(){
    double[] angles = getAngles();
    double iAngle = angles[1];
    return HEIGHT_OF_CAM/(Math.tan(iAngle));
  }

  public double getOptimalShootVelocityPower(boolean isAgainstWall){
    double angle = isAgainstWall ? 50 : 20;
    double d = getDistance() + DISTANCE_DIFFERENCE;
    double x;
    x = -9.8*d*d;
    x = x/(2*((HEIGHT_OF_SHOOTER*Math.cos(angle)*Math.cos(angle))-(d*Math.sin(angle)*Math.cos(angle))));
    x = Math.sqrt(x);
    return x/MAX_VELOCITY_OF_SHOOTER;
  }


//////////////////////////////////////////////////////////

  // == VISION TRACKING POWER CELL == //

  public List<Block> getBallBlocksOfType() {
    List<Block> output = new ArrayList<>();

    for(Block b: pixyCCC.getBlocks()) {
      if(b.getSignature() == Constants.POWER_CELL_SIG) output.add(b);
    }

    return output;
  }


  public double getBallAngle(){
    List<Block> blocksOfType = new ArrayList<Block>();
    blocksOfType = getBlocksOfType();
    Block blockGoal = blocksOfType.get(0);
    double coordinateX = blockGoal.getX();

    //This basically calculates the turn angle needed assuming right is negative and left is positive
    double angleHorizontal = ((1 - (coordinateX / (Constants.CAMERA_X / 2))) * (Constants.HORIZONTAL_TOTAL_INT / 2));
    angleHorizontal =- Constants.DIFFERENCE_BETWEEN_SHOOTER_ANGLE_AND_CAM_ANGLE;
    return angleHorizontal;
  }


}

/* IN COMMAND ALIGNWITHSHOOT
private Vision vision;
public AlignWithShoot(Vision vision){
  this.vision = vision;
}
if getGoalList.length<1{
  double velocityOfShooter = 0.5(real value to be determined)
}
else{
  double velocityOfShooter = vision.getOptimalShootVelocityPower(true);
  TurnCommand(getHorizontalVerticalAngles[0]);
}

*/
