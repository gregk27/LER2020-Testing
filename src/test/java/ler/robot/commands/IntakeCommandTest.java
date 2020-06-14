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
import ler.mocks.ctre.MockCTREController;
import ler.mocks.scheduler.MockButton;
import ler.mocks.scheduler.MockHardwareExtension;
import ler.mocks.scheduler.TestWithScheduler;
import ler.mocks.wpilib.MockDoubleSolenoid;
import ler.robot.subsystems.Conveyor;
import ler.robot.subsystems.Intake;

public class IntakeCommandTest {
    CommandScheduler scheduler = null;

    @Before
    public void before() {
        TestWithScheduler.schedulerStart();
        TestWithScheduler.schedulerClear();
        MockHardwareExtension.beforeAll();
    }

    @Test
    public void startIntake() {
        // Arrange
        MockCTREController<TalonSRX> intakeMotor = new MockCTREController<TalonSRX>(TalonSRX.class);
        MockDoubleSolenoid intakePiston = new MockDoubleSolenoid();
        Intake intake = new Intake((TalonSRX) intakeMotor.getMock(), intakePiston.getMock());
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

    @Test
    public void stopIntake() {
        // Arrange
        MockCTREController<TalonSRX> intakeMotor = new MockCTREController<TalonSRX>(TalonSRX.class);
        MockDoubleSolenoid intakePiston = new MockDoubleSolenoid();
        Intake intake = new Intake((TalonSRX) intakeMotor.getMock(), intakePiston.getMock());
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
    
    @After
    public void after() {
        TestWithScheduler.schedulerDestroy();
        MockHardwareExtension.afterAll();
    }
}