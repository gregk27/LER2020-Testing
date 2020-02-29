/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Copied directly from turret bot code
package ler.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ler.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Climber extends SubsystemBase{
    
    public void raiseElevator() {
        RobotMap.climber_position_solenoid.set(Value.kForward);
    }

    public void lowerElevator() {
        RobotMap.climber_position_solenoid.set(Value.kReverse);
    }

    public void driveWinch(double speed) {
        RobotMap.winchFalcon.set(ControlMode.PercentOutput, speed);
    }
}