// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drive_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.LimeLightAiming;
import edu.wpi.first.networktables.*;
import frc.robot.Constants;

public class DriveToGivenTargetDistance extends CommandBase {
  private DriveTrainSubsystem m_drive;
  private double m_distance;
  private LimeLightAiming m_limeLight;
  private NetworkTable m_table;
  private NetworkTableEntry m_pattern;
  private NetworkTableEntry m_patternOver;
  private double m_distanceAway;
  private boolean m_isStarted;
  private double m_RPM;

  /** Creates a new DriveToGivenTargetDistance. */
  public DriveToGivenTargetDistance(double distance, LimeLightAiming limeLight, DriveTrainSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = subsystem;
    m_distance = distance;
    m_limeLight = limeLight;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_pattern = m_table.getEntry(Constants.VISUAL_FEEDBACK_TABLE_ENTRY_NAME);
    m_patternOver = m_table.getEntry(Constants.PATTERN_FINISHED_ENTRY_NAME);
    m_isStarted = false;
    m_RPM = 1250;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_limeLight.getIsTargetFound()) {
      m_distanceAway = m_limeLight.estimateTargetDistance() - m_distance;
    } else {
      m_distanceAway = 0;
    }
    m_drive.reset();
    m_isStarted = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("Drive To Given Target Distance Is Started: " +
    // m_isStarted);
    if (!m_isStarted) {
      // m_isStarted = true;
      if (Math.abs(m_distanceAway) - Math.abs(m_drive.getAverageEncoderInches()) > 12) {
        m_RPM = 2000;
      } else {
        m_RPM = 750;
      }
      if (m_distanceAway > 0) {
        // m_drive.setMotors(-0.2, -0.2);
        m_drive.setDriveMotorsRPM(-m_RPM, -m_RPM);
      } else {
        // m_drive.setMotors(0.2, 0.2);
        m_drive.setDriveMotorsRPM(m_RPM, m_RPM);
      }
      System.out.println("RPM: " + m_RPM);
      m_patternOver.setString("nope");
      m_pattern.setString("hot pink");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_patternOver.setString("done");
    m_drive.reset();
    System.out.println("Given Target Distance Interrupted: " + interrupted);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return false;
    return Math.abs(m_distanceAway) - Math.abs(m_drive.getAverageEncoderInches()) < 3;
  }
}
