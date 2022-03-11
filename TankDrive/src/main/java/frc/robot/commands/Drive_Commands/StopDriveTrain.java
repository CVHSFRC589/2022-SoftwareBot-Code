// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drive_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class StopDriveTrain extends CommandBase {
  private DriveTrainSubsystem m_drive; 
  /** Creates a new StopDriveTrain. */
  public StopDriveTrain(DriveTrainSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("\n+++++++++++++++STOP+++++++++++++++++\n");
    m_drive.reset();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
