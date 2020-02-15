/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import ler.robot.subsystems.Conveyor;
import ler.robot.subsystems.Intake;

public class DefaultIntake extends CommandBase {
  /**
   * Creates a new IntakeCommand.
   */
  private final Intake intake;
  private final Conveyor conveyor;
  private double speed;

  public DefaultIntake(Intake intake, Conveyor conveyor, DoubleSupplier speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    this.conveyor = conveyor;

    this.speed = speed.getAsDouble();
    addRequirements(intake);
    addRequirements(conveyor);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
    intake.StartIntake(Intake.ROLLER_SPEED);
    conveyor.setConveyorSpeed(Conveyor.INTAKE_SPEED);
    */
    intake.StartIntake(speed);
    conveyor.setConveyorSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
