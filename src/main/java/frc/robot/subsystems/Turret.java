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

  DigitalInput overtravel = new DigitalInput(2);



  public Turret() {
  }

  public void setSpeed(double speed) {
    
   if (overtravel.get() == true) {
     turret.set(ControlMode.PercentOutput, speed);
    } else {
     turret.set(ControlMode.PercentOutput, 0);
    }
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
