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

  double smartratio;




  /**
   * Creates a new Shoot_Energy.
   */
  public Shoot_Energy() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.Accumulator_subsystem);
    addRequirements(RobotContainer.shooter_subsystem);
    addRequirements(RobotContainer.Limelight_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   // smartratio = SmartDashboard.getNumber("Ratio",0);
    //SmartDashboard.putNumber("Ratio",smartratio);
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.shooter_subsystem.setVelocity(5000, smartratio);
   if(RobotContainer.shooter_subsystem.atRPMs() == true){
      RobotContainer.Accumulator_subsystem.setSpeed(RobotContainer.ACC_EMPTY_SPEED);
   }else{
    //RobotContainer.Accumulator_subsystem.setSpeed(0);
   }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.Accumulator_subsystem.setSpeed(0);
    RobotContainer.shooter_subsystem.setVelocity(0,0);
    RobotContainer.shooter_subsystem.freeWheel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
