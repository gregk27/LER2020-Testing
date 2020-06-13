package ler.mocks.wpilib;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * Wrapper used to mock {@link DigitalOutput}s and track changes
 */
public class MockDigitalOutput {
    
    DigitalOutput mdo;
    public boolean state;

   /**
     * Create a wrapper for a mock {@link DigitalOutput}
     */
    public MockDigitalOutput(){
        mdo = mock(DigitalOutput.class);       
        //Update when position set
        doAnswer(invocation -> {
         Object[] args = invocation.getArguments();
         state = (boolean) args[0];
         return null; // void method in a block-style lambda, so return null
       }).when(mdo).set(anyBoolean()); 
       
       //Return state upon request (in lambda, as simpler function was faster than above lambda)
       doAnswer(invocation->{
         return state;
       }).when(mdo).get();
    }

    /**
     * Get the mocked {@link DigitalOutput} to be passed to the subsystem
     * 
     * @return The mock object
     */
    public DigitalOutput getMock(){
        return mdo;
    }
}