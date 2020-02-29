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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  
  private CANSparkMax bot_shooter = new CANSparkMax(5, MotorType.kBrushless);
  private CANSparkMax top_shooter = new CANSparkMax(6, MotorType.kBrushless);
  private CANEncoder  bot_shooter_enc = bot_shooter.getEncoder();
  private CANEncoder top_shooter_enc = top_shooter.getEncoder();
  public double kP = .0001;
  public double kI = .0000005;
  public double kD = 0;
  public double kIz = 0;
  public double kFF = 0;
  public double maxOutput = 1;
  public double minOutput = -1;
  public double maxRPM = 5000;
  public double bot_top_ratio = .5;
  public double ratio_offset = 0;




  public double PortRatio = 0;

  private CANPIDController bot_shooter_PID = bot_shooter.getPIDController();
  private CANPIDController top_shooter_PID = top_shooter.getPIDController();

  public double bot_setpoint;
  public double top_setpoint;



  public Shooter() {
    bot_shooter.restoreFactoryDefaults();

    bot_shooter_PID.setP(kP);
    bot_shooter_PID.setI(kI);
    bot_shooter_PID.setD(kD);
    bot_shooter_PID.setIZone(kIz);
    bot_shooter_PID.setFF(kFF);
    bot_shooter_PID.setOutputRange(minOutput, maxOutput);

    top_shooter_PID.setP(kP);
    top_shooter_PID.setI(kI);
    top_shooter_PID.setD(kD);
    top_shooter_PID.setIZone(kIz);
    top_shooter_PID.setFF(kFF);
    top_shooter_PID.setOutputRange(minOutput, maxOutput);
    
    SmartDashboard.putNumber("PGain", kP);
    SmartDashboard.putNumber("IGain", kI);
    SmartDashboard.putNumber("DGain", kD);
    SmartDashboard.putNumber("IZone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", maxOutput);
    SmartDashboard.putNumber("Min Output", minOutput);
    SmartDashboard.putNumber("Ratio", bot_top_ratio);
    SmartDashboard.putNumber("TestingRatio", 0.5);
    SmartDashboard.putNumber("Set Ratio", 0.5);
    SmartDashboard.putNumber("Set RPMs", 4000);

    SmartDashboard.putNumber("Top_Percent", 0);
    SmartDashboard.putNumber("Bot_Percent", 0);
  }

  public void setVelocity(double setpoint, double ratio){
    
    
    //bot_top_ratio = SmartDashboard.getNumber("Ratio",1);
    this.bot_setpoint = setpoint;
    this.top_setpoint = setpoint * ratio;

    updatePIDvariables();
    UpdateRPMs();
    bot_shooter_PID.setReference(this.bot_setpoint, ControlType.kVelocity);
    top_shooter_PID.setReference(this.top_setpoint, ControlType.kVelocity);
    
    
  }

  public void freeWheel() {
    bot_shooter_PID.setReference(0, ControlType.kVoltage);
    top_shooter_PID.setReference(0, ControlType.kVoltage);
  }

  public void percentshoot(double top_speed, double bot_speed) {

    top_shooter.set(top_speed);
    bot_shooter.set(bot_speed);
  }



  public void updatePIDvariables(){
    double p = SmartDashboard.getNumber("PGain", 0);
    double i = SmartDashboard.getNumber("IGain", 0);
    double d = SmartDashboard.getNumber("DGain", 0);
    double iz = SmartDashboard.getNumber("IZone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);


    if((p != kP)) { bot_shooter_PID.setP(p); top_shooter_PID.setP(p); kP = p; }
    if((i != kI)) { bot_shooter_PID.setI(i); top_shooter_PID.setI(i); kI = i; }
    if((d != kD)) { bot_shooter_PID.setD(d); top_shooter_PID.setD(d); kD = d; }
    if((iz != kIz)) { bot_shooter_PID.setIZone(iz); top_shooter_PID.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { bot_shooter_PID.setFF(ff); top_shooter_PID.setFF(ff);kFF = ff; }
    if((max != maxOutput) || (min != minOutput)) { 
      bot_shooter_PID.setOutputRange(min, max); 
      top_shooter_PID.setOutputRange(min, max); 
      minOutput = min; maxOutput = max; 
    }
    SmartDashboard.putNumber("PGain", kP);
    SmartDashboard.putNumber("IGain", kI);
    SmartDashboard.putNumber("DGain", kD);
    SmartDashboard.putNumber("IZone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", maxOutput);
    SmartDashboard.putNumber("Min Output", minOutput);
    SmartDashboard.putNumber("Ratio", bot_top_ratio);
   
  }
  public void adjustRatioOffset(double adjust){
    ratio_offset += adjust;
  }


  public void motorIdle(){
    
  }


  public void UpdateRPMs() {
    
    SmartDashboard.putNumber("Bot Actual RPM", bot_shooter_enc.getVelocity());
    SmartDashboard.putNumber("Top Actual RPM", top_shooter_enc.getVelocity());
  }

  public boolean atRPMs() {
    if (Math.abs(this.bot_setpoint - bot_shooter_enc.getVelocity()) < RobotContainer.SHOOTER_START_RANGE && Math.abs(this.top_setpoint - top_shooter_enc.getVelocity()) < RobotContainer.SHOOTER_START_RANGE)  {
      return true;
    }
    else{
      return false;
    }

  }

  public double getPortRatio(double Distance) {
    PortRatio = RobotContainer.RATIO_CALC_A*Math.pow(Distance,2)+ RobotContainer.RATIO_CALC_B*Distance + RobotContainer.RATIO_CALC_C + ratio_offset;
    return PortRatio;

  }


  @Override
  public void periodic() {
    SmartDashboard.putNumber("Ratio Offset", ratio_offset);

    SmartDashboard.putNumber("Bot Actual RPM", -bot_shooter_enc.getVelocity());
    SmartDashboard.putNumber("Top Actual RPM", top_shooter_enc.getVelocity());
    // This method will be called once per scheduler run
    

  }
}
