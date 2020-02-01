/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX extends SubsystemBase {
  /**
   * Creates a new NavX.
   */


  AHRS navX = new AHRS(SerialPort.Port.kUSB);
  
  public NavX() {

  }

  public double getAnlgeNavX() {
    navX.getAngle();
    return navX.getAngle();
  }

  public double getFusedHeadingNavX() {
    double FusedA = navX.getFusedHeading() * Math.PI / 180;

    if (FusedA < 0) {
      FusedA = FusedA + 2 * Math.PI;
    }
    return FusedA;
  }

  public double getAngleDifference(double TargetA) {

    double CurrentA = getFusedHeadingNavX();
    double AngleD = -Math.atan2(Math.sin(TargetA - CurrentA), Math.cos(TargetA - CurrentA));



    return AngleD;
  }


  public double getCompassHeadingNavX() {
    navX.getCompassHeading();
    return navX.getCompassHeading();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("NavX Heading", getCompassHeadingNavX());
  }
}
