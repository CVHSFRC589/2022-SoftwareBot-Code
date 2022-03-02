// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ChangeLimePipeline extends InstantCommand {
  private DriveTrainSubsystem m_drive;
  private int m_pipe;
  private NetworkTable m_table;
  private NetworkTableEntry m_pipeline;

  public ChangeLimePipeline(DriveTrainSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = subsystem;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_pipeline = m_table.getEntry(Constants.LIMELIGHT_PIPELINE_ENTRY_NAME);
    m_pipe = (int)m_pipeline.getDouble(0);
  }



  public ChangeLimePipeline(int pipe, DriveTrainSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = subsystem;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_pipeline = m_table.getEntry(Constants.LIMELIGHT_PIPELINE_ENTRY_NAME);
    m_pipe = pipe;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.setPipeline(m_pipe);
  }
}
