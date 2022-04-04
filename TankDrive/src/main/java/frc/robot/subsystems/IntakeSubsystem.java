// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystemyay. */
  private final DoubleSolenoid m_arms;
  private final CANSparkMax m_intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_PORT, MotorType.kBrushless);
  private final RelativeEncoder m_intakeEncoder = m_intakeMotor.getEncoder();
  private int m_extensionTracker;
  private int m_retractionTracker;
  private boolean m_motorRunning;

  public IntakeSubsystem() {
    m_intakeMotor.setInverted(true);
    m_arms = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.INTAKE_ARMS_ON_PORT, Constants.INTAKE_ARMS_OFF_PORT);
    m_extensionTracker = 0;
    m_retractionTracker = 0;
  }
  // public boolean isExtended()
  // {
  //   if(DoubleSolenoid.Value.kForward == m_arms.get()){
  //     return true;
  //   }
  //   else{
  //     return false;
  //   }
  // }
  public void toggleIntake(){
    if (DoubleSolenoid.Value.kOff == m_arms.get()){
      extendIntake();
    }
   else{
      m_arms.toggle();
    }
  }

  public void extendIntake()
  {
    m_extensionTracker++;
    m_arms.set(DoubleSolenoid.Value.kForward);
    // SmartDashboard.putString("Intake Solenoids", "Extended");
  }

  public void retractIntake()
  {
    m_retractionTracker++;
    m_arms.set(DoubleSolenoid.Value.kReverse);
    stopMotor();
    // SmartDashboard.putString("Intake Solenoids", "Retracted");
  }

  public void startMotor(double speed)
  {
    m_intakeMotor.set(speed);
    if(speed>0)
      m_motorRunning = true;
    else
      m_motorRunning = false;
  }

  public void stopMotor()
  {
    m_intakeMotor.set(0);
    m_motorRunning = false;
  }

  public boolean getIntakeMotorRunning(){
    return m_motorRunning;
  }

  public double getIntakeMotorSpeed()
  {
    return m_intakeEncoder.getVelocity();
  }

  public int getExtensions(){
    return m_extensionTracker;
  }

  public int getRetractions(){
    return m_retractionTracker;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Intake Running", m_motorRunning);
  }
}