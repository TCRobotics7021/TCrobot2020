/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ColorWheel_Drive extends CommandBase {

  double Speed;

  boolean Finished;
  /**
   * Creates a new ColorWheel_Drive.
   */
  public ColorWheel_Drive(double Speed) {
    addRequirements(RobotContainer.ColorWheel_subsystem);
    addRequirements(RobotContainer.Drive_subsystem);
    this.Speed = Speed;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.Drive_subsystem.setSpeed(Speed, Speed);
    Finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.ColorWheel_subsystem.getCurrentDistance() > 5) {
      Finished = true;
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
    return Finished;
  }
}
