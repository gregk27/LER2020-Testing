/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import ler.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.XboxController.Button;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class RobotMap {
  public static final class CANConstants {

    public static final int LEFT_DRIVE_SPARK_1 = 2;
    public static final int LEFT_DRIVE_SPARK_2 = 3;
    public static final int LEFT_DRIVE_SPARK_3 = 4;
    public static final int RIGHT_DRIVE_SPARK_1 = 5;
    public static final int RIGHT_DRIVE_SPARK_2 = 6;
    public static final int RIGHT_DRIVE_SPARK_3 = 7;
    
    public static final int INTAKE_TALON = 8;

    public static final int SHOOTER_BOTTOM_LEFT_SPARK = 9;
    public static final int SHOOTER_TOP_LEFT_SPARK = 10;
    public static final int SHOOTER_BOTTOM_RIGHT_SPARK = 11;
    public static final int SHOOTER_TOP_RIGHT_SPARK = 12;

    public static final int CLIMBER_TALON = 13;

    public static final int CONVEYOR_TALON = 14;
    public static final int ANGLE_ELEVATION = 15;
  
  }

  public static final class SOLENOIDConstants {
    
    public static final int ANGLE_ELEVATION_DOWN = 0;
    public static final int ANGLE_ELEVATION_UP = 1;
    public static final int INTAKE_DOWN = 2;
    public static final int INTAKE_UP = 3;
  }

  public static final class OIConstants {
    
    public static final int LEFT_DRIVER_JOYSTICK = 0;
    public static final int RIGHT_DRIVER_JOYSTICK = 1;
    public static final int OPERATOR_CONTROLLER_PORT = 2;

    public static final int HALF_SPEED_BUTTON = 2;
    public static final int INVERT_CONTROLS_BUTTON = 1;
    public static final int LIMELIGHT_AIM_BUTTON = 2;

    public static final int INTAKE_BUTTON = Button.kB.value;

    public static final int SHOOTER_CONTROL_BUTTON = Button.kBumperRight.value;
    public static final int SHOOT_BUTTON = Button.kBumperLeft.value; 
    public static final int SHOOTER_TILT_BUTTON = Button.kBumperLeft.value; 

  }


  // The motors on the left side of the drive.
  public static final CANSparkMax leftDriveSpark1 = new CANSparkMax(CANConstants.LEFT_DRIVE_SPARK_1, MotorType.kBrushless);
  public static final CANSparkMax leftDriveSpark2 = new CANSparkMax(CANConstants.LEFT_DRIVE_SPARK_2, MotorType.kBrushless);
  public static final CANSparkMax leftDriveSpark3 = new CANSparkMax(CANConstants.LEFT_DRIVE_SPARK_3, MotorType.kBrushless);

  // The motors on the right side of the drive.
  public static final CANSparkMax rightDriveSpark1 = new CANSparkMax(CANConstants.RIGHT_DRIVE_SPARK_1, MotorType.kBrushless);
  public static final CANSparkMax rightDriveSpark2 = new CANSparkMax(CANConstants.RIGHT_DRIVE_SPARK_2, MotorType.kBrushless);
  public static final CANSparkMax rightDriveSpark3 = new CANSparkMax(CANConstants.RIGHT_DRIVE_SPARK_3, MotorType.kBrushless);

  // The talons on the shooter
  public static final CANSparkMax shooterTopLeftSpark = new CANSparkMax(CANConstants.SHOOTER_TOP_LEFT_SPARK, MotorType.kBrushless);
  public static final CANSparkMax shooterBottomLeftSpark = new CANSparkMax(CANConstants.SHOOTER_BOTTOM_LEFT_SPARK, MotorType.kBrushless);
  public static final CANSparkMax shooterTopRightSpark = new CANSparkMax(CANConstants.SHOOTER_TOP_RIGHT_SPARK, MotorType.kBrushless);
  public static final CANSparkMax shooterBottomRightSpark = new CANSparkMax(CANConstants.SHOOTER_BOTTOM_RIGHT_SPARK, MotorType.kBrushless);

  //The conveyor talon
  public static final TalonSRX conveyorMotor = new TalonSRX(CANConstants.CONVEYOR_TALON);

  //The intake (talon + piston)
  public static final TalonSRX intakeRoller = new TalonSRX(CANConstants.INTAKE_TALON);
  public static DoubleSolenoid intakeArm = new DoubleSolenoid(SOLENOIDConstants.INTAKE_DOWN, SOLENOIDConstants.INTAKE_UP);

  //The angle elevation talon
  public static final TalonFX angleElevation = new TalonFX(CANConstants.ANGLE_ELEVATION);

  // Climber control
  public static final TalonFX climbingMech = new TalonFX(CANConstants.CLIMBER_TALON);


  public static void init(){
    leftDriveSpark2.follow(leftDriveSpark1);
    leftDriveSpark3.follow(leftDriveSpark1);

    leftDriveSpark1.setInverted(true);
    rightDriveSpark1.setInverted(false);

    leftDriveSpark1.setOpenLoopRampRate(0.5);
    rightDriveSpark1.setOpenLoopRampRate(0.5);

    rightDriveSpark2.follow(rightDriveSpark1);
    rightDriveSpark3.follow(rightDriveSpark1);

    //SHOOTER PIDs
    shooterTopLeftSpark.getPIDController().setP(Shooter.kP);
    shooterTopLeftSpark.getPIDController().setI(Shooter.kI);
    shooterTopLeftSpark.getPIDController().setD(Shooter.kD);
    shooterTopLeftSpark.getPIDController().setFF(Shooter.kF);

    shooterTopRightSpark.getPIDController().setP(Shooter.kP);
    shooterTopRightSpark.getPIDController().setI(Shooter.kI);
    shooterTopRightSpark.getPIDController().setD(Shooter.kD);
    shooterTopRightSpark.getPIDController().setFF(Shooter.kF);

    shooterBottomLeftSpark.getPIDController().setP(Shooter.kP);
    shooterBottomLeftSpark.getPIDController().setI(Shooter.kI);
    shooterBottomLeftSpark.getPIDController().setD(Shooter.kD);
    shooterBottomLeftSpark.getPIDController().setFF(Shooter.kF);

    shooterBottomRightSpark.getPIDController().setP(Shooter.kP);
    shooterBottomRightSpark.getPIDController().setI(Shooter.kI);
    shooterBottomRightSpark.getPIDController().setD(Shooter.kD);
    shooterBottomRightSpark.getPIDController().setFF(Shooter.kF);
  }
}
