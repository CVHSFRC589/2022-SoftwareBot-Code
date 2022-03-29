// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.PneumaticsModuleType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.PIDConstants;

public class FeederSubsystem extends SubsystemBase {
  private final CANSparkMax m_feederMotor = new CANSparkMax(Constants.FEEDER_MOTOR_PORT, MotorType.kBrushless);
  private final SparkMaxPIDController m_feederPIDController = m_feederMotor.getPIDController();
  private final RelativeEncoder m_feederEncoder = m_feederMotor.getEncoder();
  private double m_feederRPM = Constants.FEEDER_MOTOR_RPM;
  // private NetworkTable m_table;
  // private NetworkTableEntry m_rpm;
  private boolean m_isFeeding = false;
  

  
  /** Creates a new FeederSubsystem. */
  public FeederSubsystem() {
    m_feederEncoder.setPosition(0);
    m_feederMotor.setInverted(false);
    m_feederMotor.setIdleMode(IdleMode.kBrake);
    // m_trigger = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.FEEDER_TRIGGER_ON, Constants.FEEDER_TRIGGER_OFF);

    // set PID coefficients
    m_feederPIDController.setP(PIDConstants.FEEDER_P);
    m_feederPIDController.setI(PIDConstants.FEEDER_I);
    m_feederPIDController.setD(PIDConstants.FEEDER_D);
    m_feederPIDController.setIZone(PIDConstants.FEEDER_Iz);
    m_feederPIDController.setFF(PIDConstants.FEEDER_FF);
    m_feederPIDController.setOutputRange(PIDConstants.FEEDER_MIN_OUTPUT, PIDConstants.FEEDER_MAX_OUTPUT);
  }
  

  public void runFeederMotor(){
    if(m_isFeeding){
      m_feederPIDController.setReference(m_feederRPM, CANSparkMax.ControlType.kVelocity);
    }else{
      m_feederMotor.set(0);
    }
  }

  public void runFeederAtRPM(double RPM){
    m_feederPIDController.setReference(RPM, CANSparkMax.ControlType.kVelocity);
  }

  public void setFeederRPM(double newRPM){
    m_feederRPM = newRPM;
  }

  public boolean isFeeding(){
    return m_isFeeding;
  }
  public void toggleFeeding(){
    m_isFeeding = !m_isFeeding;
  }
  public void setFeeding(boolean feed){
    m_isFeeding = feed;
  }

  public void stopFeeding(){
    m_isFeeding = false;
    m_feederMotor.set(0);
  }

  public void feed(double speed){ 
    m_feederMotor.set(speed);
  }

  public void setDefaultRPM(double rpm){
    m_feederRPM = rpm;
  }
  
  public void setPID(double kP, double kI, double kD, double kIz, double kFF){
    m_feederPIDController.setP(kP);
    m_feederPIDController.setI(kI);
    m_feederPIDController.setD(kD);
    m_feederPIDController.setIZone(kIz);
    m_feederPIDController.setFF(kFF);
  }

  public double getFeederEncoderSpeed(){
    return m_feederEncoder.getVelocity()* Constants.SHOOTER_GEAR_RATIO;
    //change constant to actual gear ratio later
  }

  public void updateShuffleboard() {
    SmartDashboard.putNumber("FeederMotorRPM", m_feederEncoder.getVelocity());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateShuffleboard();
  }
}
