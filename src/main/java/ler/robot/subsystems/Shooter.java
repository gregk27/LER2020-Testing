/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ler.robot.RobotMap;

public class Shooter extends SubsystemBase{
  
  public static final double kP = 0.000065;
  public static final double kI = 0.0;
  public static final double kD = 0.000002;
  public static final double kF = 0.000090;

  private static final double SPIN_CONSTANT = 0.8;

  public static final double[] SPEEDS = {0, 0.8};
  public static final int ZEROSPEED = 0;
  public static final double limelightSpeedScaling = 0.01;
  public static final double SHOOTER_TARGET_SPEED = 7900;
  public static final double SHOOTER_TOP_TARGET_SPEED = SHOOTER_TARGET_SPEED;
  public static final double SHOOTER_BOTTOM_TARGET_SPEED = -SHOOTER_TARGET_SPEED*SPIN_CONSTANT;

  public static final int LEFT_SHOOTER = 0;
  public static final int RIGHT_SHOOTER = 1;

  public long spoolTime = System.currentTimeMillis();

  //Cycles per revolution of encoders on shooter
  public static final int cPR = 64;
  /**
   * Creates a new ShooterSubsystem.
   */
  public Shooter() {

  }

  public void setShootersSpeed(int speedArrayPosition){
    setSpecificShootersSpeed(SPEEDS[speedArrayPosition]);
  }

  public void setSpecificShootersSpeed(double speed){
    spoolTime = System.currentTimeMillis()+100;
    setSpecificShooterSpeed(speed, LEFT_SHOOTER);
    setSpecificShooterSpeed(-speed * SPIN_CONSTANT, RIGHT_SHOOTER);
  }

  public void setSpecificShooterSpeed(double speed, int shooter){
    CANSparkMax topShooter;
    CANSparkMax bottomShooter;
    if(shooter == LEFT_SHOOTER){
      topShooter = RobotMap.shooterTopLeftSpark;
      bottomShooter = RobotMap.shooterBottomLeftSpark;
    }else{
      topShooter = RobotMap.shooterTopRightSpark;
      bottomShooter = RobotMap.shooterTopRightSpark;
    }

    if(speed == 0){
      //slow down shooter gently instead of breaking
      topShooter.set(0);
      bottomShooter.set(0);
    }else{
      topShooter.getPIDController().setReference((speed), ControlType.kVelocity);
      bottomShooter.getPIDController().setReference((-speed * SPIN_CONSTANT), ControlType.kVelocity);
    }
    
  }

  public double getTopLeftSparkSpeed(){
    return(RobotMap.shooterTopLeftSpark.getEncoder().getVelocity());
  }
  
  public double getTopRightSparkSpeed(){
    return(RobotMap.shooterTopRightSpark.getEncoder().getVelocity());
  }

  public double getBottomLeftSparkSpeed(){
    return(RobotMap.shooterBottomLeftSpark.getEncoder().getVelocity());
  }
  
  public double getBottomRightSparkSpeed(){
    return(RobotMap.shooterBottomRightSpark.getEncoder().getVelocity());
  }

  public double getAverageSparksSpeed(){
    return (getTopLeftSparkSpeed() + getTopRightSparkSpeed() + getBottomLeftSparkSpeed() + getBottomRightSparkSpeed()) / 4;
  }

  //speed should be how far the bot can shoot straight up
  //should be used with getSpeed() from limelight
  public double getVelocityFromLimelight(double speed){
    return(speed * limelightSpeedScaling);
  }

}
