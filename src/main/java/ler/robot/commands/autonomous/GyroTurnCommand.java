package ler.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import ler.robot.Robot;
import ler.robot.Tools;
import ler.robot.subsystems.Drivetrain;
import ler.robot.subsystems.Gyro;

public class GyroTurnCommand extends CommandBase {
	private double target_angle;
	private boolean finished = false;
	private boolean absolute;
	private Drivetrain drivetrain;
	private Gyro gyro;
	final double HIGH_SPEED = 0.4;
	final double LOW_SPEED = 0.20;
	final double LOW_SPEED_LIMIT = 0.3;
	final double THRESHOLD_ANGLE = 40;
	final double TOLERANCE = 15;
	final double GYRO_MULTIPLIER = 5.475d / 360d;
	boolean clockwise = false;

	/**
	 * Turns to an angle in degrees. Positive is CCW. If absolute is true, it turns
	 * to the angle relative to the angle it was at when enabled.
	 * 
	 * @author Tim
	 */
	public GyroTurnCommand(Drivetrain drivetrain, Gyro gyro, double target_angle, boolean absolute) {
		this.drivetrain = drivetrain;
		this.gyro = gyro;
		addRequirements(this.drivetrain);
		addRequirements(this.gyro);

		this.absolute = absolute;
		this.target_angle = target_angle;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		if (!absolute) {
			gyro.zero();
			// target_angle -= target_angle * GYRO_MULTIPLIER; // gyro tends to increase in
			// error as it turns, this compensates (somewhat)
			clockwise = target_angle < 0; // gyro tends to increase in error as it turns, this compensates (somewhat)
		} else {
			target_angle = Tools.closestEquivalentAngle(gyro, target_angle);
//    		target_angle += (target_angle - Robot.gyro.getAbsoluteAngle()) * GYRO_MULTIPLIER; // gyro tends to increase in error as it turns, this compensates (somewhat)	
    		clockwise = gyro.getAbsoluteAngle() > target_angle;
    	}
    }

	// Called repeatedly when this Command is scheduled to run
	@Override
    public void execute() {
    	double l = 0;
    	double r = 0;
    	double calculated_speed = 0;
    	
    	double difference;

    	if (absolute) {
    		difference = target_angle - gyro.getAbsoluteAngle();
    	}
    	else {
    		difference = target_angle - gyro.getAngle();
    	}
    	//If the angle difference is above THRESHOLD_ANGLE, turn at HIGH_SPEED
    	if (!clockwise && difference > THRESHOLD_ANGLE) {
    		calculated_speed = HIGH_SPEED;
    	}
    	else if (clockwise && difference < -THRESHOLD_ANGLE) {
    		calculated_speed = -HIGH_SPEED;
    	}
    	//If the angle difference is below THRESHOLD_ANGLE, turn at LOW SPEED, 
    	//decreasing as it approaches the target
    	else if (Math.abs(difference) > TOLERANCE && Math.abs(difference) <= THRESHOLD_ANGLE) {
    		calculated_speed = (difference / THRESHOLD_ANGLE) * LOW_SPEED;
    		//If the speed is lower than the allowed limit, set it to the limit
    		if(Math.abs(calculated_speed) < LOW_SPEED_LIMIT) {
    			calculated_speed = LOW_SPEED_LIMIT * Math.signum(calculated_speed);
    		}
    	}
    	//If the angle difference is below the tolerance then the turn is finished and the command exits
    	else {
    		finished = true;
    	}
    	r = calculated_speed;
    	l = -calculated_speed;
    	drivetrain.tankDrive(l, r);
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
    	// if (isTimedOut()) {
    		// Robot.autonomous_command_group.cancel();
		// }
		//TODO: re-add finished condition
        return false;// finished || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drivetrain.tankDrive(0, 0);
    }
}
