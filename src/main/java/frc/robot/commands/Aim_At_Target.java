/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Aim_At_Target extends CommandBase {
  double TX;
  double speed;
  /**
   * Creates a new Aim_At_Target.
   */
  public Aim_At_Target() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.Turret_subsystem);
    addRequirements(RobotContainer.Limelight_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    TX = RobotContainer.Limelight_subsystem.getTx();
    speed = TX * 1/15;
    if(speed < 0){
      speed -= .1;
    }else{
      speed += .1;
    }
    RobotContainer.Turret_subsystem.setSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.Turret_subsystem.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
