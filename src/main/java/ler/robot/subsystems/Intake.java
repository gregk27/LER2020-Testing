/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import ler.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends SubsystemBase {
  
  
  public static final double ROLLER_SPEED = 0.5;

  /**
   * Creates a new Intake.
   */
  public Intake() {

  }

  public void StartIntake (double speed){
    RobotMap.intakeRoller.set(ControlMode.PercentOutput, speed );

  }
  public void StopIntake (){
    RobotMap.intakeRoller.set(ControlMode.PercentOutput, 0);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
