// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LimeLightAiming extends LimeLight{
  /** Creates a new LimelightSubsystem. */
  private static LimeLight m_Limelight = new LimeLight();

  public void updateLimelightValues()
  {
    if(m_Limelight.getIsTargetFound())
    {
      SmartDashboard.putNumber("Limelight X", m_Limelight.getdegRotationToTarget());
      SmartDashboard.putNumber("Limelight Y", m_Limelight.getdegVerticalToTarget());
      SmartDashboard.putNumber("Limelight Target Area", m_Limelight.getTargetArea());
      SmartDashboard.putNumber("Estimated Target Distance", estimateTargetDistance());
      SmartDashboard.putString("Limelight Target", "Target found");
    }
    else{
      SmartDashboard.putString("Limelight Target", "No Target found");
    }
   
  }

  public double estimateTargetDistance(){
    double finheight = Constants.HUB_HEIGHT - Constants.LIMELIGHT_HEIGHT;
    double finangle = Constants.LIMELIGHT_MOUNT_ANGLE + m_Limelight.getdegVerticalToTarget();
    finangle = Math.toRadians(finangle);
    double finratio = Math.tan(finangle); //Math.tan takes in radians
    SmartDashboard.putNumber("FinHeight", finheight);
    SmartDashboard.putNumber("Finratio", finratio);
    SmartDashboard.putNumber("Finangle", finangle);

    return (finheight)/finratio;
  }
}