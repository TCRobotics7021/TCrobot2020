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

public class Lift_Goto_Height extends CommandBase {
  double Setpoint;

  boolean finished = false;

  double DistError;
  /**
   * Creates a new Lift_Goto_Height.
   */
  public Lift_Goto_Height(double Setpoint) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.Setpoint = Setpoint;
    addRequirements(RobotContainer.Lift_Subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finished = false;
    RobotContainer.Lift_Subsystem.latchLift(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DistError = Math.abs(RobotContainer.Lift_Subsystem.lift_motor_enc.getPosition() - Setpoint);
    SmartDashboard.putNumber("Distance Error",DistError);

    if (RobotContainer.Lift_Subsystem.lift_motor_enc.getPosition() < Setpoint) {
      RobotContainer.Lift_Subsystem.setSpeed(DistError*RobotContainer.LIFT_PVALUE);
    }

    if (RobotContainer.Lift_Subsystem.lift_motor_enc.getPosition() > Setpoint) {
      RobotContainer.Lift_Subsystem.setSpeed(-DistError*RobotContainer.LIFT_PVALUE);
    }

    if (DistError < 5) {
      finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.Lift_Subsystem.setSpeed(0);
    RobotContainer.Lift_Subsystem.latchLift(true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
