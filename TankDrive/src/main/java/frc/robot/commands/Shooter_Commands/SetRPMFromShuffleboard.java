// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystemPID;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SetRPMFromShuffleboard extends CommandBase {
  private ShooterSubsystemPID m_shooter;
  private NetworkTable m_table;
  private NetworkTableEntry m_rpm;

  public SetRPMFromShuffleboard(ShooterSubsystemPID subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem;

    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_rpm = m_table.getEntry(Constants.SHOOTER_RPM_ENTRY_NAME);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  @Override
  public void execute(){
    m_shooter.shootRPM(m_rpm.getDouble(0), 0);
  }


  @Override
  public void end(boolean interrupted) {
    //m_shooter.shootRPM(0, 0);
    m_shooter.stopShooter();
  }

  

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_rpm.getDouble(0) == 0;
  }
}
