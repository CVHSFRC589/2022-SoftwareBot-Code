// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.RelativeEncoder;
// import com.revrobotics.SparkMaxPIDController;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TriggerPistonSubsystem extends SubsystemBase {
  private DoubleSolenoid m_trigger; 
  private int m_extensionTracker;
  private int m_retractionTracker;
  
  /** Creates a new FeederSubsystem. */
  public TriggerPistonSubsystem() {
    m_trigger = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.FEEDER_PISTON_ON_PORT, Constants.FEEDER_PISTON_OFF_PORT);
    extendPiston();
    m_extensionTracker = 0;
    m_retractionTracker = 0;
  }

  public void extendPiston(){
    m_extensionTracker++;
    m_trigger.set(DoubleSolenoid.Value.kReverse);
  }

  public void retractPiston(){
    m_retractionTracker++;
    m_trigger.set(DoubleSolenoid.Value.kForward);
    // System.out.println("-------------------------");
  }

  public void toggleTriggerExtension(){
    if(m_trigger.get().equals(DoubleSolenoid.Value.kForward)){
      m_trigger.set(DoubleSolenoid.Value.kReverse);
    } else {
      m_trigger.set(DoubleSolenoid.Value.kForward);
    }
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
  }
}
