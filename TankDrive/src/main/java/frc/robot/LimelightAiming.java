// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LimelightAiming{
  /** Creates a new LimelightSubsystem. */
  private static LimeLight m_Limelight = new LimeLight();

  public void updateLimelightValues()
  {
    if(m_Limelight.getIsTargetFound())
    {
      SmartDashboard.putNumber("Limelight X", m_Limelight.getdegRotationToTarget());
      SmartDashboard.putNumber("Limelight Y", m_Limelight.getdegVerticalToTarget());
      SmartDashboard.putNumber("Limelight Target Area", m_Limelight.getTargetArea());
      SmartDashboard.putString("Limelight Target", "Target found");
    }
    else{
      SmartDashboard.putString("Limelight Target", "No Target found");
    }
   
  }
}
