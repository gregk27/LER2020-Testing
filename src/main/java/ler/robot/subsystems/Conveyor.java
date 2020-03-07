/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import ler.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyor extends SubsystemBase {
  
  public static final double INTAKE_SPEED = 0.25;
  public static final double SHOOTER_SPEED = 0.25;

  public static final double[] ANGLES = {0, 1};

  /**
   * Creates a new Conveyor.
   */
  public Conveyor() {

  }

  public void setConveyorSpeed (double speed){
    //System.out.println("Driving Conveyor");
    RobotMap.conveyorMotor.set(ControlMode.PercentOutput, -speed);

  }

  public void setConveyorAngle (int angle){
    setSpecificConveyorAngle(ANGLES[angle]);
  }

  public void setSpecificConveyorAngle (double angle){
    RobotMap.angleElevation.set(ControlMode.Position, angle);
  }

  public void setValveState (boolean state){
    if(state){
      //open
      RobotMap.conveyorValve.set(Value.kForward);
    }else{
      RobotMap.conveyorValve.set(Value.kReverse);
    }
  }

  public boolean isExtended(){
    if(RobotMap.conveyorValve.get() == Value.kForward){
      return true;
    }
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
