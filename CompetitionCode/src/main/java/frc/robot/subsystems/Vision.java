/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;
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

  private double thetaTrench;

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

  public void setThetaTrench(double temp){
    thetaTrench = temp;
  }

  public double getThetaTrench(){
    return thetaTrench;
  }

  public double getTotalTrenchAngle(){
    double z = 90 - Math.abs(getThetaTrench());
    double x = Math.cos(z) * getDistanceFromObject(SHOOTER_TAPE_SIG);
    double b = Math.atan(242.63/(x+66.91));
    return (b + z);
    //The variables cannot be named due to the fact that they are hard to name.
    //Trouble understanding? Ask Emre!
  }

  public double getTheta2Trench(){
    double z = 90 - Math.abs(getThetaTrench());
    double x = Math.cos(z) * getDistanceFromObject(SHOOTER_TAPE_SIG);
    double b = Math.atan(242.63/(x+66.91));
    return 90 - b;
    //The variables cannot be named due to the fact that they are hard to name.
    //Trouble understanding? Ask Emre!
  }

  public double getTotalDistance(){
    double z = 90 - Math.abs(getThetaTrench());
    double x = Math.cos(z) * getDistanceFromObject(SHOOTER_TAPE_SIG);
    double hype = Math.sqrt(((x+66.91)*(x+66.91)) + (242.63 * 242.63));
    return hype;
    //Again the variables cannot be named due to the fact that they are hard to name.
    //Trouble understanding? Ask Emre!
  }
  public double getAnglesOfBlock(int sig, boolean isVertical){
    double coordinateX = 0;
    double coordinateY = 0;
    if (getBlocksOfType(sig).size() >=0) {
    Block block = getBlocksOfType(sig).get(0);
    
    coordinateX = block.getX();
    coordinateY = block.getY();
    

    //This basically calculates the turn angle needed assuming right is negative and left is positive
    double angleHorizontal = ((1 - (coordinateX / (Constants.CAMERA_X / 2))) * (Constants.HORIZONTAL_TOTAL_INT / 2));
    angleHorizontal =- Constants.DIFFERENCE_BETWEEN_SHOOTER_ANGLE_AND_CAM_ANGLE;
    double angleVertical = ((1 - (coordinateY / (Constants.CAMERA_Y / 2))) * (Constants.VERTICAL_TOTAL_INT / 2));
    double[] temp = new double[2];
    temp[0] = angleHorizontal;
    temp[1] = angleVertical;
    if (isVertical){setThetaTrench(temp[0]);}
    return isVertical ? temp[1] : temp[0];
    }
    return -1;

  }

  public double getDistanceFromObject(int sig){
    double angle = getAnglesOfBlock(sig, true);
    return HEIGHT_OF_CAM/(Math.tan(angle));
  }

  public double getOptimalShootVelocityPower(boolean isAgainstWall){
    double angle = isAgainstWall ? 50 : 20; // ANGLE
    double d = getDistanceFromObject(SHOOTER_TAPE_SIG) + DISTANCE_DIFFERENCE;
    double x;
    x = -9.8*d*d;
    x = x/(2*((HEIGHT_OF_SHOOTER*Math.cos(angle)*Math.cos(angle))-(d*Math.sin(angle)*Math.cos(angle))));
    x = Math.sqrt(x);
    return x/MAX_VELOCITY_OF_SHOOTER;
  }
  public boolean isWallAligned(){
    List<Block> temp = new ArrayList<Block>();
    temp = getBlocksOfType(SHOOTER_TAPE_SIG);
    return(temp.size()==0);
  }
}
