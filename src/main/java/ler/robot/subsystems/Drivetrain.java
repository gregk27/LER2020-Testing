/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ler.robot.RobotMap;

/**
 * Subsystem representing the drivetrain components.
 */
public class Drivetrain extends SubsystemBase {
  
  double maxOutput=1;
  private boolean isInverted = false;
  /** Drivetrain input is multiplied by this value. */
  static final double SPEED_ADJUST = 0.9;

  /**
   * Creates a new DriveSubsystem.
   */
  public Drivetrain() {

  }

  /**
   * Get the encoder position for the left side of the drivetrain.
   * @return The encoder position, with respect to configured conversion factor
   */
  public double getLeftEncoder() {
    return(((RobotMap.leftDriveSpark1.getEncoder().getPosition())));// + (RobotMap.leftDriveSpark2.getEncoder().getPosition()) + (RobotMap.leftDriveSpark3.getEncoder().getPosition())/3.00));
  }
  
  /**
   * Get the encoder position for the right side of the drivetrain.
   * @return The encoder position, with respect to configured conversion factor
   */
  public double getRightEncoder() {
    return(((RobotMap.rightDriveSpark1.getEncoder().getPosition())));// + (RobotMap.rightDriveSpark2.getEncoder().getPosition()) + (RobotMap.rightDriveSpark3.getEncoder().getPosition())/3.00));
  }

  /**
   * Reset drivetrain encoders to 0. 
   */
  public void resetPosition(){
    RobotMap.leftDriveSpark1.getEncoder().setPosition(0);
    RobotMap.leftDriveSpark2.getEncoder().setPosition(0);
    RobotMap.leftDriveSpark3.getEncoder().setPosition(0);
    RobotMap.rightDriveSpark1.getEncoder().setPosition(0);
    RobotMap.rightDriveSpark2.getEncoder().setPosition(0);
    RobotMap.rightDriveSpark3.getEncoder().setPosition(0);
  }


  /**
   * Drives the robot using arcade controls.
   *
   * @param left the commanded forward movement
   * @param right the commanded rotation
   */
  public void tankDrive(double left, double right) {

    //Slow it down
    left *= SPEED_ADJUST;
    right *= SPEED_ADJUST;
    
    if(isInverted){
      double tempRight = left * -1;
      left = right * -1;
      right = tempRight;
    }
    RobotMap.leftDriveSpark1.set(left);
    RobotMap.rightDriveSpark1.set(right);
    //System.out.println("Actual Left" + left+"\t"+ "Actual Right" + right);
  }

  /**
   * Stop both sides drivetrain.
   */
  public void tankStop() {
    RobotMap.leftDriveSpark1.set(0);
    RobotMap.rightDriveSpark1.set(0);
  }

  /**
   * Invert the drivetrain controls.
   * @TODO: currently not mapped to anything
   */
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

  /**
   * Set drivetrain speeds directly without wrapper code.
   * 
   * @param l Left % output
   * @param r Right % output
   */
  public void setPercentVoltage(double l, double r) {
		RobotMap.leftDriveSpark1.set(l);	// because talons 2 and 3 follow 1, we only need to set 1
		RobotMap.rightDriveSpark1.set(-r);
	}
}
