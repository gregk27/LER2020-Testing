package ler.mocks.wpilib;

import static org.mockito.Mockito.doAnswer;

import org.mockito.Mockito;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * Wrapper used to mock {@link DoubleSolenoid}s and track changes
 */
public class MockDoubleSolenoid {

    DoubleSolenoid ds;
    /**
     * The solenoid's state, as set by {@link DoubleSolenoid#set(Value)}
     * <p> Can be:
     * <ul>
     * <li> {@link Value#kOff}
     * <li> {@link Value#kForward}
     * <li> {@link Value#kReverse}
     */
    public Value state = Value.kOff;

    /**
     * Create a wrapper for a mock {@link DoubleSolenoid}
     */
    public MockDoubleSolenoid(){
       ds = Mockito.mock(DoubleSolenoid.class);
       
       //Update when position set
       doAnswer(invocation -> {
        Object[] args = invocation.getArguments();
        state = (Value) args[0];
        return null; // void method in a block-style lambda, so return null
      }).when(ds).set(Mockito.any(Value.class)); 
      
      //Return state upon request (in lambda, as simpler function was faster than above lambda)
      doAnswer(invocation->{
        return state;
      }).when(ds).get();
    }

    /**
     * Get the mocked {@link DoubleSolenoid} to be passed to the subsystem
     * 
     * @return The mock object
     */
    public DoubleSolenoid getMock(){
        return ds;
    }

}