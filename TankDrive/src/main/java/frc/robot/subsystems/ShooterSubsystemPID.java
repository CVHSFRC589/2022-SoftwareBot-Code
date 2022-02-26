// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystemPID extends SubsystemBase {
  private final CANSparkMax m_shooterMotor = new CANSparkMax(Constants.SHOOTER_MOTOR_PORT, MotorType.kBrushless);
  private final RelativeEncoder m_shooterEncoder = m_shooterMotor.getEncoder();
  private final CANSparkMax m_feederMotor = new CANSparkMax(Constants.FEEDER_MOTOR_PORT, MotorType.kBrushless);
  private final RelativeEncoder m_feederEncoder = m_feederMotor.getEncoder();
  private final SparkMaxPIDController m_shooterPIDController = m_shooterMotor.getPIDController();
  private double m_shooterRPM = Constants.STARTING_SHOOTER_RPM;
  private double m_feederSpeed = 0;
  private double m_leverRPM;
  private NetworkTable m_table;
  private NetworkTableEntry m_rpm;


  /** Creates a new ShooterSubsystemPID. */
  public ShooterSubsystemPID() {
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_rpm = m_table.getEntry(Constants.SHOOTER_RPM_ENTRY_NAME);
    m_rpm.setDouble(Constants.MAX_SHOOTER_RPM*.25);

    m_shooterEncoder.setPosition(0);
    m_shooterMotor.setInverted(true);
    m_feederEncoder.setPosition(0);
    m_feederMotor.setInverted(false);

    // set PID coefficients
    m_shooterPIDController.setP(Constants.kP);
    m_shooterPIDController.setI(Constants.kI);
    m_shooterPIDController.setD(Constants.kD);
    m_shooterPIDController.setIZone(Constants.kIz);
    m_shooterPIDController.setFF(Constants.kFF);
    m_shooterPIDController.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput);
  }

  public void shoot(double leverValue) { 
    m_leverRPM = leverValue*-.075*Constants.MAX_SHOOTER_RPM;
    m_shooterPIDController.setReference(m_shooterRPM + m_leverRPM, CANSparkMax.ControlType.kVelocity);
  }

  public void shootRPM(double RPM, double leverValue) { 
    m_leverRPM = leverValue*-.075*Constants.MAX_SHOOTER_RPM;
    m_shooterPIDController.setReference(RPM + m_leverRPM, CANSparkMax.ControlType.kVelocity);
  }

  public void feed(double speed){ 
    m_feederSpeed = speed;
    m_feederMotor.set(m_feederSpeed);
  }

  public void changeSpeed(double speed){
    if(m_shooterRPM+speed>=0 && m_shooterRPM+speed<=1){
      m_shooterRPM+=speed;
    }
  }
  public void setShooterSpeed(double newRPM){
    m_shooterRPM = newRPM;
  }

  public double getShooterSpeed(){
    return m_shooterRPM;
  }

  public double getShooterEncoderSpeed(){
    // return m_shooterEncoder.getVelocity()* Constants.SHOOTER_GEAR_RATIO;
    return m_shooterEncoder.getVelocity();
    //change constant to actual gear ratio later
  }

  public double getFeederEncoderSpeed(){
    return m_feederEncoder.getVelocity()* Constants.SHOOTER_GEAR_RATIO;
    //change constant to actual gear ratio later
  }
  
  public void updateShuffleboard()
  {
    SmartDashboard.putNumber("Shooter Speed: ", m_shooterRPM+m_leverRPM);
    SmartDashboard.putNumber("ShootMotorRPM", m_shooterEncoder.getVelocity());
    SmartDashboard.putNumber("FeedMotorRPM", m_feederEncoder.getVelocity());
    SmartDashboard.putNumber("LeverValue", m_leverRPM/.075/Constants.MAX_SHOOTER_RPM);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateShuffleboard();
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
