// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.Shooter_Motor_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class SMControl extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_shootSubsystem;
  // private double m_lever;
  private DoubleSupplier m_lever;
  private NetworkTable m_table;
  private NetworkTableEntry m_pattern;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SMControl(DoubleSupplier lever, ShooterSubsystem subsystem) {
    m_lever = lever;
    m_shootSubsystem = subsystem;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_pattern = m_table.getEntry(Constants.VISUAL_FEEDBACK_TABLE_ENTRY_NAME);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // SmartDashboard.putBoolean("ShooterMotorRunning", true);
    // if(!m_shootSubsystem.isShooting()){
    //   m_shootSubsystem.toggleShooting();
    // }
    m_shootSubsystem.setShooterRPM(Constants.STARTING_SHOOTER_RPM);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shootSubsystem.runShooterMotor(m_lever.getAsDouble());
    // double shootMotorSpeed = m_shootSubsystem.getShooterEncoderSpeed();
    if(m_shootSubsystem.getShooterEncoderSpeed() > Constants.SHOOTER_STOPPING_SPEED){
      if(Math.abs((m_shootSubsystem.getShooterRPM() + m_lever.getAsDouble() * Constants.SHOOTING_LEVER_RPM_MULTIPLIER) - m_shootSubsystem.getShooterEncoderSpeed()) < Constants.SHOOTER_RPM_TOLERANCE){ // if within 25 rpm of target speed, set LEDs to green
        m_pattern.setString("green");
      }
      else{
        m_pattern.setString("yellow");
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // SmartDashboard.putBoolean("ShooterMotorRunning", false);
    // m_shootSubsystem.shootRPM(0, 0);
    if(!interrupted){
      SmartDashboard.putBoolean("Shooter Interrrupted", interrupted);
      System.out.println("---------------------- interrupted: "+interrupted);
      m_shootSubsystem.stopShooter();
    }
    else{
      System.out.println("++++++++++++++++++++++++ interrupted: "+interrupted);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
