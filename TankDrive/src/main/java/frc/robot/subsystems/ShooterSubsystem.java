// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private final CANSparkMax m_shooterMotor = new CANSparkMax(Constants.SHOOTER_MOTOR_PORT, MotorType.kBrushless);
  private final RelativeEncoder m_shooterEncoder = m_shooterMotor.getEncoder();
  private final CANSparkMax m_feederMotor = new CANSparkMax(Constants.FEEDER_MOTOR_PORT, MotorType.kBrushless);
  private final RelativeEncoder m_feederEncoder = m_shooterMotor.getEncoder();
  private double m_shooterSpeed = 0;
  private double m_feederSpeed = 0;
  private int m_count = 0;
  private double m_previousAmps = 0;
  private boolean m_average = false;
  private double m_maxAmps = 0;


  /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {

    m_shooterEncoder.setPosition(0);
    m_shooterMotor.setInverted(true);
    m_feederEncoder.setPosition(0);
    m_feederMotor.setInverted(true);
  }

  public void shoot(){
    m_shooterMotor.set(m_shooterSpeed);

    if(m_average){
      m_previousAmps += m_shooterMotor.getOutputCurrent();
      SmartDashboard.putNumber("Average Amps: ", m_previousAmps/m_count);
      m_count++;
      if(m_shooterMotor.getOutputCurrent() > m_maxAmps)
      {
        m_maxAmps = m_shooterMotor.getOutputCurrent();
        SmartDashboard.putNumber("Maximum Amps", m_maxAmps);
      }
    }
  }

  public void feed(double speed){ //do it for a certain number of times and then stop...  
    m_feederSpeed = speed;
    m_feederMotor.set(m_feederSpeed);
  }

  public void toggleAverage(){
    System.out.println("Before: " + m_average);
    m_average = !m_average;
    System.out.println("After: " + m_average);
  }
  
  public void resetAverage(){
   // System.out.println("Previous Average Amps: "+ m_previousAmps/m_count);
    SmartDashboard.putNumber("Previous Average Amps", m_previousAmps/m_count);
    m_previousAmps = 0;
    // m_maxAmps = 0;
    m_count = 1;
  }

  public void changeSpeed(double speed){
    System.out.println("old speed: " + m_shooterSpeed);
    if(m_shooterSpeed+speed>=0 && m_shooterSpeed+speed<=1){
      m_shooterSpeed+=speed;
      System.out.println("new speed: " + m_shooterSpeed);
    }
  }
  public void setShooterSpeed(double speed){
    m_shooterSpeed = speed;
  }

  public double getShooterSpeed(){
    return m_shooterSpeed;
  }


  public double getShooterEncoderSpeed(){
    return m_shooterEncoder.getVelocity()* Constants.SHOOTER_GEAR_RATIO;
    //change constant to actual gear ratio later
  }

  public double getFeederEncoderSpeed(){
    return m_feederEncoder.getVelocity()* Constants.SHOOTER_GEAR_RATIO;
    //change constant to actual gear ratio later
  }
  public void updateShuffleboard()
  {
    SmartDashboard.putNumber("Feeder Speed: ", m_feederSpeed);
    SmartDashboard.putNumber("Feeder Current: ", m_feederMotor.getOutputCurrent());
    SmartDashboard.putNumber("Shooter Speed: ", m_shooterSpeed);
    SmartDashboard.putNumber("Shooter Current: ", m_shooterMotor.getOutputCurrent());
    SmartDashboard.putNumber("ShooterMotorVelocityRPM", m_shooterEncoder.getVelocity());
    SmartDashboard.putNumber("FeederMotorVelocityRPM", m_feederEncoder.getVelocity());
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
