// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FeederSubsystem extends SubsystemBase {
  private final CANSparkMax m_feederMotor = new CANSparkMax(Constants.FEEDER_MOTOR_PORT, MotorType.kBrushless);
  private final RelativeEncoder m_feederEncoder = m_feederMotor.getEncoder();
  private double m_feederSpeed = Constants.FEEDER_MOTOR_SPEED;
  
  /** Creates a new FeederSubsystem. */
  public FeederSubsystem() {
    m_feederEncoder.setPosition(0);
    m_feederMotor.setInverted(false);
    m_feederMotor.setIdleMode(IdleMode.kBrake);
  }

  public void feed(double speed){ 
    m_feederSpeed = speed;
    m_feederMotor.set(m_feederSpeed);
  }

  public double getFeederEncoderSpeed(){
    return m_feederEncoder.getVelocity()* Constants.SHOOTER_GEAR_RATIO;
    //change constant to actual gear ratio later
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
