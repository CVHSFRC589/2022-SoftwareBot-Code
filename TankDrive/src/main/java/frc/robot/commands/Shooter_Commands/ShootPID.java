// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.Shooter_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ShooterSubsystemPID;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class ShootPID extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystemPID m_shootSubsystem;
  // private double m_lever;
  private DoubleSupplier m_lever;
  private NetworkTable m_table;
  private NetworkTableEntry m_pattern;
  private NetworkTableEntry m_patternOver;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShootPID(DoubleSupplier lever, ShooterSubsystemPID subsystem) {
    m_lever = lever;
    m_shootSubsystem = subsystem;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_pattern = m_table.getEntry(Constants.VISUAL_FEEDBACK_TABLE_ENTRY_NAME);
    m_patternOver = m_table.getEntry(Constants.PATTERN_FINISHED_ENTRY_NAME);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
  // public Shoot(double lever, ShooterSubsystem subsystem) {
  //   m_lever = lever;
  //   m_shootSubsystem = subsystem;
  //   m_table = NetworkTableInstance.getDefault().getTable(Constants.VISUAL_FEEDBACK_TABLE_NAME);
  //   m_pattern = m_table.getEntry(Constants.VISUAL_FEEDBACK_TABLE_ENTRY_NAME);
  //   // Use addRequirements() here to declare subsystem dependencies.
  //   addRequirements(subsystem);
  // }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("ShooterMotorRunning", true);
    m_patternOver.setString("nope");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shootSubsystem.shoot(m_lever.getAsDouble());
    if(m_shootSubsystem.getShooterEncoderSpeed() > 25){
      if(m_shootSubsystem.getShooterEncoderSpeed() < 667){
        m_pattern.setString("red");
      }
      else if(m_shootSubsystem.getShooterEncoderSpeed() < 667*2){
        m_pattern.setString("red orange");
      }
      else if(m_shootSubsystem.getShooterEncoderSpeed() < 667*3){
        m_pattern.setString("orange");
      }
      else if(m_shootSubsystem.getShooterEncoderSpeed() < 667*4){
        m_pattern.setString("gold");
      }
      else if(m_shootSubsystem.getShooterEncoderSpeed() < 667*5){
        m_pattern.setString("yellow");
      }
      else if(m_shootSubsystem.getShooterEncoderSpeed() < 667*6){
        m_pattern.setString("lawn green");
      }
      else{
        m_pattern.setString("lime");
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("ShooterMotorRunning", false);
    m_shootSubsystem.shootRPM(0, 0);
    m_patternOver.setString("done");  
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
