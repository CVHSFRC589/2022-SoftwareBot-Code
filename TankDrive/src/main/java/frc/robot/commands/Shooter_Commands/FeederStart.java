// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Commands;

import frc.robot.subsystems.ShooterSubsystemPID;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FeederStart extends CommandBase {
  /** Creates a new Feed. */
  private final ShooterSubsystemPID m_shootSubsystem;

  public FeederStart(ShooterSubsystemPID subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shootSubsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shootSubsystem.feed(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shootSubsystem.feed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
