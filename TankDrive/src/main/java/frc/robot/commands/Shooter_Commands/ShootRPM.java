// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Commands;

import frc.robot.subsystems.ShooterSubsystemPID;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShootRPM extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystemPID m_shootSubsystem;
  private double m_RPM;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShootRPM(double RPM, ShooterSubsystemPID subsystem) {
    m_RPM = RPM;
    m_shootSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if(m_shootSubsystem.getShooterEncoderSpeed() < (m_RPM + 5))
    // {
    //   motorInput += Math.pow(m_RPM-m_shootSubsystem.getShooterEncoderSpeed(), 2)/100000000 + 0.0001;
    // }
    // else if(m_shootSubsystem.getShooterEncoderSpeed() > (m_RPM - 5))
    // {
    //   motorInput -= Math.pow(m_RPM-m_shootSubsystem.getShooterEncoderSpeed(), 2)/100000000 + 0.0001;
    // }     
    m_shootSubsystem.setShooting(true);
    m_shootSubsystem.shootRPM(m_RPM);
    // parabola opens upward
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shootSubsystem.setShooting(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !m_shootSubsystem.getShooting() || m_RPM == 0; //button 3 doesnt stop when motor is running
  }
}
