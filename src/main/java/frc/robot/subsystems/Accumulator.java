/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Accumulator extends SubsystemBase {
  
  public CANSparkMax motor = new CANSparkMax(9, MotorType.kBrushless);

  public DigitalInput infeedsensor = new DigitalInput(1);
  public DigitalInput outfeedsensor = new DigitalInput(3);
 //The infeedersensor sends the balls through the accumilator 
 //The outfeedsensor stops them from getting into the turret
  /**
   * Creates a new Accumulator.
   */
  public Accumulator() {

  }

  public void setSpeed(double speed) {
    motor.set(-speed);
  }

  public boolean infeedblocked() {
    return !infeedsensor.get();
  }
  public boolean outfeedblocked() {
    return !outfeedsensor.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
