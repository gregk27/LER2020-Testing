/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import ler.robot.commands.*;
import ler.robot.commands.instant.*;


/**
 * Operator Interface, used to map buttons with the controllers
 */
public class OI {
    
    // The driver uses two joysticks, operator uses one controller
    
    public XboxController driverXboxController = new XboxController(RobotMap.OIConstants.DRIVER_XBOX_CONTROLLER);
    
    public Joystick leftDriverJoystick = new Joystick(RobotMap.OIConstants.LEFT_DRIVER_JOYSTICK);
    public Joystick rightDriverJoystick = new Joystick(RobotMap.OIConstants.RIGHT_DRIVER_JOYSTICK);
    

    public XboxController operatorController = new XboxController(RobotMap.OIConstants.OPERATOR_CONTROLLER_PORT);


    public JoystickButton halfSpeedButton = new JoystickButton(leftDriverJoystick, RobotMap.OIConstants.HALF_SPEED_BUTTON);
    public JoystickButton invertControlsButton = new JoystickButton(rightDriverJoystick, RobotMap.OIConstants.INVERT_CONTROLS_BUTTON);
    public JoystickButton limelightAimButton = new JoystickButton(rightDriverJoystick, RobotMap.OIConstants.LIMELIGHT_AIM_BUTTON);

    public JoystickButton xboxHalfSpeedButton = new JoystickButton(driverXboxController, RobotMap.OIConstants.HALF_SPEED_BUTTON);
    public JoystickButton xboxInvertControlsButton = new JoystickButton(driverXboxController, RobotMap.OIConstants.INVERT_CONTROLS_BUTTON);
    public JoystickButton xboxLimelightAimButton = new JoystickButton(driverXboxController, RobotMap.OIConstants.LIMELIGHT_AIM_BUTTON);
  
    
    public JoystickButton shooterRevButton = new JoystickButton(operatorController, RobotMap.OIConstants.SHOOTER_CONTROL_BUTTON);
    public JoystickButton shootButton = new JoystickButton(operatorController, RobotMap.OIConstants.SHOOT_BUTTON);
    public JoystickButton shooterTiltButton = new JoystickButton(operatorController, RobotMap.OIConstants.SHOOTER_TILT_BUTTON);

    public JoystickButton conveyorPistonButton = new JoystickButton(operatorController, RobotMap.OIConstants.CONVEYOR_PISTON_BUTTON);
    
    public JoystickButton intakeButton = new JoystickButton(operatorController, RobotMap.OIConstants.INTAKE_BUTTON);
    public JoystickButton intakeExtendButton = new JoystickButton(operatorController, RobotMap.OIConstants.INTAKE_EXTEND_BUTTON);

    public JoystickButton climberExtendButton = new JoystickButton(operatorController, RobotMap.OIConstants.CLIMBER_EXTEND_BUTTON);
    public JoystickButton climberWinchButton = new JoystickButton(operatorController, RobotMap.OIConstants.CLIMBER_WINCH_BUTTON);

    

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    public void init(RobotContainer container){
        /*
         * // Grab the hatch when the 'A' button is pressed. new
         * JoystickButton(m_driverController, Button.kA.value) .whenPressed(new
         * GrabHatch(m_hatchSubsystem)); // Release the hatch when the 'B' button is
         * pressed. new JoystickButton(m_driverController, Button.kB.value)
         * .whenPressed(new ReleaseHatch(m_hatchSubsystem));
         */
        // While holding the shoulder button, drive at half speed
        if(RobotMap.XBOX_DRIVE){
            halfSpeedButton = xboxHalfSpeedButton;
            invertControlsButton = xboxInvertControlsButton;
            limelightAimButton = xboxLimelightAimButton;
        }
        
        halfSpeedButton.whenHeld(new HalveDriveSpeed(container.drivetrain));
        invertControlsButton.whenPressed(new InvertControlsCommand(container.drivetrain));
        limelightAimButton.whenPressed(new LimelightAimCommand(container.drivetrain, container.limelight));

        intakeButton.whenHeld(new IntakeCommand(container.intake,container.conveyor));
        intakeExtendButton.whenPressed(new IntakeExtendCommand(container.intake));

        //shooterRevButton revs shooter, shootButton feeds ball from conveyor to shooter
        //should use whenHeld, oh well
        shooterRevButton.whenPressed(new ShooterStartCommand(container.shooter, container.limelight));
        shooterRevButton.whenReleased(new ShooterStopCommand(container.shooter, container.conveyor));
        shootButton.whenHeld(new ShootCommand(container.shooter, container.conveyor, container.limelight));
        shooterTiltButton.whenPressed(new ShooterTiltCommand());

        conveyorPistonButton.whenPressed(new ConveyorExtendCommand(container.conveyor));

        climberExtendButton.whenPressed(new ClimberExtendCommand(container.climber));
        climberWinchButton.whenHeld(new ClimberUpCommand(container.climber));
        
    }

}
