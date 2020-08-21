package ler.robot.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import ca.gregk.frcmocks.ctre.MockCTREController;
import ca.gregk.frcmocks.ctre.MockTalonSRX;
import ca.gregk.frcmocks.scheduler.MockButton;
import ca.gregk.frcmocks.scheduler.MockHardwareExtension;
import ca.gregk.frcmocks.scheduler.TestWithScheduler;
import ca.gregk.frcmocks.wpilib.MockDoubleSolenoid;
import ler.robot.subsystems.Conveyor;
import ler.robot.subsystems.Intake;

/**
 * JUnit tests to ensure functionality of IntakeCommand and connected subsystems.
 */
public class IntakeCommandTest {
    CommandScheduler scheduler = null;

    /**
     * Setup the scheduler for simulated testing.
     */
    @Before
    public void before() {
        TestWithScheduler.schedulerStart();
        TestWithScheduler.schedulerClear();
        MockHardwareExtension.beforeAll();
    }

    /**
     * Ensure that the roller spins and intake deploys on start.
     */
    @Test
    public void startIntake() {
        // Arrange
        MockTalonSRX intakeMotor = new MockTalonSRX();
        MockDoubleSolenoid intakePiston = new MockDoubleSolenoid();
        Intake intake = new Intake(intakeMotor.getMock(), intakePiston.getMock());
        Conveyor conveyor = mock(Conveyor.class);

        IntakeCommand command = new IntakeCommand(intake, conveyor);

        MockButton button = new MockButton();
        button.whenPressed(command);
        
        // Act
        button.push();
        CommandScheduler.getInstance().run();
        button.release();

        // Assert
        assertEquals(intakeMotor.controlMode, ControlMode.PercentOutput);
        assertEquals(Math.abs(intakeMotor.setpoint), Math.abs(Intake.ROLLER_SPEED), 0.01);
        assertTrue("Intake not extended", intake.isExtended());

        TestWithScheduler.schedulerClear();
    }

    /**
     * Ensure that the roller stops and intake retracts on stop.
     */
    @Test
    public void stopIntake() {
        // Arrange
        MockTalonSRX intakeMotor = new MockTalonSRX();
        MockDoubleSolenoid intakePiston = new MockDoubleSolenoid();
        Intake intake = new Intake(intakeMotor.getMock(), intakePiston.getMock());
        Conveyor conveyor = mock(Conveyor.class);

        IntakeCommand command = new IntakeCommand(intake, conveyor);

        MockButton button = new MockButton();
        button.whileHeld(command);
        
        // Act
        // Deploy
        button.push();
        CommandScheduler.getInstance().run();
        //Retract
        button.release();
        CommandScheduler.getInstance().run();

        // Assert
        assertEquals(Math.abs(intakeMotor.setpoint), 0, 0.01);
        assertFalse("Intake not retracted", intake.isExtended());

        TestWithScheduler.schedulerClear();
    }
    
    /**
     * Clean up scheduler system.
     */
    @After
    public void after() {
        TestWithScheduler.schedulerDestroy();
        MockHardwareExtension.afterAll();
    }
}