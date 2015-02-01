
package org.usfirst.frc.team4533.robot;


import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

public class Robot extends SampleRobot {
	private Victor leftMotor;
	private Victor rightMotor;
	private Relay elevatorMotor;
	private Relay launcherValve;
	private RobotDrive drive;
	private Joystick joystick;

	public Robot() {
		this.leftMotor = new Victor(0);
		this.rightMotor = new Victor(1);
		
		this.drive = new RobotDrive(leftMotor, rightMotor);
		
		this.joystick = new Joystick(0);
		
		this.launcherValve = new Relay(0);
		this.launcherValve.setDirection(Direction.kForward);
		
		this.elevatorMotor = new Relay(1);
		this.elevatorMotor.setDirection(Direction.kBoth);
	}

	public void autonomous() {
	}

	public void operatorControl() {
		drive.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			double leftValue = this.joystick.getRawAxis(1) * -1;
			double rightValue = this.joystick.getRawAxis(3)* -1;
			this.drive.tankDrive(leftValue, rightValue);
			
			if (this.joystick.getRawButton(8)) {
				this.launcherValve.set(Value.kOn);
			} else {
				this.launcherValve.set(Value.kOff);
			}
			
			if (this.joystick.getRawButton(6)) {
				this.elevatorMotor.set(Value.kForward);
			} else if (this.joystick.getRawButton(5)) {
				this.elevatorMotor.set(Value.kReverse);
			} else {
				this.elevatorMotor.set(Value.kOff);
			}
		}
	}

	/**
	 * Runs during test mode
	 */
	public void test() {
	}
}
