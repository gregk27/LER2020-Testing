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

import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class RobotMap {
  public static final class DriveConstants {
    public static final int kLeftMotor1Port = 1;
    public static final int kLeftMotor2Port = 2;
    public static final int kLeftMotor3Port = 3;
    public static final int kRightMotor1Port = 4;
    public static final int kRightMotor2Port = 5;
    public static final int kRightMotor3Port = 6;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 1;
  }

  public static final class ShooterConstants {
    public static final int talonShooterTop = 8;
    public static final int talonShooterBottom = 9;

    public static final double kP = 0.25;
    public static final double kI = 0.001;
    public static final double kD = 20;
    public static final double kF = 1;

    public static final int[] speeds = {0, 300, 600, 900, 1200};
    public static final int ZEROSPEED = 0;

    //Cycles per revolution of encoders on shooter
    public static final int cPR = 64;
  }

  // The motors on the left side of the drive.
  public static final CANSparkMax m_leftMotors = new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless);
  public static final CANSparkMax m_leftMotor2 = new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless);
  public static final CANSparkMax m_leftMotor3 = new CANSparkMax(DriveConstants.kLeftMotor3Port, MotorType.kBrushless);
  

  // The motors on the right side of the drive.
  public static final CANSparkMax m_rightMotors = new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless);
  public static final CANSparkMax m_rightMotor2 = new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless);
  public static final CANSparkMax m_rightMotor3 = new CANSparkMax(DriveConstants.kRightMotor3Port, MotorType.kBrushless);

  // The robot's drive
  public static final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // The talons on the shooter
  public static final TalonSRX talonShooterTop = new TalonSRX(ShooterConstants.talonShooterTop);
  public static final TalonSRX talonShooterBottom = new TalonSRX(ShooterConstants.talonShooterBottom);

  public static void init(){
    //drivetrain init
    m_leftMotor2.follow(m_leftMotors);
    m_leftMotor3.follow(m_leftMotors);

    m_leftMotors.setInverted(true);
    m_rightMotors.setInverted(false);

    m_rightMotor2.follow(m_rightMotors);
    m_rightMotor3.follow(m_rightMotors);

    //shooter init
    talonShooterTop.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

    talonShooterTop.config_kF(0, ShooterConstants.kF);
    talonShooterTop.config_kP(0, ShooterConstants.kP);
    talonShooterTop.config_kI(0, ShooterConstants.kI);
    talonShooterTop.config_kD(0, ShooterConstants.kD);
  }
}
