package ler.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.revrobotics.ControlType;

import org.junit.Test;

import ler.mocks.rev.MockCANSparkMax;

/**
 * JUnit tests to ensure functionality of the shooter subsystem.
 */
public class ShooterTest {
    
    /**
     * Ensure that the wheel speeds are set properly (top at full, bottom at a multiple of that).
     */
    @Test
    public void setSpeed(){
        // Arrange
        MockCANSparkMax tlSpark = new MockCANSparkMax();
        MockCANSparkMax trSpark = new MockCANSparkMax();
        MockCANSparkMax brSpark = new MockCANSparkMax();
        MockCANSparkMax blSpark = new MockCANSparkMax();

        Shooter shooter = new Shooter(tlSpark.getMock(), trSpark.getMock(), brSpark.getMock(), blSpark.getMock());

        // Act
        shooter.setSpecificShootersSpeed(Shooter.SHOOTER_TARGET_SPEED);

        // Assert
        assertTrue("TL Spark in wrong mode", tlSpark.pidMode);
        assertEquals(ControlType.kVelocity, tlSpark.controlType);
        assertEquals(Shooter.SHOOTER_TOP_TARGET_SPEED, tlSpark.setpoint, 0.01);

        assertTrue("TR Spark in wrong mode", trSpark.pidMode);
        assertEquals(ControlType.kVelocity, trSpark.controlType);
        assertEquals(-Shooter.SHOOTER_TOP_TARGET_SPEED, trSpark.setpoint, 0.01);

        assertTrue("BR Spark in wrong mode", brSpark.pidMode);
        assertEquals(ControlType.kVelocity, brSpark.controlType);
        assertEquals(-Shooter.SHOOTER_BOTTOM_TARGET_SPEED, brSpark.setpoint, 0.01);

        assertTrue("BL Spark in wrong mode", blSpark.pidMode);
        assertEquals(ControlType.kVelocity, blSpark.controlType);
        assertEquals(Shooter.SHOOTER_BOTTOM_TARGET_SPEED, blSpark.setpoint, 0.01);
    }

    /**
     * Ensure that when the shooter wheels are stopped, they coast instead of braking.
     */
    @Test
    public void stopShooter(){        
        // Arrange
        MockCANSparkMax tlSpark = new MockCANSparkMax();
        MockCANSparkMax trSpark = new MockCANSparkMax();
        MockCANSparkMax brSpark = new MockCANSparkMax();
        MockCANSparkMax blSpark = new MockCANSparkMax();

        Shooter shooter = new Shooter(tlSpark.getMock(), trSpark.getMock(), brSpark.getMock(), blSpark.getMock());

        // Act
        // Set speeds
        shooter.setSpecificShootersSpeed(Shooter.SHOOTER_TARGET_SPEED);
        // Kill speeds
        shooter.setSpecificShootersSpeed(0);

        // Assert
        assertFalse("TL Spark in wrong mode", tlSpark.pidMode);
        assertEquals(0, tlSpark.setpoint, 0);

        assertFalse("TR Spark in wrong mode", trSpark.pidMode);
        assertEquals(0, trSpark.setpoint, 0);

        assertFalse("BR Spark in wrong mode", brSpark.pidMode);
        assertEquals(0, brSpark.setpoint, 0);

        assertFalse("BL Spark in wrong mode", blSpark.pidMode);
        assertEquals(0, blSpark.setpoint, 0);


    }
}