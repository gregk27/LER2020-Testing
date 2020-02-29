/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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
  /**
   * Subclass to hold all mapping constants (motors, DIO ports, etc)
   */
  public static final class Mappings {
    public static final int LEFT_DRIVE_SPARK_1 = 5;
    public static final int LEFT_DRIVE_SPARK_2 = 6;
    public static final int LEFT_DRIVE_SPARK_3 = 7;
    public static final int RIGHT_DRIVE_SPARK_1 = 2;
    public static final int RIGHT_DRIVE_SPARK_2 = 3;
    public static final int RIGHT_DRIVE_SPARK_3 = 4;
    

    public static final int SHOOTER_TOP_SPARK = 9;
    public static final int SHOOTER_BOTTOM_SPARK = 8;

    public static final int CONVEYOR_TALON = 12;

   public static final int INTAKE_TALON = 11;
  }

  public static final class OIConstant{

    public static final int HALF_SPEED_BUTTON = 2;
    public static final int INVERT_CONTROLS_BUTTON = 1;
    public static final int LIMELIGHT_AIM_BUTTON = 2;

    public static final int INTAKE_BUTTON = Button.kB.value;
    public static final int REVERSE_BOTH_BUTTON = Button.kX.value;
    public static final int REVERSE_INTAKE_BUTTON = Button.kY.value;
    public static final int CONVEYOR_BUTTON = Button.kA.value;

    public static final int SHOOTER_CONTROL_BUTTON = Button.kBumperRight.value;
    public static final int SHOOT_BUTTON = Button.kBumperLeft.value; 
    public static final int SHOOTER_TILT_BUTTON = Button.kBumperLeft.value; 
  }
  // The motors on the left side of the drive.
  public static final CANSparkMax leftDriveSpark1 = new CANSparkMax(Mappings.LEFT_DRIVE_SPARK_1, MotorType.kBrushless);
  public static final CANSparkMax leftDriveSpark2 = new CANSparkMax(Mappings.LEFT_DRIVE_SPARK_2, MotorType.kBrushless);
  public static final CANSparkMax leftDriveSpark3 = new CANSparkMax(Mappings.LEFT_DRIVE_SPARK_3, MotorType.kBrushless);
  

  // The motors on the right side of the drive.
  public static final CANSparkMax rightDriveSpark1 = new CANSparkMax(Mappings.RIGHT_DRIVE_SPARK_1, MotorType.kBrushless);
  public static final CANSparkMax rightDriveSpark2 = new CANSparkMax(Mappings.RIGHT_DRIVE_SPARK_2, MotorType.kBrushless);
  public static final CANSparkMax rightDriveSpark3 = new CANSparkMax(Mappings.RIGHT_DRIVE_SPARK_3, MotorType.kBrushless);

  // The talons on the shooter
  public static final CANSparkMax shooterTopSpark = new CANSparkMax(Mappings.SHOOTER_TOP_SPARK, MotorType.kBrushless);
  public static final CANSparkMax shooterBottomSpark = new CANSparkMax(Mappings.SHOOTER_BOTTOM_SPARK, MotorType.kBrushless);

  //The conveyor 
  public static final TalonSRX conveyorMotor = new TalonSRX(Mappings.CONVEYOR_TALON);
  //The intake
  public static final TalonSRX intakeRoller = new TalonSRX(Mappings.INTAKE_TALON);


  public static void init(){
    leftDriveSpark2.follow(leftDriveSpark1);
    leftDriveSpark3.follow(leftDriveSpark1);

    leftDriveSpark1.setInverted(true);
    rightDriveSpark1.setInverted(false);

    leftDriveSpark1.setOpenLoopRampRate(0.5);
    rightDriveSpark1.setOpenLoopRampRate(0.5);

    rightDriveSpark2.follow(rightDriveSpark1);
    rightDriveSpark3.follow(rightDriveSpark1);

    //shooter init
    

    shooterTopSpark.getPIDController().setP(Shooter.kP);
    shooterTopSpark.getPIDController().setI(Shooter.kI);
    shooterTopSpark.getPIDController().setD(Shooter.kD);
    shooterTopSpark.getPIDController().setFF(Shooter.kF);

    

    shooterBottomSpark.getPIDController().setP(Shooter.kP);
    shooterBottomSpark.getPIDController().setI(Shooter.kI);
    shooterBottomSpark.getPIDController().setD(Shooter.kD);
    shooterBottomSpark.getPIDController().setFF(Shooter.kF);

  

  }
}
