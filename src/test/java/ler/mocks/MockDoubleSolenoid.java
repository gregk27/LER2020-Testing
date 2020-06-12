package ler.mocks;

import static org.mockito.Mockito.doAnswer;

import org.mockito.Mockito;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class MockDoubleSolenoid {
    DoubleSolenoid ds;
    Value state = Value.kOff;

    public MockDoubleSolenoid(){
       ds = Mockito.mock(DoubleSolenoid.class);
       
       //Update when position set
       doAnswer(invocation -> {
        System.out.println("Updating state");
        Object[] args = invocation.getArguments();
        state = (Value) args[0];
        return null; // void method in a block-style lambda, so return null
      }).when(ds).set(Mockito.any(Value.class)); 
      
      //Return state upon request (in lambda, as simpler function was slower than above lambda)
      doAnswer(invocation->{
        System.out.println(state);
        return state;
      }).when(ds).get();
    }

    public DoubleSolenoid getMock(){
        return ds;
    }

    public Value getState(){
        return state;
    }
}