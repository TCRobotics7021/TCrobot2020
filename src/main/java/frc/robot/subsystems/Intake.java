/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  public Spark front_intake = new Spark(4);
  public Spark top_intake = new Spark(1);
  public Spark bottom_intake = new Spark(2);
  

  /**
   * Creates a new Intake.
   */
  public Intake() {

  }

  public void set_Intake_Speed(double speed) {
    front_intake.set(speed);
    top_intake.set(-speed*1.5); 
    bottom_intake.set(-speed*1.5);
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }
}
