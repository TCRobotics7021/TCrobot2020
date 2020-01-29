/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Accumulator_Index extends CommandBase {
  Timer acc_delay = new Timer(); 
  /**
   * Creates a new Accumulator_Index.
   */
  public Accumulator_Index() {
    addRequirements(RobotContainer.Accumulator_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.Accumulator_subsystem.infeedblocked() == true){
      acc_delay.reset();
      acc_delay.start();
    }
    if (RobotContainer.Accumulator_subsystem.infeedblocked() == true && RobotContainer.Accumulator_subsystem.outfeedblocked() == false) {
      RobotContainer.Accumulator_subsystem.setSpeed(RobotContainer.ACC_SPEED);
    } else if(RobotContainer.Accumulator_subsystem.outfeedblocked()== true || acc_delay.get()> RobotContainer.ACC_DELAY) {
      RobotContainer.Accumulator_subsystem.setSpeed(0);
    }


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) { 
  
    RobotContainer.Accumulator_subsystem.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}