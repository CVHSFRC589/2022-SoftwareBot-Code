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
      // SmartDashboard.putNumber("Limelight X", Math.floor(m_Limelight.getdegRotationToTarget()*1000)/1000);
      // SmartDashboard.putNumber("Limelight Y", Math.floor(m_Limelight.getdegVerticalToTarget()*1000)/1000);
      // SmartDashboard.putNumber("Target Area", m_Limelight.getTargetArea());
      SmartDashboard.putNumber("Estimated Target Distance", Math.floor(estimateTargetDistance()*1000)/1000/12);
      SmartDashboard.putString("Limelight Target", "Target found");
      SmartDashboard.putNumber("Estimated RPM", Math.floor(inchesToRPM(estimateTargetDistance())*1000)/1000);
    }
    else{
      SmartDashboard.putString("Limelight Target", "No Target found");
    }
  //  SmartDashboard.putNumber("LimelightPipeline", m_Limelight.getPipeline());
  }

  public double estimateTargetDistance(){
    double finheight = Constants.HUB_HEIGHT - Constants.LIMELIGHT_HEIGHT;
    double finangle = Constants.LIMELIGHT_MOUNT_ANGLE + m_Limelight.getdegVerticalToTarget();
    finangle = Math.toRadians(finangle);
    double finratio = Math.tan(finangle); //Math.tan takes in radians
    // SmartDashboard.putNumber("FinHeight", finheight);
    // SmartDashboard.putNumber("Finratio", finratio);
    // SmartDashboard.putNumber("Finangle", finangle);
    if(getIsTargetFound()){
      return (finheight)/finratio;
    }
   return 0;
  }

  public double estimateRPM(){
    double rpm = inchesToRPM(estimateTargetDistance());
    if(rpm>5000 || !getIsTargetFound()){
      return Constants.STARTING_SHOOTER_RPM;
    }
    return rpm;
  }
  
  public double inchesToRPM(double inches){
    // return -2126 + 117*inches + (-1.14*Math.pow(inches, 2))+3.67e-3*Math.pow(inches, 3);
    return 309 + 46.3*inches - 0.473*Math.pow(inches,2) + 1.62e-03*Math.pow(inches,3);

  }
}
