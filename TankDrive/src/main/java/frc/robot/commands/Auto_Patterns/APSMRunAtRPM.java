// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;

import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class APSMRunAtRPM extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_shootSubsystem;
  private double m_RPM;
  private DoubleSupplier m_lever;
  private NetworkTable m_table;
  private NetworkTableEntry m_pattern;
  private NetworkTableEntry m_patternOver;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public APSMRunAtRPM(double RPM, ShooterSubsystem subsystem) {
    m_RPM = RPM;
    m_shootSubsystem = subsystem;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_pattern = m_table.getEntry(Constants.VISUAL_FEEDBACK_TABLE_ENTRY_NAME);
    m_patternOver = m_table.getEntry(Constants.PATTERN_FINISHED_ENTRY_NAME);
    m_lever = () -> 0;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
  public APSMRunAtRPM(double RPM, DoubleSupplier lever, ShooterSubsystem subsystem) {
    m_RPM = RPM;
    m_lever = lever;
    m_shootSubsystem = subsystem;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_pattern = m_table.getEntry(Constants.VISUAL_FEEDBACK_TABLE_ENTRY_NAME);
    m_patternOver = m_table.getEntry(Constants.PATTERN_FINISHED_ENTRY_NAME);
    // Use addRequirements() here to declare subsystem dependencies.
    // addRequirements(subsystem);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // SmartDashboard.putBoolean("ShooterMotorRunning", true);
    m_shootSubsystem.setShooting(true);
    m_shootSubsystem.setShooterRPM(m_RPM);
    m_patternOver.setString("nope");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shootSubsystem.runShooterMotor(m_lever.getAsDouble());
    // parabola opens upward
    if(m_shootSubsystem.getShooterEncoderSpeed() > Constants.SHOOTER_STOPPING_SPEED){
      m_patternOver.setString("nope");
      if(Math.abs((m_shootSubsystem.getShooterRPM() + m_lever.getAsDouble() * Constants.SHOOTING_LEVER_RPM_MULTIPLIER) - m_shootSubsystem.getShooterEncoderSpeed()) < Constants.SHOOTER_RPM_TOLERANCE){ // if within 25 rpm of target speed, set LEDs to green
        m_pattern.setString("green");
      }
      else{
        m_pattern.setString("yellow");
      }
    }
    else{
      m_patternOver.setString("done");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // SmartDashboard.putBoolean("ShooterMotorRunning", false);
    // m_shootSubsystem.shootRPM(0, 0);
    m_shootSubsystem.setShooting(false);
    m_patternOver.setString("done");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_RPM == 0; //button 3 doesnt stop when motor is running
  }
}
