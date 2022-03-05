// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.FeederSubsystem;

public class FeedOneBall extends CommandBase {
  private FeederSubsystem m_feeder;
  private Timer m_timer = new Timer();
  /** Creates a new FeedOneBall. */
  public FeedOneBall(FeederSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_feeder = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_feeder.feed(Constants.FEEDER_MOTOR_SPEED);
    m_timer.stop();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_feeder.feed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.hasElapsed(1);
  }
}
