/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.Set;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class SpinningWheel extends CommandBase {
  /**
   * Creates a new SpinningWheel.
   */

  //create your variables here
  Timer ColorDelay = new Timer();
  int Counter;
  int Target;
  String PreviousColor;
  String CurrentColor;
  String NextColor;
  boolean Finished;

  public SpinningWheel(int Target){
    addRequirements(RobotContainer.ColorWheel_subsystem);
    this.Target = Target;
    // Use addRequirements() here to declare subsystem dependencies.
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   Counter = 0;
   PreviousColor = RobotContainer.ColorWheel_subsystem.getCurrentColor();
   RobotContainer.ColorWheel_subsystem.setWheelSpeed(0.7);
   ColorDelay.start();
   Finished = false;
  }  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(ColorDelay.get()>0.2){
      CurrentColor = RobotContainer.ColorWheel_subsystem.getCurrentColor();
    if (CurrentColor.equals(NextColor)) {
      Counter += 1;
      SmartDashboard.putNumber("Color Counter", Counter);
      SmartDashboard.putString("Current Color", CurrentColor);
      PreviousColor = RobotContainer.ColorWheel_subsystem.getCurrentColor();   
      }
    if(CurrentColor.equals("Green")) {
      NextColor = "Blue";
    }
    if(CurrentColor.equals("Blue")) {
      NextColor = "Yellow";
    }
    if(CurrentColor.equals("Yellow")) {
      NextColor = "Red";
    }
    if(CurrentColor.equals("Red")) {
      NextColor = "Green";
    }
      ColorDelay.reset();
      ColorDelay.start();
    
    }
    if (Counter >= Target) {
      Finished = true;
    }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.ColorWheel_subsystem.setWheelSpeed(0);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Finished;
  }
}