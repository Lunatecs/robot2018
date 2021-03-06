package org.usfirst.frc.team316.robot;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActivateIntakeCommand extends Command {
	
	private VictorSPX intake;
	private double power;
	private Date stopTime;
	private boolean isFinished = false;
	private int milliSeconds;
	
	
	public ActivateIntakeCommand (VictorSPX intake, double power, int milliSeconds) {
		this.intake = intake;
		this.power = power;
		this.milliSeconds = milliSeconds;
	}
	
	@Override
	protected void initialize() {
		Calendar calculateDate = GregorianCalendar.getInstance();
		calculateDate.add(GregorianCalendar.MILLISECOND, milliSeconds);
		stopTime = calculateDate.getTime();
	}
	
	@Override
	protected void execute() {
		intake.set(ControlMode.PercentOutput, power);
		if(new Date().after(stopTime)) {
			intake.set(ControlMode.PercentOutput, 0);
			this.isFinished = true;
		}
		
		SmartDashboard.putString("stop time", stopTime.toString() + " " + new Date().after(stopTime));
		SmartDashboard.putBoolean("Inake Done", this.isFinished);
	}
	
	@Override
	protected boolean isFinished() {
		return isFinished;
	}

}
