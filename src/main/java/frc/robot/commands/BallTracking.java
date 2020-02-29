/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.hal.sim.mockdata.RoboRioDataJNI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class BallTracking extends CommandBase {
  /**
   * Creates a new BallTracking.
   */

    double tx;
    double ty;

    double turningSpeed;
    double driveSpeed;
    boolean ballClose;
    Timer drivedelay = new Timer();
    boolean finished;


  public BallTracking() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.Drivecamera_subsystem);
    addRequirements(RobotContainer.Drive_subsystem);
    addRequirements(RobotContainer.Intake_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.Drivecamera_subsystem.setPipeline(1);
    ballClose = false;
    finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tx = RobotContainer.Drivecamera_subsystem.getTx();
    ty = RobotContainer.Drivecamera_subsystem.getTy();
    turningSpeed = tx * RobotContainer.BALL_TRACKING_PVALUE;

    if (Math.abs(tx) > RobotContainer.BALL_TRACKING_TX) {
      driveSpeed = 0;
    } else{
      driveSpeed = RobotContainer.BALL_TRACKING_DRIVESPEED;
    }

    if (ty < RobotContainer.BALL_TRACKING_TY){
      ballClose = true;
      drivedelay.start();
      RobotContainer.Intake_subsystem.set_Intake_Speed(RobotContainer.INTAKE_SPEED, RobotContainer.INNER_INTAKE_SPEED);
    }

    if ( ballClose == false){
      RobotContainer.Drive_subsystem.setSpeed(driveSpeed + turningSpeed, driveSpeed - turningSpeed);
    } else if(drivedelay.get() < RobotContainer.BALL_TRACKING_DRIVEDELAY){
      RobotContainer.Drive_subsystem.setSpeed(RobotContainer.BALL_TRACKING_DRIVESPEED, RobotContainer.BALL_TRACKING_DRIVESPEED);
    } else{
      RobotContainer.Drive_subsystem.setSpeed(0, 0);
    }

    if (RobotContainer.infeedsensor.get() == false && ballClose == true){
      finished = true;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.Drive_subsystem.setSpeed(0, 0);
    RobotContainer.Drivecamera_subsystem.setPipeline(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
