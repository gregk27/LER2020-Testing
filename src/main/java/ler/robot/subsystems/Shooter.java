/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import ler.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Shooter extends SubsystemBase{
  
  public static final double kP = 0.25;
  public static final double kI = 0.001;
  public static final double kD = 20;
  public static final double kF = 1;

  public static final int[] speeds = {0, 300, 600, 900, 1200};
  public static final int ZEROSPEED = 0;

  //Cycles per revolution of encoders on shooter
  public static final int cPR = 64;

  private int currentSpeed = 0;
  /**
   * Creates a new ShooterSubsystem.
   */
  public Shooter() {

  }

  public void setShooterSpeed(int speed){
    setSpecificShooterSpeed(speeds[speed]);
  }

  public void setSpecificShooterSpeed(double speed){
    //talonShooterTop.set(ControlMode.PercentOutput, speeds[currentSpeed]);
    RobotMap.shooterTopTalon.set(ControlMode.Velocity, speed);
    //talonShooterBottom.set(ControlMode.PercentOutput, -speeds[currentSpeed]);
    RobotMap.shooterBottomTalon.set(ControlMode.Velocity, -speed);
  }

  public void talonResetPos(){
    RobotMap.shooterTopTalon.setSelectedSensorPosition(0);
    RobotMap.shooterBottomTalon.setSelectedSensorPosition(0);
  }

  public double getRevolutionsPerSecond(int rpsinput){
    return ((rpsinput/cPR)*10)*0.314;
  }
}
