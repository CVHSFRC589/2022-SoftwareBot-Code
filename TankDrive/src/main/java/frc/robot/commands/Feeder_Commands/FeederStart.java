// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Feeder_Commands;

import frc.robot.Constants;
import frc.robot.subsystems.FeederSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FeederStart extends CommandBase {
  /** Creates a new Feed. */
  private final FeederSubsystem m_feeder;

  public FeederStart(FeederSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_feeder = subsystem;
    // addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_feeder.feed(Constants.FEEDER_MOTOR_SPEED); //Changed from 3/2/22 testing
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // m_feeder.feed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
