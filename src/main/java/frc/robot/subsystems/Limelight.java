/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Limelight extends SubsystemBase {
  /**
   * Creates a new Limelight.
   */

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  double yposition = 0;
  double xposition = 0;
  double distance = 0;


  public double getTx() {
    return table.getEntry("tx").getDouble(0);
  }

  public double getTy() {
    return table.getEntry("ty").getDouble(0);
  }

  public double getTa() {
    return table.getEntry("ta").getDouble(0);
  }

  public double getTs() {
    return table.getEntry("ts").getDouble(0);
  }

  

 
  public void setPipeline(int pipeline) {
    NetworkTableEntry pipelineTableEntry = table.getEntry("pipeline");
    pipelineTableEntry.setNumber(pipeline);
    NetworkTableEntry streamTableEntry = table.getEntry("stream");
    streamTableEntry.setNumber(2);

  }
  
  public void setLEDmode(int LEDmode) {
    NetworkTableEntry LEDmodeEntry = table.getEntry("LEDmode");
    LEDmodeEntry.setNumber(LEDmode);
  }





  public double getDistance() {
    setPipeline(1);
    yposition = getTy();
    distance = Math.pow(yposition,2)*RobotContainer.DIST_CALC_A+RobotContainer.DIST_CALC_B*yposition+RobotContainer.DIST_CALC_C;

    return distance;
  }

  public boolean isCentered() {
    if (Math.abs(getTx()) <= RobotContainer.LR_AIM_TOL) {
      return true;

    }else{

      return false;
    }
  }


  public Limelight() {

  }




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
