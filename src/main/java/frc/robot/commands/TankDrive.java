/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class TankDrive extends CommandBase {
  double LAxis = 0.0; 
  double RAxis = 0.0; 
  /**
   * Creates a new TankDrive.
   */
  public TankDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.Drive_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RAxis =.5*RobotContainer.JoyL.getRawAxis(1);
    LAxis = .5*RobotContainer.JoyR.getRawAxis(1);
    if(Math.abs(RAxis)<.05){
      RAxis = 0.0;
    }
    if(Math.abs(LAxis)<.05){
      LAxis = 0.0;
    }
    if (RobotContainer.Drive_subsystem.ControlsInverted == false){
    RobotContainer.Drive_subsystem.setSpeed(RAxis, LAxis);
    }
    else{
      RobotContainer.Drive_subsystem.setSpeed(-RAxis, -LAxis);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.Drive_subsystem.setSpeed(0,0); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
