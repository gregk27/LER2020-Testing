/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ler.robot.RobotMap;

public class Shooter extends SubsystemBase{
  
  public static final double kP = 0.000002;
  public static final double kI = 0.0;
  public static final double kD = 0.000001;
  public static final double kF = 0.000086;

  private static final double SPIN_CONSTANT = 0.85;

  public static final double[] SPEEDS = {0, 0.9};
  public static final int ZEROSPEED = 0;
  public static final double limelightSpeedScaling = 0.01;


  //Cycles per revolution of encoders on shooter
  public static final int cPR = 64;
  /**
   * Creates a new ShooterSubsystem.
   */
  public Shooter() {

  }

  public void setShooterSpeed(int speedArrayPosition){
    setSpecificShooterSpeed(SPEEDS[speedArrayPosition]);
    //currentSpeed = SPEEDS[speedArrayPosition];
  }

  public void setSpecificShooterSpeed(double speed){
    //talonShooterTop.set(ControlMode.PercentOutput, speeds[currentSpeed]);
    //System.out.println(RobotMap.shooterTopTalon.contr);
    RobotMap.shooterTopSpark.getPIDController().setReference((speed*SPIN_CONSTANT), ControlType.kVelocity);
    //talonShooterBottom.set(ControlMode.PercentOutput, -speeds[currentSpeed]);
    RobotMap.shooterBottomSpark.getPIDController().setReference(-speed, ControlType.kVelocity);
  }

  public void talonResetPos(){
    RobotMap.shooterTopSpark.getEncoder().getPosition();
    RobotMap.shooterTopSpark.getEncoder().getPosition();
  }

  public double getTopTalonSpeed(){
    return(RobotMap.shooterTopSpark.getEncoder().getVelocity());
  }

  public double getBottomTalonSpeed(){
    return(RobotMap.shooterTopSpark.getEncoder().getPosition());
  }

  public double getAverageTalonSpeed(){
    return((getTopTalonSpeed() + getBottomTalonSpeed()) / 2);
  }

  //speed should be how far the bot can shoot straight up
  //should be used with getSpeed() from limelight
  public double getVelocityFromLimelight(double speed){
    return(speed * limelightSpeedScaling);
  }
}
