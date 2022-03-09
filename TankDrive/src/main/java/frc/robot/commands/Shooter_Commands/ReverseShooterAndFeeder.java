// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystemPID;

public class ReverseShooterAndFeeder extends CommandBase {
  private ShooterSubsystemPID m_shooter;
  private FeederSubsystem m_feeder;
  /** Creates a new ReverseShooterFeeder. */
  public ReverseShooterAndFeeder(ShooterSubsystemPID sSubsystem, FeederSubsystem fSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = sSubsystem;
    m_feeder = fSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // m_shooter.setShooterMotor(-.3);
    m_feeder.feed(-.3);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // m_shooter.setShooterMotor(0);
    m_feeder.feed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
