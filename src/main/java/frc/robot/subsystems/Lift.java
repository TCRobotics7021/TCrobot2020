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
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Lift extends SubsystemBase {
  
  private CANSparkMax lift_motor = new CANSparkMax(8, MotorType.kBrushless);
  private CANEncoder  lift_motor_enc = lift_motor.getEncoder();
  private DigitalInput top_limit = new DigitalInput(5);
  private DigitalInput bottom_limit = new DigitalInput(6);
  public double kP = .0002;
  public double kI = .0;
  public double kD = .0;
  public double kIz = 0;
  public double kFF = 0;
  public double maxOutput = 1;
  public double minOutput = -1;
  public double maxRPM = 5000;


  private CANPIDController lift_motor_PID = lift_motor.getPIDController();

  public double lift_setpoint;
  



  public Lift() {
    lift_motor.restoreFactoryDefaults();

    lift_motor_PID.setP(kP);
    lift_motor_PID.setI(kI);
    lift_motor_PID.setD(kD);
    lift_motor_PID.setIZone(kIz);
    lift_motor_PID.setFF(kFF);
    lift_motor_PID.setOutputRange(minOutput, maxOutput);
    
    SmartDashboard.putNumber("PGain_lift", kP);
    SmartDashboard.putNumber("IGain_lift", kI);
    SmartDashboard.putNumber("DGain_lift", kD);
    SmartDashboard.putNumber("IZone_lift", kIz);
    SmartDashboard.putNumber("Max Output_lift", maxOutput);
    SmartDashboard.putNumber("Min Output_lift", minOutput);
   
  }

  public void setPosition(double setpoint){
    
    
    this.lift_setpoint = setpoint;

    updatePIDvariables();

    lift_motor_PID.setReference(this.lift_setpoint, ControlType.kPosition);
  }

  public void setSpeed(double speed) {
    if(speed < 0 && bottom_limit.get()){
      speed = 0;
    }
    if(speed > 0 && top_limit.get()){
      speed = 0;
    }
    lift_motor_PID.setReference(speed, ControlType.kDutyCycle);

  }



  public void updatePIDvariables(){
    double p = SmartDashboard.getNumber("PGain_lift", 0);
    double i = SmartDashboard.getNumber("IGain_lift", 0);
    double d = SmartDashboard.getNumber("DGain_lift", 0);
    double iz = SmartDashboard.getNumber("IZone_lift", 0);
    double ff = SmartDashboard.getNumber("Feed Forward_lift", 0);
    double max = SmartDashboard.getNumber("Max Output_lift", 0);
    double min = SmartDashboard.getNumber("Min Output_lift", 0);


    if((p != kP)) { lift_motor_PID.setP(p); kP = p; }
    if((i != kI)) { lift_motor_PID.setI(i); kI = i; }
    if((d != kD)) { lift_motor_PID.setD(d); kD = d; }
    if((iz != kIz)) { lift_motor_PID.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { lift_motor_PID.setFF(ff); ;kFF = ff; }
    if((max != maxOutput) || (min != minOutput)) { 
      lift_motor_PID.setOutputRange(min, max); 
      minOutput = min; maxOutput = max; 
    }
    SmartDashboard.putNumber("PGain_lift", kP);
    SmartDashboard.putNumber("IGain_lift", kI);
    SmartDashboard.putNumber("DGain_lift", kD);
    SmartDashboard.putNumber("IZone_lift", kIz);
    SmartDashboard.putNumber("Feed Forward_lift", kFF);
    SmartDashboard.putNumber("Max Output_lift", maxOutput);
    SmartDashboard.putNumber("Min Output_lift", minOutput);
   
  }
  public void Reset_enc(){
    setPosition(0);

  }

    @Override
  public void periodic() {
    lift_motor_enc.setPositionConversionFactor(RobotContainer.LIFT_POS_CONV_FACTOR); 
    if(bottom_limit.get() == true){
      Reset_enc();
    }
    SmartDashboard.putNumber("Lift Encoder Position", lift_motor_enc.getPosition());
    // This method will be called once per scheduler run
  }




}