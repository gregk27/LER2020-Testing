/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands.instant;

import ler.robot.subsystems.Conveyor;
import ler.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.InstantCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ConveyorLimelightTiltCommand extends InstantCommand {
  Conveyor conveyor;
  Limelight limelight;
  public ConveyorLimelightTiltCommand(Conveyor conveyor, Limelight limelight) {
    this.conveyor = conveyor;
    this.limelight = limelight;
    //this addrequirements might make it so that you can't run the conveyor
    addRequirements(conveyor);
    addRequirements(limelight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    conveyor.setSpecificConveyorAngle(limelight.getAngle());
  }
}
