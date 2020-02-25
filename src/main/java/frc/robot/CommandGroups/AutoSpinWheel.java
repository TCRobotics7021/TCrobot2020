/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.ColorWheel_Drive;
import frc.robot.commands.Lift_Goto_Height;
import frc.robot.commands.SpinningWheel;
import frc.robot.commands.Timed_Drive;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoSpinWheel extends SequentialCommandGroup {
  /**
   * Creates a new AutoSpinWheel.
   */
  public AutoSpinWheel() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    addCommands(
      new Lift_Goto_Height(RobotContainer.COLORWHEEL_ABOVE_POS),
      new ColorWheel_Drive(.1),
      new Lift_Goto_Height(RobotContainer.COLORWHEEL_ON_POS),
      new SpinningWheel(24),
      new Lift_Goto_Height(RobotContainer.COLORWHEEL_ABOVE_POS),
      new Timed_Drive(1.5, -.1)
    );
  }
}
