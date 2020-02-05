/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import ler.robot.RobotMap;
import ler.robot.subsystems.ShooterSubsystem;

public class DefaultShooter extends CommandBase {
  private int currentSpeed;

  private final ShooterSubsystem m_shooter;
  private final int speedCount = RobotMap.ShooterConstants.speeds.length;

    /**
   * Creates a new default shooter
   * @param subsystem
   * @param zero
   * @param faster
   * @param slower
   */
  public DefaultShooter(ShooterSubsystem subsystem, BooleanSupplier zero, BooleanSupplier faster, BooleanSupplier slower) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem;
    if (zero.getAsBoolean()){
      currentSpeed = 0;
      m_shooter.talonResetPos();
    }
    if (faster.getAsBoolean()){
      currentSpeed++;
      if(currentSpeed > speedCount){
        currentSpeed--;
      }
    }
    if (slower.getAsBoolean()){
      currentSpeed --;
      if(currentSpeed < speedCount){
        currentSpeed++;
      }
    }

    addRequirements(m_shooter);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.setShooterSpeed(currentSpeed);
  }

}
