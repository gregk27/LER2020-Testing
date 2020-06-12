/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyor extends SubsystemBase {
  
  public static final double INTAKE_SPEED = 0.30;
  public static final double NORMAL_SPEED = 0.80;
  public static final double SHOOTER_SPEED = 0.25;

  //second item is the angle of the longbomb shot
  //TODO: store the longbomb angle in a better place
  public static final double[] ANGLES = {0, 60};

  //TODO: change these values
  //distance from where rod attaches to pase to pivot
  public static final double BASE_DISTANCE = 1;
  //distance from pivot to where rod attaches to conveyor, measured along conveyor
  public static final double CONVEYOR_DISTANCE = 1;

  public static final double CONVERT_DISTANCE_TO_MOTOR = 1;


  TalonSRX driveMotor;
  CANSparkMax angleMotor;
  DoubleSolenoid valve;
  /**
   * Creates a new Conveyor.
   */
  public Conveyor(TalonSRX driveMotor, CANSparkMax angleMotor, DoubleSolenoid valve) {
    this.driveMotor = driveMotor;
    this.angleMotor = angleMotor;
    this.valve = valve;
  }

  public void setConveyorSpeed (double speed){
    driveMotor.set(ControlMode.PercentOutput, -speed);

  }

  public void stopConveyor(){
    setConveyorSpeed(0);
  }

  public void setConveyorAngle (int angle){
    setSpecificConveyorAngle(ANGLES[angle]);
  }

  public void setSpecificConveyorAngle (double angle){
    //https://www.desmos.com/calculator/0c4jqiqryo for the math stuff
    //in this case, 0 is vertical, meanwhile 90 is horizontal

    //point where rod touches conveyor
    double[] p1 = {CONVEYOR_DISTANCE * Math.sin(angle), CONVEYOR_DISTANCE * Math.cos(angle)};
    //point where rod touches base
    double[] p2 = {-BASE_DISTANCE, 0};

    double distance = Math.hypot(p1[0] - p2[0], p1[1] - p2[1]);

    angle = distance * CONVERT_DISTANCE_TO_MOTOR;

    angleMotor.getPIDController().setReference(angle, ControlType.kPosition);
  }

  public void setConveyorAngleVoltage (double voltage){
    //TODO: this might need to be scaled or something
    angleMotor.getPIDController().setReference(voltage, ControlType.kVoltage);
  }

  public void setValveState (boolean state){
    if(state){
      //open
      valve.set(Value.kForward);
    }else{
      valve.set(Value.kReverse);
    }
  }

  public boolean isExtended(){
    if(valve.get() == Value.kForward){
      return true;
    }
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
