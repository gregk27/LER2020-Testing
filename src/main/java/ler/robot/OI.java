/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import ler.robot.commands.*;


/**
 * Operator Interface, used to map buttons with the controllers
 */
public class OI {

    public static final int DRIVER_CONTROLLER_PORT = 1;
    public static final int OPERATOR_CONTROLLER_PORT = 2;

    
    public static final class ButtonMappings {
        public static final int HALF_SPEED_BUTTON = Button.kBumperRight.value;
        public static final int INVERT_CONTROLS_BUTTON = Button.kA.value;

        public static final int SHOOTER_CONTROL_BUTTON = Button.kBumperRight.value;
        public static final int SHOOT_BUTTON = Button.kBumperLeft.value; 
        public static final int SHOOTER_TILT_BUTTON = Button.kBumperLeft.value; 

        public static final int LIMELIGHT_AIM_BUTTON = Button.kY.value;
    }

    // The driver's controller
    public XboxController driverController = new XboxController(DRIVER_CONTROLLER_PORT);
    public XboxController operatorController = new XboxController(OPERATOR_CONTROLLER_PORT);

    
    public JoystickButton invertControlsButton = new JoystickButton(driverController, ButtonMappings.INVERT_CONTROLS_BUTTON);
    public JoystickButton limelightAimButton = new JoystickButton(driverController, ButtonMappings.LIMELIGHT_AIM_BUTTON);
    public JoystickButton halfSpeedButton = new JoystickButton(driverController, ButtonMappings.HALF_SPEED_BUTTON);

    //public JoystickButton intakeButton = new JoystickButton(operatorController, ButtonMappings.INTAKE_BUTTON);
    public JoystickButton shooterControlButton = new JoystickButton(operatorController, ButtonMappings.SHOOTER_CONTROL_BUTTON);
    public JoystickButton shootButton = new JoystickButton(operatorController, ButtonMappings.SHOOT_BUTTON);
    public JoystickButton shooterTiltButton = new JoystickButton(operatorController, ButtonMappings.SHOOTER_TILT_BUTTON);
    
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
        halfSpeedButton.whenHeld(new HalveDriveSpeed(container.drivetrain));
        invertControlsButton.whenPressed(new InvertControlsCommand(container.drivetrain));
        limelightAimButton.whenPressed(new LimelightAimCommand(container.drivetrain, container.limelight));

        //intakeButton.whenHeld(new IntakeCommand(container.intake,container.conveyor));
        shooterControlButton.whenPressed(new ShooterStartCommand(container.shooter, container.limelight));
        shooterControlButton.whenReleased(new ShooterStopCommand(container.shooter, container.conveyor));
        shootButton.whenHeld(new ShooterStopCommand(container.shooter, container.conveyor));
        shooterTiltButton.whenPressed(new ShooterTiltCommand());


        
    }

}
