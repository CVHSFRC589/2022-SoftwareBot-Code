// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.LimeLightAiming;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystemPID;


public class ShootLimeRPM extends CommandBase {
  private LimeLightAiming m_limelight;
  private ShooterSubsystemPID m_shoot;
  private double m_shootSpeed;
  private DoubleSupplier m_lever;
  private NetworkTable m_table;
  private NetworkTableEntry m_pattern;
  private NetworkTableEntry m_patternOver;

  /** Creates a new ShootLimeRPM. */
  public ShootLimeRPM(DoubleSupplier lever, LimeLightAiming limelight, ShooterSubsystemPID subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_lever = lever;
    m_limelight = limelight;
    m_shoot = subsystem;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_pattern = m_table.getEntry(Constants.VISUAL_FEEDBACK_TABLE_ENTRY_NAME);
    m_patternOver = m_table.getEntry(Constants.PATTERN_FINISHED_ENTRY_NAME);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shootSpeed = m_limelight.estimateRPM();
    m_shoot.toggleShooting();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_patternOver.setString("nah");
    if(Math.abs((m_shootSpeed+m_lever.getAsDouble()*Constants.SHOOTING_LEVER_RPM_MULTIPLIER)-m_limelight.estimateRPM())>=25)
      m_shootSpeed=m_limelight.estimateRPM();

    if(Math.abs((m_shootSpeed+m_lever.getAsDouble()*Constants.SHOOTING_LEVER_RPM_MULTIPLIER)-m_shoot.getShooterEncoderSpeed())<=25){
      m_pattern.setString("green");
    }else{
      m_pattern.setString("yellow");
    }
    m_shoot.setShooterRPM(m_limelight.estimateRPM()+m_lever.getAsDouble()*Constants.SHOOTING_LEVER_RPM_MULTIPLIER);
    m_shoot.shoot(m_lever.getAsDouble());
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shoot.toggleShooting();
    m_shoot.setShooterRPM(Constants.STARTING_SHOOTER_RPM);
    m_patternOver.setString("done");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
