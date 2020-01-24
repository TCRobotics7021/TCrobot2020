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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
  /**
   * Creates a new Vision.
   * 
   */
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry ty = table.getEntry("ty");
  double yposition = 0.0;
  double distance = 0.0;

  public Vision() {

  }

  @Override
  public void periodic() {
        yposition = ty.getDouble(0.0);
        distance = Math.pow(yposition,2)*40.518-1523.6*yposition+17523;


        SmartDashboard.putNumber("Y Position",yposition);
        SmartDashboard.putNumber("Distance Calculated",distance);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(4);
  }
}
