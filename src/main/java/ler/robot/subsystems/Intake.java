/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake extends SubsystemBase {
  
  
  public static final double ROLLER_SPEED = 0.80;

  TalonSRX roller;
  DoubleSolenoid arm;
  
  /**
   * Creates a new Intake.
   */
  public Intake(TalonSRX roller, DoubleSolenoid arm) {
    this.roller = roller;
    this.arm = arm;
  }

  public void startIntake(double speed){
    roller.set(ControlMode.PercentOutput, speed );

  }
  public void stopIntake(){
    roller.set(ControlMode.PercentOutput, 0);

  }

  public void extendIntake(){
    arm.set(Value.kForward);
  }

  public void retractIntake(){
    arm.set(Value.kReverse);
  }

  public boolean isExtended(){
    if(arm.get() == Value.kForward){
      return true;
    }
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
