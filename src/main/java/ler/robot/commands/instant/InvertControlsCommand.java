/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands.instant;

import edu.wpi.first.wpilibj2.command.CommandBase;
import ler.robot.subsystems.Drivetrain;

/**
 * Command that toggles inversion of drive controls. 
 */
public class InvertControlsCommand extends CommandBase{
  Drivetrain drivetrain;

  /**
   * Creates a new invertControlsCommand.
   * 
   * @param drivetrain The drivetrain subsystem
   */
  public InvertControlsCommand(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.invertControls();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.invertControls();
  }

}
