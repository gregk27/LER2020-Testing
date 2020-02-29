/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ler.robot.RobotMap;

public class Gyro extends SubsystemBase {

  /**
   * Creates a new Gyro.
   */


  public Gyro() {

  }
  
  public void calibrate(){
    RobotMap.gyro.reset();
  }

  public void resetAngle(){

  }
  public double[] getStraightOutput(double l, double r) {
    final double ANGLE_TOLERANCE = 1;
    double k_p = 0.015;
    double l_out = l;
    double r_out = r;
    double current_angle = getAngle();
    
    
    if (Math.abs(current_angle) > ANGLE_TOLERANCE) {	// adjust values to compensate for turning drift	
      double modifier = current_angle * k_p;	// make amount of correction proportional to angle
      l_out += modifier;
      r_out -= modifier;
    }
    
    l_out = l_out > 1 ? 1 : l_out;	// ensure values are within -1 to 1 range
    l_out = l_out < -1 ? -1 : l_out;
    r_out = r_out > 1 ? 1 : r_out;
    r_out = r_out < -1 ? -1 : r_out;
    
    return new double[] {l_out, r_out};
  }

  public double getAngle(){
    return RobotMap.gyro.getAngle();
  }

}
