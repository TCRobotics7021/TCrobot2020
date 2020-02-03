/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AcardeDrive;
import frc.robot.commands.Accumulator_Index;
import frc.robot.commands.Aim_At_Target;
import frc.robot.commands.DriveInvertedToggle;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Manual_Turret;
import frc.robot.commands.Shoot_Energy_At_Target;
import frc.robot.commands.Toggle_Auto_Aim;
import frc.robot.subsystems.Accumulator;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public final static Shooter shooter_subsystem = new Shooter();
  public final static Accumulator Accumulator_subsystem = new Accumulator();
  public final static Turret Turret_subsystem = new Turret();
  public final static Limelight Limelight_subsystem = new Limelight();
  public final static Drive Drive_subsystem = new Drive();
  public final static NavX NavX_subsystem = new NavX();
  

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final Joystick gamepad = new Joystick(0);
  public static Joystick JoyL = new Joystick(1);
  public static Joystick JoyR = new Joystick(2);
  
  //Constants
  public final static double ACC_SPEED = 1;
  public final static double ACC_EMPTY_SPEED = .8;
  public final static double LR_AIM_TOL = 2;
  public final static double ACC_DELAY = .5; 
  public final static double DRIVE_SCALING = .5;
  public final static double DRIVE_TURN_SCALING = .5;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    Accumulator_subsystem.setDefaultCommand(new Accumulator_Index());

    Drive_subsystem.setDefaultCommand(new AcardeDrive());

    Turret_subsystem.setDefaultCommand(new Aim_At_Target());



  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(JoyR, 1).whileHeld(new Shoot_Energy_At_Target());
    new JoystickButton(JoyR, 2).whileHeld(new Manual_Turret(.5));
    new JoystickButton(JoyL, 2).whileHeld(new Manual_Turret(-.5));
    new JoystickButton(JoyL, 3).whileHeld(new Aim_At_Target());
    new JoystickButton(JoyR, 7).whenPressed(new DriveInvertedToggle());
    new JoystickButton(JoyR, 14).whenPressed(new Toggle_Auto_Aim());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
