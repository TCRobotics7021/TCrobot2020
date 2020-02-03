/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  /**
   * Creates a new Drive.
   */
  public Drive() {
    TalonFX front_right = new TalonFX(1);
    TalonFX front_left = new TalonFX(2);
    TalonFX mid_right = new TalonFX(3);
    TalonFX mid_left = new TalonFX(4);
    TalonFX back_right = new TalonFX(5);
    TalonFX back_left = new TalonFX(6);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
