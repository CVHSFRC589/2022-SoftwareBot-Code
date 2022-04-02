// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber_Commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ToggleClimberArms extends InstantCommand {
  private ClimberSubsystem m_climb;
  private NetworkTable m_table;
  private NetworkTableEntry m_pattern;
  private NetworkTableEntry m_patternOver;
  
  public ToggleClimberArms(ClimberSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climb = subsystem;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_pattern = m_table.getEntry(Constants.VISUAL_FEEDBACK_TABLE_ENTRY_NAME);
    m_patternOver = m_table.getEntry(Constants.PATTERN_FINISHED_ENTRY_NAME);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(m_climb.getArmsValue().equals(DoubleSolenoid.Value.kForward)){
      m_patternOver.setString("unfinished");
      m_pattern.setString("rainbow party palette");
    }
    else {
      m_patternOver.setString("done");
    }
    m_climb.toggleClimberSolenoids();
    // m_climb.toggleRightSolenoid();
  }
}
