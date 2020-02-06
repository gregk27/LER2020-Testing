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
    public static final int LEFT_DRIVE_SPARK_1 = 1;
    public static final int LEFT_DRIVE_SPARK_2 = 2;
    public static final int LEFT_DRIVE_SPARK_3 = 3;
    public static final int RIGHT_DRIVE_SPARK_1 = 4;
    public static final int RIGHT_DRIVE_SPARK_2 = 5;
    public static final int RIGHT_DRIVE_SPARK_3 = 6;
    
    public static final int SHOOTER_TOP_TALON = 8;
    public static final int SHOOTER_BOTTOM_TALON = 9;
    
    //TODO: Calibrate the fake values
    public static final int CONVEYOR_TALON = 2708;
    //TODO: Calibrate the fake values
   public static final int INTAKE_TALON = 2708;
  }

  // The motors on the left side of the drive.
  public static final CANSparkMax leftDriveSpark1 = new CANSparkMax(Mappings.LEFT_DRIVE_SPARK_1, MotorType.kBrushless);
  public static final CANSparkMax leftDriveSpark2 = new CANSparkMax(Mappings.LEFT_DRIVE_SPARK_2, MotorType.kBrushless);
  public static final CANSparkMax leftDriveSpark3 = new CANSparkMax(Mappings.LEFT_DRIVE_SPARK_3, MotorType.kBrushless);
  

  // The motors on the right side of the drive.
  public static final CANSparkMax rightDriveSpark1 = new CANSparkMax(Mappings.RIGHT_DRIVE_SPARK_1, MotorType.kBrushless);
  public static final CANSparkMax rightDriveSpark2 = new CANSparkMax(Mappings.RIGHT_DRIVE_SPARK_2, MotorType.kBrushless);
  public static final CANSparkMax rightDriveSpark3 = new CANSparkMax(Mappings.RIGHT_DRIVE_SPARK_3, MotorType.kBrushless);

  // The robot's drive
  public static final DifferentialDrive m_drive = new DifferentialDrive(leftDriveSpark1, rightDriveSpark1);

  // The talons on the shooter
  public static final TalonSRX shooterTopTalon = new TalonSRX(Mappings.SHOOTER_TOP_TALON);
  public static final TalonSRX shooterBottomTalon = new TalonSRX(Mappings.SHOOTER_BOTTOM_TALON);

  //The conveyor 
  public static final TalonSRX conveyorMotor = new TalonSRX(Mappings.CONVEYOR_TALON);
  //The intake
  public static final TalonSRX intakeRoller = new TalonSRX(Mappings.INTAKE_TALON);


  public static void init(){
    leftDriveSpark2.follow(leftDriveSpark1);
    leftDriveSpark3.follow(leftDriveSpark1);

    leftDriveSpark1.setInverted(true);
    rightDriveSpark1.setInverted(false);

    rightDriveSpark2.follow(rightDriveSpark1);
    rightDriveSpark3.follow(rightDriveSpark1);

    //shooter init
    shooterTopTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

    shooterTopTalon.config_kF(0, Shooter.kF);
    shooterTopTalon.config_kP(0, Shooter.kP);
    shooterTopTalon.config_kI(0, Shooter.kI);
    shooterTopTalon.config_kD(0, Shooter.kD);
    
  }
}
