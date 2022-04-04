// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class APSetDriveMotorsRPM extends CommandBase {
  double m_leftSpeed;
  double m_rightSpeed;
  DriveTrainSubsystem m_drive;
  /** Creates a new APSetDriveMotorsRPM. */
  public APSetDriveMotorsRPM(double leftSpeed, double rightSpeed, DriveTrainSubsystem drive) {
    m_leftSpeed = leftSpeed;
    m_rightSpeed = rightSpeed;
    m_drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
