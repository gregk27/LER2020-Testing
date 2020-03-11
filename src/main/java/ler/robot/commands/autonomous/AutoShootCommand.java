/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands.autonomous;

import ler.robot.RobotContainer;
import ler.robot.commands.LimelightAimCommand;
import ler.robot.commands.instant.ConveyorLimelightTiltCommand;
import ler.robot.commands.ShootCommand;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoShootCommand extends ParallelCommandGroup {
  long timeOut;
  long startTime;
  /**
   * Creates a new AutoShootCommand.
   */
  public AutoShootCommand(RobotContainer container, long timeOut) {
    super();

    this.timeOut = timeOut;

    startTime = System.currentTimeMillis();

    addCommands(
      new LimelightAimCommand(container.drivetrain, container.limelight),
      new ConveyorLimelightTiltCommand(container.conveyor, container.limelight),
      new ShootCommand(container.shooter, container.conveyor, container.limelight)
     );
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
  }

  @Override
  public boolean isFinished() {
    if(System.currentTimeMillis() > startTime + timeOut){
      return true;
    }
    return false;
  }
}
