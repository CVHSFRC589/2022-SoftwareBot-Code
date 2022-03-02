// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;


public class ClimberSubsystem extends SubsystemBase {
  /** Creates a new climber. */
  private final DoubleSolenoid m_leftArm;
  private final DoubleSolenoid m_rightArm;
  private final Compressor m_compressor;
  
  public ClimberSubsystem() {
    m_leftArm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.CLIMBER_LEFT_ARM_ON, Constants.CLIMBER_LEFT_ARM_OFF);
    m_rightArm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.CLIMBER_RIGHT_ARM_ON, Constants.CLIMBER_RIGHT_ARM_OFF);
    m_compressor = new Compressor(PneumaticsModuleType.CTREPCM); //Also turns on the compressor
    m_compressor.enableDigital();
    // m_compressor.disable();  
  }
  
  public DoubleSolenoid.Value getLeftValue(){
    return m_leftArm.get();
  }

  public DoubleSolenoid.Value getRightValue(){
    return m_rightArm.get();
  }
  
  public void reachLeft()
  {
    m_leftArm.set(DoubleSolenoid.Value.kForward);
    SmartDashboard.putBoolean("Left Climber", true);
    
  }

  public void reachRight()
  {
    m_rightArm.set(DoubleSolenoid.Value.kForward);
    SmartDashboard.putBoolean("Right Climber", true);
  }

  public void pullLeft()
  {
    m_leftArm.set(DoubleSolenoid.Value.kReverse);
    SmartDashboard.putBoolean("Left Climber", false);
    
  }

  public void pullRight()
  {
    m_rightArm.set(DoubleSolenoid.Value.kReverse);
    SmartDashboard.putBoolean("Right Climber", false);
  }
  
  public void toggleLeftSolenoid(){
    if(getLeftValue().equals(DoubleSolenoid.Value.kForward)){
      pullLeft();
    }
    else
    
    {
      reachLeft();
    }
  }

  public void toggleRightSolenoid(){
    if(getRightValue().equals(DoubleSolenoid.Value.kForward)){
      pullRight();
    }
    else
    
    {
      reachRight();
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
