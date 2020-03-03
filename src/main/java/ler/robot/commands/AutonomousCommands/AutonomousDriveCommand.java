/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import ler.robot.subsystems.Drivetrain;

public class AutonomousDriveCommand extends CommandBase {
  Drivetrain drivetrain;

  public long startAutoTime = System.currentTimeMillis();
  public long timeOut;


  /**
   * Creates a new IntakeCommand.
   */
  public AutonomousDriveCommand(Drivetrain d, int t, double l, double r) {
    this.drivetrain = d;
    this.timeOut = t;
    

    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      drivetrain.tankStop();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.tankDrive(50, 50);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.tankStop();
}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(System.currentTimeMillis() >= startAutoTime+timeOut) {
        return true;
    }

    return true;
  }
}
