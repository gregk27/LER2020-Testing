/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import ler.robot.Tools;
import ler.robot.subsystems.Drivetrain;
import ler.robot.subsystems.Gyro;

public class AutoDriveStraightCommand extends CommandBase {
  Drivetrain drivetrain;
  Gyro gyro;

  public long startAutoTime = System.currentTimeMillis();
  public long timeOut;
  double speed;
  int target;
  
  final double kP=1/60.0;
  

  /**
   * Creates a new IntakeCommand.
   */
  public AutoDriveStraightCommand(Drivetrain d, Gyro g, int timeOut, double speed, int target) {
    this.drivetrain = d;
    this.timeOut = timeOut;
    this.speed = speed;
    this.target = target;
    this.gyro = g;
    // System.out.println("Command starting");

    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      
      // System.out.println("isInitializing");
      drivetrain.resetPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftError = target-drivetrain.getLeftEncoder();
    double rightError = target-drivetrain.getRightEncoder();

    double error = (leftError+rightError)/2.00;
    
    System.out.println("error: "+error+", output:"+error*kP);

    // double[] gyroOutput = gyro.getStraightOutput(error*kP*speed, error*kP*speed);
    double speed = Tools.fitToRange(error*kP,-0.2, 0.2);
    drivetrain.tankDrive(speed, speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // System.out.println("DEAD");
    drivetrain.tankStop();
}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if(System.currentTimeMillis() >= startAutoTime+timeOut) {
    //     return true;
    // }

    return false;
  }
}
