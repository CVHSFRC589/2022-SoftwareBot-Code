// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;

public class ClimberSubsystem extends SubsystemBase {
  /* Creates a new climber. */
  private final DoubleSolenoid m_climberArms;
  private final Compressor m_compressor;
  private int m_reachTracker;
  private int m_pullTracker;

  public ClimberSubsystem() {
    m_climberArms = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.CLIMBER_ARMS_ON_PORT, Constants.CLIMBER_ARMS_OFF_PORT);
    m_compressor = new Compressor(PneumaticsModuleType.CTREPCM); //Also turns on the compressor
    m_compressor.enableDigital();
    m_reachTracker = 0;
    m_pullTracker = 0;

    // m_compressor.disable();  
  }
  
  public DoubleSolenoid.Value getArmsValue(){
    return m_climberArms.get();
  }
  
  public void reachArms()
  {
    m_reachTracker++;
    m_climberArms.set(DoubleSolenoid.Value.kForward);
  }

  public void pullArms()
  {
    m_pullTracker++;
    m_climberArms.set(DoubleSolenoid.Value.kReverse);
  }
  
  public void toggleClimberSolenoids(){
    if(getArmsValue().equals(DoubleSolenoid.Value.kForward))
      pullArms();
    else
      reachArms();
  }

  public int getReaches(){
    return m_reachTracker;
  }

  public int getPulls(){
    return m_pullTracker;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
