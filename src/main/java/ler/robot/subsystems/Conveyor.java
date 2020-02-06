/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import ler.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyor extends SubsystemBase {
  
  public static final double ConveyorIntakeSpeed = 0.25;
  public static final double ConveyorShooterSpeed = 0.25;

  /**
   * Creates a new Conveyor.
   */
  public Conveyor() {

  }

  public void SetConveyorSpeed (double speed){
    RobotMap.talonConveyor.set(ControlMode.PercentOutput, speed);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
