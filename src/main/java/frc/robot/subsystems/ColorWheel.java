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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheel extends SubsystemBase {
  Spark colorWheel_motor = new Spark(6);
  I2C.Port I2Cport = I2C.Port.kOnboard;
  ColorSensorV3 ColorSensor = new ColorSensorV3(I2Cport);
  ColorMatch colorMatcher = new ColorMatch();
  Color BlueTarget = ColorMatch.makeColor(0.1301, 0.4192, 0.4512);
  Color GreenTarget = ColorMatch.makeColor(0.1763, 0.5701, 0.2542);
  Color RedTarget = ColorMatch.makeColor(0.5242, 0.3411, 0.1350);
  Color YellowTarget = ColorMatch.makeColor(0.3293, 0.5500, 0.1208);
  Color DetectedColor;
  Color detectedColor2;
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
  public String getCurrentColor() {
    DetectedColor = ColorSensor.getColor();
    MatchColor = colorMatcher.matchClosestColor(DetectedColor);
     if(MatchColor.color == BlueTarget){
       return "Blue";
     } else if(MatchColor.color == RedTarget){
       return "Red";
     }else if(MatchColor.color == GreenTarget){
       return "Green";
     }else if(MatchColor.color == YellowTarget){
       return "Yellow";
     }else{
       return "No Color";
     }
  }
  public void setWheelSpeed(double speed) {
    colorWheel_motor.setSpeed(speed);
  }
  public int getCurrentDistance() {
    return ColorSensor.getIR();
  }
  @Override
  public void periodic() {
    //SmartDashboard.putNumber("Color Sensor Height", ColorSensor.getProximity());
    //SmartDashboard.putString("Current Color", getCurrentColor());
    // This method will be called once per scheduler run
    //detectedColor2 = ColorSensor.getColor();
    //SmartDashboard.putNumber("Red", detectedColor2.red); 
    //SmartDashboard.putNumber("Green", detectedColor2.green); 
    //SmartDashboard.putNumber("Blue", detectedColor2.blue); 
    //SmartDashboard.putNumber("Color Sensor Distance", ColorSensor.getIR());
 

  }
}
