// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
 
import frc.robot.subsystems.DriveTrainSubsystem;
//import edu.wpi.first.wpilibj2.command.CommandBase;
 

public class turnLeftV2 extends CommandBase  {
  private final DriveTrainSubsystem m_drivetrain;
  private double m_degrees = 0;
  /** Creates a new turnLeftV2. */
  public turnLeftV2(DriveTrainSubsystem subsystem, double degrees) {
    m_drivetrain = subsystem;
    m_degrees = degrees;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    int junk = 0;
    junk++;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    m_drivetrain.turnLeft();
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double circum = 2*3.141592*9.875;
    //return m_degrees <= 3*circum;
    return false;   
  }
}
