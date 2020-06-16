/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  
  double maxOutput=1;
  private boolean isInverted = false;

  CANSparkMax leftSide;
  CANSparkMax rightSide;

  public Drivetrain(CANSparkMax leftLead, CANSparkMax rightLead){
    
  }

  public double getLeftEncoder() {
    return(((leftSide.getEncoder().getPosition())));// + (RobotMap.leftDriveSpark2.getEncoder().getPosition()) + (RobotMap.leftDriveSpark3.getEncoder().getPosition())/3.00));
  }
  public double getRightEncoder() {
    return(((rightSide.getEncoder().getPosition())));// + (RobotMap.rightDriveSpark2.getEncoder().getPosition()) + (RobotMap.rightDriveSpark3.getEncoder().getPosition())/3.00));
  }
  public void resetPosition(){
    leftSide.getEncoder().setPosition(0);
    rightSide.getEncoder().setPosition(0);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param left the commanded forward movement
   * @param right the commanded rotation
   */
  public void tankDrive(double left, double right) {

    //Slow it down
    left *= 0.9;
    right *= 0.9;
    
    if(isInverted){
      double tempRight = left * -1;
      left = right * -1;
      right = tempRight;
    }
    leftSide.set(left);
    rightSide.set(right);
    //System.out.println("Actual Left" + left+"\t"+ "Actual Right" + right);
  }

  public void tankStop() {
    leftSide.set(0);
    rightSide.set(0);
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

  public void setPercentVoltage(double l, double r) {
		leftSide.set(l);	// because talons 2 and 3 follow 1, we only need to set 1
		rightSide.set( -r);
	}
}
