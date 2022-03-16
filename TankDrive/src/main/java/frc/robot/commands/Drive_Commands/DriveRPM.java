// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drive_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveRPM extends CommandBase {
  private DriveTrainSubsystem m_drive;
  private double m_leftRPM;
  private double m_rightRPM;

  /** Creates a new DriveRPM. */
  public DriveRPM(double leftRPM, double rightRPM, DriveTrainSubsystem subsystem) {
    m_drive = subsystem;
    m_leftRPM = leftRPM;
    m_rightRPM = rightRPM;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.setDriveMotorsRPM(m_leftRPM, m_rightRPM);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
