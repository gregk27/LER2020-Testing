package ler.robot;

import ler.robot.subsystems.Gyro;

public class Tools {
    final static double DEADZONE = 0.15;

    //vvv Halil Drive vvv
    public static double getAdaptedSpeed(double speed) {
        // Using a logisitic  function *** FUNCTION HAS BEEN FIXED ***
        //Alot harder to control in mid range but much nicer in low and high range.
        //double out = ((1.01339285092)/(1+Math.pow(Math.E,(-10*(speed-0.5)))))-0.5067;
        //2019 function: nicer in mid range worse control in low and high.
        
        if (Math.abs(speed)<DEADZONE){
            speed = 0;
        }
  
        double out = (0.5 * (Math.sin(Math.PI * speed - Math.PI / 2)) + 0.5);
        return out * (speed > 0 ? 1 : -1);
    }

    public static double fitToRange(double value, double min, double max) {
        value = value < min ? min : value;
        value = value > max ? max : value;
        return value;
    }

    public static double setAbsoluteMinimum(double value, double min) {
		value = (value < min && value > 0) ? min : value;
		value = (value > -min && value < 0) ? -min : value;
		return value;
    }
    
    // helper for auto gyro
    public static double closestEquivalentAngle(Gyro gyro, double target_angle) {
		double t = target_angle;
		double c = gyro.getAbsoluteAngle();
		boolean b = true;
		while (b) {
			if (Math.abs(c - t) > Math.abs(c - (t + 360))) {
	    		t += 360;
	    	}
			else if (Math.abs(c - t) > Math.abs(c - (t - 360))) {
				t -= 360;
			}
			else {
				b = false;
			}
		}
		return t;
	}
}