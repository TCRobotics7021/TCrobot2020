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
import edu.wpi.first.wpilibj.Timer;

public class Percent_Shoot extends CommandBase {
  /**
   * Creates a new Percent_Shoot.
   */
  boolean shootingStarted;

   double TX;
   
   double turretSpeed;

   Timer percentShootDelay = new Timer();

  public Percent_Shoot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.Accumulator_subsystem);
    addRequirements(RobotContainer.Limelight_subsystem);
    addRequirements(RobotContainer.shooter_subsystem);
    addRequirements(RobotContainer.Turret_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.shooter_subsystem.percentshoot(SmartDashboard.getNumber("Top_Percent", 0), SmartDashboard.getNumber("Bot_Percent", 0));
    percentShootDelay.start();
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
      if (turretSpeed > .75) {
        turretSpeed = .75;
      } 
      if (turretSpeed < -.75) {
        turretSpeed = -.75;    }
      }else{
      turretSpeed = 0;
    }
      RobotContainer.Turret_subsystem.setSpeed(turretSpeed);
  } 
      if(percentShootDelay.get()>3) {
        RobotContainer.Accumulator_subsystem.setSpeed(RobotContainer.ACC_SPEED);
        shootingStarted = true;
      }
  
    
    
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter_subsystem.setVelocity(0,0);
    RobotContainer.Accumulator_subsystem.setSpeed(0);
    RobotContainer.Turret_subsystem.setSpeed(0);
    RobotContainer.shooter_subsystem.freeWheel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
