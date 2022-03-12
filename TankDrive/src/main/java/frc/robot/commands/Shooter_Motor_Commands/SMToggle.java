// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Motor_Commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystemPID;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SMToggle extends InstantCommand {
  private NetworkTable m_table;
  private NetworkTableEntry m_patternOver;
  private ShooterSubsystemPID m_shooter;
  
  public SMToggle(ShooterSubsystemPID subsystem) {
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_patternOver = m_table.getEntry(Constants.PATTERN_FINISHED_ENTRY_NAME);

    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.toggleShooting();
    if(m_shooter.isShooting()){
      m_patternOver.setString("nope");
    }else{
      m_patternOver.setString("done");
    }
  }
}
