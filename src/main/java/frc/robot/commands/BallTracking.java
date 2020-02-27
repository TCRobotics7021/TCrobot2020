/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class BallTracking extends CommandBase {
  /**
   * Creates a new BallTracking.
   */

    double p = .037;

    double tx;
    double ty;

    double turningSpeed;


  public BallTracking() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.Drivecamera_subsystem);
    addRequirements(RobotContainer.Drive_subsystem);
    addRequirements(RobotContainer.Intake_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tx = RobotContainer.Drivecamera_subsystem.getTx();
    ty = RobotContainer.Drivecamera_subsystem.getTy();

    if (Math.abs(tx) > 5) {
      turningSpeed = tx * p;
      RobotContainer.Drive_subsystem.setSpeed(turningSpeed, -turningSpeed);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}