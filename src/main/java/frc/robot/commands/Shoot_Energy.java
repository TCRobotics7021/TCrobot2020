/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Shoot_Energy extends CommandBase {

  double ratio;

  boolean shootingStarted;

  double TX;
  
  double turretSpeed;

  double RPMs;
  /**
   * Creates a new Shoot_Energy.
   */
  public Shoot_Energy() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.Accumulator_subsystem);
    addRequirements(RobotContainer.shooter_subsystem);
    addRequirements(RobotContainer.Limelight_subsystem);
    addRequirements(RobotContainer.Intake_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   // smartratio = SmartDashboard.getNumber("Ratio",0);
    //SmartDashboard.putNumber("Ratio",smartratio);
   shootingStarted = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.OPpanel.getRawButton(4)== false){
      if(shootingStarted == false){
      TX = RobotContainer.Limelight_subsystem.getTx();
      turretSpeed = TX * 1/10;
      if(turretSpeed < 0){
        turretSpeed -= 0;
      }else{
        turretSpeed += 0;
      }
    }else{
      turretSpeed = 0;
    }
      RobotContainer.Turret_subsystem.setSpeed(turretSpeed);
  }
      ratio = SmartDashboard.getNumber("Set Ratio", 0);

      RPMs = SmartDashboard.getNumber("Set RPMs", 0);
  
      RobotContainer.shooter_subsystem.setVelocity(RPMs, ratio);

      if(RobotContainer.shooter_subsystem.atRPMs()&& RPMs > 0 && ratio != 0 && ( Math.abs(TX) < 2 || RobotContainer.OPpanel.getRawButton(4)) ) {
        RobotContainer.Accumulator_subsystem.setSpeed(RobotContainer.ACC_EMPTY_SPEED);
        RobotContainer.Intake_subsystem.set_Intake_Speed(0, .6);
        shootingStarted = true;
      }
  

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.Accumulator_subsystem.setSpeed(0);
    RobotContainer.shooter_subsystem.setVelocity(0,0);
    RobotContainer.shooter_subsystem.freeWheel();
    RobotContainer.Intake_subsystem.set_Intake_Speed(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
