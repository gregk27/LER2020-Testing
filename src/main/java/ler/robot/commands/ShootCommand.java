/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import ler.robot.subsystems.Conveyor;
import ler.robot.subsystems.Limelight;
import ler.robot.subsystems.Shooter;

public class ShootCommand extends CommandBase {
  private Shooter shooter;
  private Conveyor conveyor;
  private Limelight limelight;
  /**
   * Creates a new ShootCommand.
   */
  public ShootCommand(Shooter shooter, Conveyor conveyor, Limelight limelight) {
    this.shooter = shooter;
    this.conveyor = conveyor;
    this.limelight = limelight;
    addRequirements(shooter);
    addRequirements(conveyor);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //close the conveyor
    conveyor.setValveState(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //configure, maybe add closeness to robotMap or something
    double speed = limelight.getSpeed();
    double closeness = 250;
    double conveyorSpeed = 0.6;

    System.out.println("TopLeft: " + shooter.getTopLeftSparkSpeed() + "\t" + Shooter.SHOOTER_TOP_TARGET_SPEED + "\t" + (shooter.getTopLeftSparkSpeed()-Shooter.SHOOTER_TOP_TARGET_SPEED));
    System.out.println("TopRight: " + shooter.getTopRightSparkSpeed() + "\t" + Shooter.SHOOTER_TOP_TARGET_SPEED + "\t" + (shooter.getTopRightSparkSpeed()-Shooter.SHOOTER_TOP_TARGET_SPEED));
    System.out.println("BottomLeft: " + shooter.getBottomLeftSparkSpeed() + "\t" + Shooter.SHOOTER_BOTTOM_TARGET_SPEED + "\t" + (shooter.getBottomLeftSparkSpeed()-Shooter.SHOOTER_BOTTOM_TARGET_SPEED));
    System.out.println("BottomRight: " + shooter.getBottomRightSparkSpeed() + "\t" + Shooter.SHOOTER_BOTTOM_TARGET_SPEED + "\t" + (shooter.getBottomRightSparkSpeed()-Shooter.SHOOTER_BOTTOM_TARGET_SPEED));

    // TODO determine how to calculate if it is fast enough to run
    //remember kids, don't make your if statements like this
    /*
    if(Math.abs(shooter.getTopSparkSpeed() - Shooter.SHOOTER_TOP_TARGET_SPEED)< closeness && 
       Math.abs(shooter.getBottomSparkSpeed()- Shooter.SHOOTER_BOTTOM_TARGET_SPEED)< closeness &&
       System.currentTimeMillis()>= shooter.spoolTime) {
         */
    if(Math.abs(shooter.getAverageSparksSpeed() - Shooter.SHOOTER_TOP_TARGET_SPEED)< closeness && 
      System.currentTimeMillis()>= shooter.spoolTime) {

      conveyor.setConveyorSpeed(conveyorSpeed);

    } else {
      conveyor.setConveyorSpeed(0);
    
    }
  
    /*
    if(Math.abs(shooter.getAverageSparkSpeed() - speed) < closeness){
      conveyor.setConveyorSpeed(conveyorSpeed);
    }else{
      conveyor.setConveyorSpeed(0);
    }
    */
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    conveyor.setConveyorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
