// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;
 
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
// import java.util.function.DoubleSupplier;
 
public class goFreeze extends CommandBase {
    private final DriveTrainSubsystem m_drivetrain;
 
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public goFreeze(DriveTrainSubsystem subsystem) {
    m_drivetrain = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
 
  // Called when the command is initially scheduled.
  @Override
 
  public void initialize() {}
 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    m_drivetrain.goFreeze();
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