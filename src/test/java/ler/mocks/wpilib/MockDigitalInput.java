package ler.mocks.wpilib;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Wrapper used to mock {@link DigitalInput}s and track changes
 */
public class MockDigitalInput {
    
    DigitalInput mdi;
    public boolean state;

   /**
     * Create a wrapper for a mock {@link DigitalInput}
     * 
     * @param state Set the inital state
     */
    public MockDigitalInput(boolean state){
        mdi = mock(DigitalInput.class);
        when(mdi.get()).thenReturn(this.state);
    }
    
    /**
     * Create a wrapper for a mock {@link DigitalInput} with a <code>false</code> {@link #state}
     */
    public MockDigitalInput(){
        this(false);
    }

    /**
     * Get the mocked {@link DigitalInput} to be passed to the subsystem
     * 
     * @return The mock object
     */
    public DigitalInput getMock(){
        return mdi;
    }
}