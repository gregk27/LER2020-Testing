package ler.robot.commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.robototes.helpers.MockButton;
import com.robototes.helpers.MockHardwareExtension;
import com.robototes.helpers.TestWithScheduler;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import ler.mocks.MockDoubleSolenoid;
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
    public void startIntake() throws InterruptedException {
        // Arrange
        TalonSRX intakeMotor = mock(TalonSRX.class);
        MockDoubleSolenoid intakePiston = new MockDoubleSolenoid();
        Intake intake = new Intake(intakeMotor, intakePiston.getMock());
        Conveyor conveyor = mock(Conveyor.class);

        IntakeCommand command = new IntakeCommand(intake, conveyor);

        MockButton button = new MockButton();
        button.whenPressed(command);
        
        // Act
        button.push();
        CommandScheduler.getInstance().run();
        button.release();

        // Assert
        assertTrue("Intake not extended", intake.isExtended());

        TestWithScheduler.schedulerClear();
    }
    
    @After
    public void after() {
        TestWithScheduler.schedulerDestroy();
        MockHardwareExtension.afterAll();
    }
}