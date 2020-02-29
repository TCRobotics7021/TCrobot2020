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

public class AutoShoot extends CommandBase {
  Timer shootTimer = new Timer();

  double TimeOn;

  double ratio;

  double distance;

  boolean shootingStarted;

  double TX;
  
  double turretSpeed;
  /**
   * Creates a new AutoShoot.
   */
  public AutoShoot(double TimeOn) {
    this.TimeOn = TimeOn;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.Accumulator_subsystem);
    addRequirements(RobotContainer.Limelight_subsystem);
    addRequirements(RobotContainer.shooter_subsystem);
    addRequirements(RobotContainer.Turret_subsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shootingStarted = false;
    shootTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(shootingStarted == false){
      TX = RobotContainer.Limelight_subsystem.getTx();
      turretSpeed = TX * 1/10;
      if(turretSpeed < 0){
        turretSpeed -= 0;
      }else{
        turretSpeed += 0;
      }
      distance = RobotContainer.Limelight_subsystem.getDistance();
    }else{
      turretSpeed = 0;
    }
      RobotContainer.Turret_subsystem.setSpeed(turretSpeed);
  
      ratio = RobotContainer.shooter_subsystem.getPortRatio(distance);
  
      RobotContainer.shooter_subsystem.setVelocity(4000, ratio);

      if(RobotContainer.shooter_subsystem.atRPMs()&&( Math.abs(TX) < 2)) {
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
    if (shootTimer.get() > TimeOn) {
      return true;
    } else {
      return false;
    }
  }
}
