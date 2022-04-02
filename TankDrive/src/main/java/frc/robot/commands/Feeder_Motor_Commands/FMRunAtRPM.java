// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Feeder_Motor_Commands;

import frc.robot.subsystems.FeederSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class FMRunAtRPM extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final FeederSubsystem m_feederSubsystem;
  private double m_RPM = 0;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public FMRunAtRPM(double speed, FeederSubsystem feederSubsystem) {
    
    m_RPM = speed;
    m_feederSubsystem = feederSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    // addRequirements(subsystem);
  }

  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_feederSubsystem.runFeederAtRPM(m_RPM);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

   // Returns true when the command should end.
   @Override
   public boolean isFinished() {
     return m_RPM == 0; //button 3 doesnt stop when motor is running
   }
}