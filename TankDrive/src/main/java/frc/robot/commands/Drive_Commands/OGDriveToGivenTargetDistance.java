// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drive_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.LimeLightAiming;
import edu.wpi.first.networktables.*;
import frc.robot.Constants;

public class OGDriveToGivenTargetDistance extends CommandBase {
  private DriveTrainSubsystem m_drive; 
  private double m_distance;
  private LimeLightAiming m_limeLight;
  private NetworkTable m_table;
  private NetworkTableEntry m_pattern;
  private NetworkTableEntry m_patternOver;
  /** Creates a new DriveToGivenTargetDistance. */
  public OGDriveToGivenTargetDistance(double distance, LimeLightAiming limeLight, DriveTrainSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = subsystem;
    m_distance = distance;
    m_limeLight = limeLight;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_pattern = m_table.getEntry(Constants.VISUAL_FEEDBACK_TABLE_ENTRY_NAME);
    m_patternOver = m_table.getEntry(Constants.PATTERN_FINISHED_ENTRY_NAME);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_distance > m_limeLight.estimateTargetDistance())
    {
      m_drive.setMotors(0.25, 0.25); //Swap negatives here and line 34, Limelight was on back of softwarebot
    }
    else
  {
      m_drive.setMotors(-0.25,-0.25);
    }
    m_patternOver.setString("nope");
    m_pattern.setString("hot pink");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_patternOver.setString("done");
    m_drive.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return false;
    return(Math.abs(m_distance-m_limeLight.estimateTargetDistance()) <= 3) || !m_limeLight.getIsTargetFound();

  }
}
