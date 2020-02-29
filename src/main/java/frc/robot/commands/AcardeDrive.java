/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class AcardeDrive extends CommandBase {
  double LAxis = 0.0; 
  double RAxis = 0.0; 
  /**
   * Creates a new AcardeDrive.
   */
  public AcardeDrive() {
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
    RAxis =-RobotContainer.DRIVE_SCALING*RobotContainer.JoyL.getRawAxis(1);
    LAxis = RobotContainer.DRIVE_TURN_SCALING*RobotContainer.JoyR.getRawAxis(0); 
    if (abs(RAxis)+abs(LAxis)>1){
      RAxis = RAxis/(abs(RAxis)+abs(LAxis));
      LAxis = LAxis/(abs(RAxis)+abs(LAxis));
    }
    if(Math.abs(RAxis)<.05){
      RAxis = 0.0;
    }
    if(Math.abs(LAxis)<.05){
      LAxis = 0.0;
    }
    if (RobotContainer.Drive_subsystem.ControlsInverted == false){
      RobotContainer.Drive_subsystem.setSpeed(RAxis-LAxis, RAxis+LAxis);
      }
      else{
        RobotContainer.Drive_subsystem.setSpeed(-RAxis+LAxis, -RAxis-LAxis);
      }


  }

  private int abs(double lAxis2) {
    return 0;
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
