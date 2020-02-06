/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import ler.robot.subsystems.Shooter;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class RobotMap {
  /**
   * Subclass to hold all mapping constants (motors, DIO ports, etc)
   */
  public static final class Mappings {
    public static final int LEFT_MOTOR_1 = 1;
    public static final int LEFT_MOTOR_2 = 2;
    public static final int LEFT_MOTOR_3 = 3;
    public static final int RIGHT_MOTOR_1 = 4;
    public static final int RIGHT_MOTOR_2 = 5;
    public static final int RIGHT_MOTOR_3 = 6;
    
    public static final int talonShooterTop = 8;
    public static final int talonShooterBottom = 9;
    
    //TODO: Calibrate the fake values
    public static final int talonConveyor = 2708;
    //TODO: Calibrate the fake values
   public static final int talonIntake = 2708;
  }

  // The motors on the left side of the drive.
  public static final CANSparkMax leftMotor1 = new CANSparkMax(Mappings.LEFT_MOTOR_1, MotorType.kBrushless);
  public static final CANSparkMax leftMotor2 = new CANSparkMax(Mappings.LEFT_MOTOR_2, MotorType.kBrushless);
  public static final CANSparkMax leftMotor3 = new CANSparkMax(Mappings.LEFT_MOTOR_3, MotorType.kBrushless);
  

  // The motors on the right side of the drive.
  public static final CANSparkMax rightMotor1 = new CANSparkMax(Mappings.RIGHT_MOTOR_1, MotorType.kBrushless);
  public static final CANSparkMax rightMotor2 = new CANSparkMax(Mappings.RIGHT_MOTOR_2, MotorType.kBrushless);
  public static final CANSparkMax rightMotor3 = new CANSparkMax(Mappings.RIGHT_MOTOR_3, MotorType.kBrushless);

  // The robot's drive
  public static final DifferentialDrive m_drive = new DifferentialDrive(leftMotor1, rightMotor1);

  // The talons on the shooter
  public static final TalonSRX talonShooterTop = new TalonSRX(Mappings.talonShooterTop);
  public static final TalonSRX talonShooterBottom = new TalonSRX(Mappings.talonShooterBottom);

  //The conveyor 
  public static final TalonSRX talonConveyor = new TalonSRX(Mappings.talonConveyor);
  //The intake
  public static final TalonSRX talonIntake = new TalonSRX(Mappings.talonIntake);
  public static void init(){
    leftMotor2.follow(leftMotor1);
    leftMotor3.follow(leftMotor1);

    leftMotor1.setInverted(true);
    rightMotor1.setInverted(false);

    rightMotor2.follow(rightMotor1);
    rightMotor3.follow(rightMotor1);

    //shooter init
    talonShooterTop.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

    talonShooterTop.config_kF(0, Shooter.kF);
    talonShooterTop.config_kP(0, Shooter.kP);
    talonShooterTop.config_kI(0, Shooter.kI);
    talonShooterTop.config_kD(0, Shooter.kD);
    
  }
}
