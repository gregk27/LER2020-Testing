/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import ler.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  // The motors on the left side of the drive.
  private static final CANSparkMax m_leftMotors = new CANSparkMax(DriveConstants.kLeftMotor1Port, MotorType.kBrushless);
  private static final CANSparkMax m_leftMotor2 = new CANSparkMax(DriveConstants.kLeftMotor2Port, MotorType.kBrushless);
  private static final CANSparkMax m_leftMotor3 = new CANSparkMax(DriveConstants.kLeftMotor3Port, MotorType.kBrushless);
  

  // The motors on the right side of the drive.
  private static final CANSparkMax m_rightMotors = new CANSparkMax(DriveConstants.kRightMotor1Port, MotorType.kBrushless);
  private static final CANSparkMax m_rightMotor2 = new CANSparkMax(DriveConstants.kRightMotor2Port, MotorType.kBrushless);
  private static final CANSparkMax m_rightMotor3 = new CANSparkMax(DriveConstants.kRightMotor3Port, MotorType.kBrushless);

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  /*
  // The left-side drive encoder
  private final Encoder m_leftEncoder =
      new Encoder(DriveConstants.kLeftEncoderPorts[0], DriveConstants.kLeftEncoderPorts[1],
                  DriveConstants.kLeftEncoderReversed);

  // The right-side drive encoder
  private final Encoder m_rightEncoder =
      new Encoder(DriveConstants.kRightEncoderPorts[0], DriveConstants.kRightEncoderPorts[1],
                  DriveConstants.kRightEncoderReversed);
  */

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    // Sets the distance per pulse for the encoders
    /*
    m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    */
  }

  public static void init(){
    m_leftMotor2.follow(m_leftMotors);
    m_leftMotor3.follow(m_leftMotors);

    m_leftMotors.setInverted(true);
    m_rightMotors.setInverted(false);

    m_rightMotor2.follow(m_rightMotors);
    m_rightMotor3.follow(m_rightMotors);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void tankDrive(double left, double right) {
    //slow it down
    left *= 0.25;
    right *= 0.25;
    System.out.println(left);
    System.out.println(right);
    m_leftMotors.set(left);
    m_rightMotors.set(right);
  }

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  /*
  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }
  */

  /**
   * Gets the average distance of the TWO encoders.
   *
   * @return the average of the TWO encoder readings
   */

   /*
  public double getAverageEncoderDistance() {
    return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
  }
  */

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */

   /*
  public Encoder getLeftEncoder() {
    return m_leftEncoder;
  }
  */

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */

   /*
  public Encoder getRightEncoder() {
    return m_rightEncoder;
  }
  */

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }
}
