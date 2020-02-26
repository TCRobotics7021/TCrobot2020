/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Lift extends SubsystemBase {
  
  private CANSparkMax lift_motor = new CANSparkMax(8, MotorType.kBrushless);
  public CANEncoder  lift_motor_enc = lift_motor.getEncoder();
  private DigitalInput top_limit = new DigitalInput(5);
  private DigitalInput bottom_limit = new DigitalInput(0);
  private Solenoid latch = new Solenoid(11,0);
  



  public Lift() {
    lift_motor.restoreFactoryDefaults();
    SmartDashboard.putNumber("Test Height", 0);
   
  }

  

  public void setSpeed(double speed) {
    if(speed < 0 && !bottom_limit.get()){
      speed = 0;
    }
    if(speed > 0 && !top_limit.get()){
      speed = 0;
    }
    lift_motor.set(speed);

  }

  public void latchLift(boolean latched){
    if(latched == true){
      latch.set(false);
    }else{
      latch.set(true);
    }
}

 
  public void Set_enc(double Enc_set){
    lift_motor_enc.setPosition(Enc_set);

  }

    @Override
  public void periodic() {
    lift_motor_enc.setPositionConversionFactor(RobotContainer.LIFT_POS_CONV_FACTOR); 
    if(bottom_limit.get() == false){
      Set_enc(RobotContainer.RESET_ENC_POS);
    }
    SmartDashboard.putNumber("Lift Encoder Position", lift_motor_enc.getPosition());
    // This method will be called once per scheduler run
  }




}