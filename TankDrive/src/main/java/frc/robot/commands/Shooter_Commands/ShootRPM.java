// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Commands;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.*;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class ShootRPM extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_shootSubsystem;
  private double m_RPM;
  private double motorInput = 0;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShootRPM(double RPM, ShooterSubsystem subsystem) {
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
    if(m_shootSubsystem.getShooterEncoderSpeed() < m_RPM)
    {
      motorInput += Math.pow(m_RPM-m_shootSubsystem.getShooterEncoderSpeed(), 2)/100000000;
    }
    else if(m_shootSubsystem.getShooterEncoderSpeed() > m_RPM)
    {
      motorInput -= Math.pow(m_RPM-m_shootSubsystem.getShooterEncoderSpeed(), 2)/100000000;
    }     

    m_shootSubsystem.shootInput(motorInput);
    // parabola opens upward
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
