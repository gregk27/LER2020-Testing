package ler.mocks;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

public class MockCTREController<T extends BaseMotorController> {
    
    T mc;
    Class<T> type;
    
    public ControlMode controlMode;
    public double setpoint;
    public double velocity;
    public double position;

    public double kP=0;
    public double kI=0;
    public double kD=0;
    public double kF=0;



    public MockCTREController(Class<T> mcClass) {
        mc = mock(mcClass);
        type = mcClass;
        //Update when control set
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            controlMode = (ControlMode) args[0];
            setpoint = (double) args[1];
            if(controlMode == ControlMode.Position){
                position = setpoint;
            } else if (controlMode == ControlMode.Velocity){
                velocity = setpoint;
            }
            return null; // void method in a block-style lambda, so return null
        }).when(mc).set(any(ControlMode.class), anyDouble()); 
      
        //Send position on request
        doAnswer(invocation -> {
            return position;
        }).when(mc).getSelectedSensorPosition();
        doAnswer(invocation -> {
            return position;
        }).when(mc).getSelectedSensorPosition(anyInt());

        //Send velocity on request
        doAnswer(invocation -> {
            return velocity;
        }).when(mc).getSelectedSensorVelocity();
        doAnswer(invocation -> {
            return velocity;
        }).when(mc).getSelectedSensorVelocity(anyInt());

        //Update when kP set
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            controlMode = (ControlMode) args[0];
            kP = (double) args[1];
            return null; // void method in a block-style lambda, so return null
        }).when(mc).config_kP(anyInt(), anyDouble()); 
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            controlMode = (ControlMode) args[0];
            kP = (double) args[1];
            return null; // void method in a block-style lambda, so return null
        }).when(mc).config_kP(anyInt(), anyDouble(), anyInt()); 
    
        //Update when kI set
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            controlMode = (ControlMode) args[0];
            kI = (double) args[1];
            return null; // void method in a block-style lambda, so return null
        }).when(mc).config_kP(anyInt(), anyDouble()); 
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            controlMode = (ControlMode) args[0];
            kI = (double) args[1];
            return null; // void method in a block-style lambda, so return null
        }).when(mc).config_kP(anyInt(), anyDouble(), anyInt()); 

        //Update when kD set
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            controlMode = (ControlMode) args[0];
            kD = (double) args[1];
            return null; // void method in a block-style lambda, so return null
        }).when(mc).config_kP(anyInt(), anyDouble()); 
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            controlMode = (ControlMode) args[0];
            kD = (double) args[1];
            return null; // void method in a block-style lambda, so return null
        }).when(mc).config_kP(anyInt(), anyDouble(), anyInt()); 
    
        //Update when kF set
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            controlMode = (ControlMode) args[0];
            kF = (double) args[1];
            return null; // void method in a block-style lambda, so return null
        }).when(mc).config_kP(anyInt(), anyDouble()); 
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            controlMode = (ControlMode) args[0];
            kF = (double) args[1];
            return null; // void method in a block-style lambda, so return null
        }).when(mc).config_kP(anyInt(), anyDouble(), anyInt()); 
    }

    public T getMock(){
        return mc;
    }

    public Class<T> controllerType(){
        return type;
    }

    public double[] getPIDF(){
        return new double[] {kP, kI, kD, kF};
    }
    public void setPIDF(double[] pidf){
        kP = pidf[0];
        kI = pidf[1];
        kD = pidf[2];
        kF = pidf[3];
    }

    public double[] getPID(){
        return new double[] {kP, kI, kD};
    }
    public void setPID(double[] pid){
        kP = pid[0];
        kI = pid[1];
        kD = pid[2];
    }
}