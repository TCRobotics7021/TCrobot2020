/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Timed_Drive extends CommandBase {

  double Timed;
  
  double Speed;

  boolean Finished;

  Timer Drive_Time = new Timer();
  /**
   * Creates a new Timed_Drive.
   */
  public Timed_Drive(double Timed, double Speed) {
    addRequirements(RobotContainer.Drive_subsystem);
    this.Timed = Timed;
    this.Speed = Speed;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Finished = false;
    RobotContainer.Drive_subsystem.setSpeed(Speed, Speed);
    Drive_Time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Drive_Time.get() > Timed) {
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
