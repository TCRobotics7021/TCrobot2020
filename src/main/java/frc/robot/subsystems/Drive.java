/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {

  TalonFX BRMotor = new TalonFX(1);
  TalonFX BLMotor = new TalonFX(2);
  TalonFX FRMotor = new TalonFX(3);
  TalonFX FLMotor = new TalonFX(4);
  public boolean ControlsInverted = false;
  /**
   * Creates a new Drive.
   */
  public Drive() {

  }

  public void setSpeed(double RSpeed, double LSpeed){
    BRMotor.set(ControlMode.PercentOutput,RSpeed);
    BLMotor.set(ControlMode.PercentOutput,-LSpeed);
    FLMotor.set(ControlMode.PercentOutput,-LSpeed);
    FRMotor.set(ControlMode.PercentOutput,RSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
