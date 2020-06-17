/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import ler.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Subsystem representing the intake arm and rollers.
 */
public class Intake extends SubsystemBase {
  
  
  public static final double ROLLER_SPEED = 0.80;

  /**
   * Creates a new Intake.
   */
  public Intake() {

  }

  /**
   * Drive the intake roller.
   *
   * @param speed The % to drive at
   */
  public void startIntake(double speed){
    RobotMap.intakeRoller.set(ControlMode.PercentOutput, speed);

  }

  /**
   * Stop the intake roller.
   */
  public void stopIntake(){
    RobotMap.intakeRoller.set(ControlMode.PercentOutput, 0);

  }

  /**
   * Deploy intake.
   */
  public void extendIntake(){
    RobotMap.intakeArm.set(Value.kForward);
  }

  /**
   * Retract intake.
   */
  public void retractIntake(){
    RobotMap.intakeArm.set(Value.kReverse);
  }

  /**
   * Get if the intake is deployed.
   * 
   * @return <code>true</code> if the intake is deployed
   */
  public boolean isExtended(){
    if(RobotMap.intakeArm.get() == Value.kForward){
      return true;
    }
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
