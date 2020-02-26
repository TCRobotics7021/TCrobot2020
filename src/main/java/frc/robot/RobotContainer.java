/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.CommandGroups.AutoShootandMove;
import frc.robot.CommandGroups.AutoSpinWheel;
import frc.robot.commands.AcardeDrive;
import frc.robot.commands.Accumulator_Index;
import frc.robot.commands.Aim_At_Target;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.CancelCommand;
import frc.robot.commands.ColorWheel_Drive;
import frc.robot.commands.Default_Intake;
import frc.robot.commands.DriveInvertedToggle;
import frc.robot.commands.ExampleCommand;

import frc.robot.commands.Lift_Goto_Height;
import frc.robot.commands.Manual_Turret;
import frc.robot.commands.Percent_Lift;
import frc.robot.commands.Percent_Shoot;
import frc.robot.commands.Ratio_Adjust;
import frc.robot.commands.Shoot_Energy;
import frc.robot.commands.Shoot_Energy_At_Target;
import frc.robot.commands.SpinningWheel;
import frc.robot.commands.Timed_Drive;
import frc.robot.commands.Toggle_Auto_Aim;
import frc.robot.subsystems.Accumulator;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
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
  //public final static NavX NavX_subsystem = new NavX();
  public final static ColorWheel ColorWheel_subsystem = new ColorWheel();
  public final static Intake Intake_subsystem = new Intake(); 
  public final static Lift Lift_Subsystem = new Lift();
  
  SendableChooser AutonomousChooser = new SendableChooser<Command>();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  public static Joystick JoyL = new Joystick(1);
  public static Joystick JoyR = new Joystick(2);
  public static Joystick OPpanel = new Joystick(0);
  public static DigitalInput outfeedsensor = new DigitalInput(3); 
  public static DigitalInput infeedsensor = new DigitalInput(1); 
  //Constants
  public final static double ACC_SPEED = .6;
  public final static double ACC_EMPTY_SPEED = 1;
  public final static double ACC_DELAY = .0; //In Seconds
  
  public final static double LR_AIM_TOL = 2;
   
  public final static double DRIVE_SCALING = .5;
  public final static double DRIVE_TURN_SCALING = .5;

  public final static double RATIO_CALC_A = -.00000003;
  public final static double RATIO_CALC_B = .0004;
  public final static double RATIO_CALC_C = -.6633;

  public final static double DIST_CALC_A = 17.635;
  public final static double DIST_CALC_B = -5.4232;
  public final static double DIST_CALC_C = 3075.9;

  public final static double INTAKE_SPEED = .4; //The intake's speed 
  public final static double INNER_INTAKE_SPEED = .6;

  public final static double PRESET_SHOOTING_DIST = 3000; //In mm 

  public final static double LIFT_POS_CONV_FACTOR = 2.9723191748; 
  public final static double RESET_ENC_POS = 1084;
  public final static double BAR_POS = 1650;
  public final static double COLORWHEEL_ABOVE_POS = 1115;
  public final static double COLORWHEEL_ON_POS = 1100;
  public final static double RETRACT_POS = 1170;

  public final static double LIFT_PVALUE = .2;

  public final static double MANUAL_TURRET_SPEED = .5;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() { 
    AutonomousChooser.setDefaultOption("Move Off Line", new Timed_Drive(1, .2));
    AutonomousChooser.addOption("Shoot Balls", new AutoShoot(5));
    AutonomousChooser.addOption("Auto Shoot and Move", new AutoShootandMove());

    SmartDashboard.putData("Auto Commands", AutonomousChooser);

    // Configure the button bindings
    configureButtonBindings();

    Accumulator_subsystem.setDefaultCommand(new Accumulator_Index());

    Drive_subsystem.setDefaultCommand(new AcardeDrive());
    Intake_subsystem.setDefaultCommand(new Default_Intake());
    //Turret_subsystem.setDefaultCommand(new Aim_At_Target());



  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(JoyL, 2).whileHeld(new Shoot_Energy());
    new JoystickButton(JoyL, 1).whileHeld(new Shoot_Energy_At_Target());
    new JoystickButton(OPpanel, 8).whileHeld(new Manual_Turret(MANUAL_TURRET_SPEED));
    new JoystickButton(OPpanel, 12).whileHeld(new Manual_Turret(-MANUAL_TURRET_SPEED));
    
    new JoystickButton(OPpanel, 15).whileHeld(new Percent_Lift(.25));
    new JoystickButton(OPpanel, 16).whileHeld(new Percent_Lift(-.25));
    //new JoystickButton(JoyR, 7).whenPressed(new DriveInvertedToggle());

    new JoystickButton(OPpanel, 13).whenPressed(new Lift_Goto_Height(BAR_POS));
    new JoystickButton(OPpanel, 14).whenPressed(new Lift_Goto_Height(RETRACT_POS));

    new JoystickButton(OPpanel, 6).whenPressed(new AutoSpinWheel());
    new JoystickButton(JoyR, 10).whenPressed(new Timed_Drive(2, .1));
    new JoystickButton(JoyR, 11).whenPressed(new ColorWheel_Drive(.1));
    new JoystickButton(JoyR, 12).whenPressed(new SpinningWheel(5));
    new JoystickButton(JoyR, 13).whenPressed(new Lift_Goto_Height(COLORWHEEL_ABOVE_POS));
    new JoystickButton(JoyR, 14).whenPressed(new Lift_Goto_Height(COLORWHEEL_ON_POS));

    new JoystickButton(OPpanel, 3).whileHeld(new CancelCommand());

    new JoystickButton(OPpanel, 2).whileHeld(new Percent_Shoot());
    new JoystickButton(OPpanel, 5).whenPressed(new Lift_Goto_Height(SmartDashboard.getNumber("Test Height", 0)));
  }

  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous 
    return (Command) AutonomousChooser.getSelected();
  }
}
