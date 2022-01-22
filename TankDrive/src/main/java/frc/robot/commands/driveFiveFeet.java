// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;
 
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
// import java.util.function.DoubleSupplier;
 
public class driveFiveFeet extends CommandBase {
    private final DriveTrainSubsystem m_drivetrain;
    private final double m_speed = 0.5; //change later to be defined in constructor
   
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public driveFiveFeet(DriveTrainSubsystem subsystem) {
    m_drivetrain = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
 
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.setLeft(0);
    m_drivetrain.setRight(0);
    //m_drivetrain.TankDrive();
  }
 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    m_drivetrain.driveFiveFeet();
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
 
