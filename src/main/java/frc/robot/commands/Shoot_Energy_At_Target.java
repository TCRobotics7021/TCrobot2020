/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Shoot_Energy_At_Target extends CommandBase {
  /**
   * Creates a new Shoot_Energy_At_Target.
   */

   double setpoint;

   double distance;

   boolean atSpeed;


  public Shoot_Energy_At_Target() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.Accumulator_subsystem);
    addRequirements(RobotContainer.Limelight_subsystem);
    addRequirements(RobotContainer.shooter_subsystem);
    addRequirements(RobotContainer.Turret_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    atSpeed = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(RobotContainer.shooter_subsystem.atRPMs()) {
      atSpeed = true;
    }


    if(atSpeed = true) {
      RobotContainer.Accumulator_subsystem.setSpeed(RobotContainer.ACC_SPEED);
    }

    distance = RobotContainer.Limelight_subsystem.getDistance();

    setpoint = RobotContainer.shooter_subsystem.getPortRPM(distance);

    RobotContainer.shooter_subsystem.setVelocity(setpoint);

    


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter_subsystem.setVelocity(0);
    RobotContainer.Accumulator_subsystem.setSpeed(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
