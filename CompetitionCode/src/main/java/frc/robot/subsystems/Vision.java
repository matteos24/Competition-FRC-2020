/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

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

  public Vision(Color goalColor) {
    pixy = Pixy2.createInstance(link);
    pixy.init();
    pixyCCC = pixy.getCCC();
    pixyVideo = pixy.getVideo();
  }
  
  /**
   * Gets an arraylist of all the current "blocks" the camera detects and returns true
   * if the largest block's color matches the one inputed
   * 
   Get color of goal and make an array with the blocks of that
   color; The first block in the array will always be the
   biggest in size.
   */
  public ArrayList<Block> getGoalList(Color goalColor){
    
    pixyCCC.getBlocks(true, 255, 255);
    ArrayList<Block> blocks = pixyCCC.getBlocks();

    ArrayList<Block> temp = new ArrayList<Block>();
    for(Block blockTemp: blocks){
      if(getBlockColor(blockTemp).equals(goalColor)) temp.add(blockTemp);
    } 
    return temp;
  }
//TODO: We love you papa patrick
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

  public double[] getHorizontalVerticalAngles(Color goalColor){
    Block goal = getGoalList(goalColor).get(0);
    double coordinateX = goal.getX();
    double coordinateY = goal.getY();
    //This basically calculates the turn angle needed assuming right is negative and left is positive
    double angleHorizontal = ((1 - (coordinateX/(Constants.CAMERA_X/2)))*(Constants.HORIZONTAL_TOTAL_INT/2));
    angleHorizontal =- Constants.DIFFERENCE_BETWEEN_SHOOTER_ANGLE_AND_CAM_ANGLE;
    double angleVertical = ((1 - (coordinateY/(Constants.CAMERA_Y/2)))*(Constants.VERTICAL_TOTAL_INT/2));
    double[] temp = new double[] {angleHorizontal, angleVertical};
    return temp;
  }

  public double getDistance(Color goalColor){
    double[] angles = getHorizontalVerticalAngles(goalColor);
    double iAngle = angles[1];
    return HEIGHT_OF_CAM/(Math.tan(iAngle));
  }

  public double getOptimalShootVelocityPower(boolean isFar, Color goalColor){
    double angle = isFar? 20: 50;
    double d = getDistance(goalColor) + DISTANCE_DIFFERENCE;
    double x;
    x = -9.8*d*d;
    x = x/(2*((HEIGHT_OF_SHOOTER*Math.cos(angle)*Math.cos(angle))-(d*Math.sin(angle)*Math.cos(angle))));
    x = Math.sqrt(x);
    return x/MAX_VELOCITY_OF_SHOOTER;
  }
}