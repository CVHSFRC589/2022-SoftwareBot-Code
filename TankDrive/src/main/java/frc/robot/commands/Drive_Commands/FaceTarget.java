// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drive_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.LimeLight;
import frc.robot.subsystems.DriveTrainSubsystem;

public class FaceTarget extends CommandBase {
  /** Creates a new FaceTarget. */
  private LimeLight m_limeLight;
  private DriveTrainSubsystem m_drivetrain;
  // private double m_targetFoundSpeed;
  private double m_initialEncoderValue;
  // private double m_noTargetSpeed;
  // private boolean m_targetFound;
  private double m_targetFoundRPM;
  private double m_noTargetFoundRPM;

  public FaceTarget(LimeLight limeLight, DriveTrainSubsystem subsystem) {

    m_limeLight = limeLight;
    m_drivetrain = subsystem;
    // m_targetFoundSpeed = 0.15;
    // m_noTargetSpeed = 0.25;
    m_targetFoundRPM = 750; // 125
    m_noTargetFoundRPM = 1000; // 450
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_initialEncoderValue = m_drivetrain.getAverageEncoderInches();
    // m_targetFound = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_limeLight.getIsTargetFound()) {
      double direction = Math.abs(m_limeLight.getdegRotationToTarget()) / m_limeLight.getdegRotationToTarget();
      // m_drivetrain.setMotors(-direction*m_targetFoundSpeed,
      // direction*m_targetFoundSpeed);
      m_drivetrain.setDriveMotorsRPM(-direction * m_targetFoundRPM, direction * m_targetFoundRPM);
      // }
    } else {
      // m_drivetrain.setMotors(-m_noTargetSpeed, m_noTargetSpeed);
      m_drivetrain.setDriveMotorsRPM(-m_noTargetFoundRPM, m_noTargetFoundRPM);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setMotors(0, 0);
    if (!interrupted) {
      System.out.println("Target Found");
    } else {
      System.out.println("Face Target Interrupted");
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_limeLight.getIsTargetFound() && (Math.abs(m_limeLight.getdegRotationToTarget()) <= .5)) {
      return true;
    } else if (m_drivetrain.getAverageEncoderInches() >= m_initialEncoderValue + 2 * Constants.ROBOT_TURN_CIRCUM) {
      return true;
      // Giving up so no infinite spin
    }
    return false;
  }
}
