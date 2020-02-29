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

public class Default_Intake extends CommandBase {
  Timer intake_delay = new Timer(); 


  /**
   * Creates a new Default_Intake.
   */
  public Default_Intake() {
    addRequirements(RobotContainer.Intake_subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake_delay.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
    if (RobotContainer.JoyL.getRawButton(1)) {
      RobotContainer.Intake_subsystem.set_Intake_Speed(-RobotContainer.INTAKE_SPEED, RobotContainer.INNER_INTAKE_SPEED);
    intake_delay.reset();
    intake_delay.start();

    }
    else {
      RobotContainer.Intake_subsystem.stop_outer_intake(); 
    }
    if (intake_delay.get()> 1){
      RobotContainer.Intake_subsystem.set_Intake_Speed(0,0);
    }
  
  if (!RobotContainer.infeedsensor.get()&&!RobotContainer.outfeedsensor.get()){
    RobotContainer.Intake_subsystem.set_Intake_Speed(0,0);
  } else if (!RobotContainer.infeedsensor.get()&&RobotContainer.outfeedsensor.get()) {
    RobotContainer.Intake_subsystem.set_Intake_Speed(0,.6);
  }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //RobotContainer.Intake_subsystem.set_Intake_Speed(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
