// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.Drive_Commands;

import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveToDistance extends CommandBase {
  private final DriveTrainSubsystem m_drivetrain;
  private double m_distanceInches = 0;
  private final double m_speed;

  /**
   * @param subsystem The subsystem used by this command.
   */
  public DriveToDistance(double distanceinches, double speed, DriveTrainSubsystem drivetrain) {
    m_drivetrain = drivetrain;
    m_speed = speed;
    m_distanceInches = distanceinches;
    // Use addRequirements() here to declare subsystem dependencies.
    // addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // m_drivetrain.driveToDistance(m_distanceInches);
    m_drivetrain.tankDrive(-m_speed, -m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_drivetrain.getAverageEncoderInches()) >= m_distanceInches;
  }
}