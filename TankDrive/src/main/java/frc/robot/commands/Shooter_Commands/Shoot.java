// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Commands;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.*;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class Shoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_shootSubsystem;
  private DoubleSupplier m_lever;
  private NetworkTable m_table;
  private NetworkTableEntry m_pattern;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Shoot(DoubleSupplier lever, ShooterSubsystem subsystem) {
    m_lever = lever;
    m_shootSubsystem = subsystem;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.VISUAL_FEEDBACK_TABLE_NAME);
    m_pattern = m_table.getEntry(Constants.VISUAL_FEEDBACK_TABLE_ENTRY_NAME);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //m_shootSubsystem.setShooterSpeed(m_speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shootSubsystem.shoot(m_lever.getAsDouble());
    m_pattern.setString("red");
    SmartDashboard.putNumber("Shooter Speed: ", m_shootSubsystem.getShooterEncoderSpeed());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_pattern.setString(Constants.DEFAULT_COLOR);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
