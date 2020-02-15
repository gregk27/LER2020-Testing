/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ler.robot.RobotMap;

public class Shooter extends SubsystemBase{
  
  public static final double kP = 0.25;
  public static final double kI = 0.001;
  public static final double kD = 20;
  public static final double kF = 1;

  public static final double[] SPEEDS = {0, 0.9};
  public static final int ZEROSPEED = 0;
  public static final double limelightSpeedScaling = 0.01;

  public final double BOTTOMSHOOTERTARGETVELOCITY = 12.2;
  public final double TOPSHOOTERTARGETVELOCITY = 12.2;

  public double bottomShooterCurrentVelocity = getRollerVelocity(RobotMap.shooterBottomTalon.getSelectedSensorVelocity());
  public double topShooterCurrentVelocity = getRollerVelocity(RobotMap.shooterTopTalon.getSelectedSensorVelocity());


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
    RobotMap.shooterTopTalon.set(ControlMode.PercentOutput, -speed);
    //talonShooterBottom.set(ControlMode.PercentOutput, -speeds[currentSpeed]);
    RobotMap.shooterBottomTalon.set(ControlMode.PercentOutput, -speed);
  }

  public void talonResetPos(){
    RobotMap.shooterTopTalon.setSelectedSensorPosition(0);
    RobotMap.shooterBottomTalon.setSelectedSensorPosition(0);
  }

  public double getTopTalonSpeed(){
    return(RobotMap.shooterTopTalon.getSelectedSensorVelocity());
  }

  public double getBottomTalonSpeed(){
    return(RobotMap.shooterBottomTalon.getSelectedSensorVelocity());
  }

  public double getAverageTalonSpeed(){
    return((getTopTalonSpeed() + getBottomTalonSpeed()) / 2);
  }

  public double getRollerVelocity(int rpsinput){
    return ((rpsinput/cPR)*10)*0.314;
  }

  //speed should be how far the bot can shoot straight up
  //should be used with getSpeed() from limelight
  public double getVelocityFromLimelight(double speed){
    return(speed * limelightSpeedScaling);
  }
}
