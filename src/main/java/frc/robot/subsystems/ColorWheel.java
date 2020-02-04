/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheel extends SubsystemBase {
  Spark colorWheel_motor = new Spark(2);
  I2C.Port I2Cport = I2C.Port.kOnboard;
  ColorSensorV3 ColorSensor = new ColorSensorV3(I2Cport);
  ColorMatch colorMatcher = new ColorMatch();
  Color BlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  Color GreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  Color RedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  Color YellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  Color DetectedColor;
  ColorMatchResult MatchColor;
  /**
   * Creates a new ColorWheel.
   */
  public ColorWheel() {

    colorMatcher.addColorMatch(BlueTarget);
    colorMatcher.addColorMatch(GreenTarget);
    colorMatcher.addColorMatch(RedTarget);
    colorMatcher.addColorMatch(YellowTarget);
  }
  public Color getCurrentColor() {
    DetectedColor = ColorSensor.getColor();
    MatchColor = colorMatcher.matchClosestColor(DetectedColor);
    return MatchColor.color;
  }
  public void setWheelSpeed(double speed) {
    colorWheel_motor.setSpeed(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
