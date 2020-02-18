/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  /**
   * Creates a new Turret.
   */


  TalonSRX turret = new TalonSRX(7);

  DigitalInput OTLeft = new DigitalInput(4);
  DigitalInput OTRight = new DigitalInput(2);
  
  public boolean autoAim = false;


  public Turret() {
  }

  public void setSpeed(double speed) {
    speed = -speed;
    if (OTLeft.get() == false && speed < 0 || OTRight.get() == false && speed > 0) {
      turret.set(ControlMode.PercentOutput, 0);
    } else {
      turret.set(ControlMode.PercentOutput, speed);
    }
    
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (OTLeft.get() == false && turret.getMotorOutputPercent() < 0 || OTRight.get() == false && turret.getMotorOutputPercent() > 0) {
      turret.set(ControlMode.PercentOutput, 0); 
    }
  }
}
