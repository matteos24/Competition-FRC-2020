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
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2Video;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.Pixy2Video.RGB;
import io.github.pseudoresonance.pixy2api.links.SPILink;

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
   * Gets an arraylist of all the current "blocks" the camera detects and returns the 
   * largest block that is the same color as requested in the parameters 
   * 
   * @param color Color to check if the block's color matches it
   * @return Returns the largest block of the same color
   */
  public Block getLargestSameColorBlock(Color color) {
    // Get data from all blocks and recieve said data
    pixyCCC.getBlocks(true, 255, 255);
    ArrayList<Block> blocks = pixyCCC.getBlocks();

    // Goes through every block to get largest same color block
    Block myBlock = null;
    for (Block block: blocks) {
      if (color.equals(getBlockColor(block))) {
        myBlock = block;
        break;
      }
    }

    return(myBlock);
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
}
