/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ler.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
  
  double maxOutput=1;
  private final  double DEADZONE = 0.15;
  private boolean isInverted = false;

  public final PIDController pidController = new PIDController(7, 0.018, 1.5);


  public int getLeftEncoder() {
    return((int) ((RobotMap.leftDriveSpark1.getEncoder().getPosition()) + (RobotMap.leftDriveSpark2.getEncoder().getPosition()) + (RobotMap.leftDriveSpark3.getEncoder().getPosition())/3.00));
  }
  public int getRightEncoder() {
    return((int) ((RobotMap.rightDriveSpark1.getEncoder().getPosition()) + (RobotMap.rightDriveSpark2.getEncoder().getPosition()) + (RobotMap.rightDriveSpark3.getEncoder().getPosition())/3.00));
  }
  public void resetPosition(){
    RobotMap.leftDriveSpark1.getEncoder().setPosition(0);
    RobotMap.leftDriveSpark2.getEncoder().setPosition(0);
    RobotMap.leftDriveSpark3.getEncoder().setPosition(0);
    RobotMap.rightDriveSpark1.getEncoder().setPosition(0);
    RobotMap.rightDriveSpark2.getEncoder().setPosition(0);
    RobotMap.rightDriveSpark3.getEncoder().setPosition(0);
  }




  /**
   * Creates a new DriveSubsystem.
   */
  public Drivetrain() {

  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param left the commanded forward movement
   * @param right the commanded rotation
   */
  public void tankDrive(double left, double right) {

    if (Math.abs(left)<DEADZONE){
      left = 0;

    }
    if (Math.abs(right)<DEADZONE){
      right = 0;
    }

    //Slow it down
    left *= 0.9;
    right *= 0.9;
    
    if(isInverted){
      double tempRight = left * -1;
      left = right * -1;
      right = tempRight;
    }
    RobotMap.leftDriveSpark1.set(left);
    RobotMap.rightDriveSpark1.set(right);
    System.out.println("Actual Left" + left+"\t"+ "Actual Right" + right);
  }

  public void tankStop() {
    RobotMap.leftDriveSpark1.set(0);
    RobotMap.rightDriveSpark1.set(0);
  }

  //@todo currently not mapped to anything
  public void invertControls(){
    isInverted = !isInverted;
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    this.maxOutput = maxOutput;
  }
}
