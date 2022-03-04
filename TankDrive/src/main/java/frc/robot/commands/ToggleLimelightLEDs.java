// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.LimeLightAiming;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ToggleLimelightLEDs extends InstantCommand {
  private LimeLightAiming m_lime;

  public ToggleLimelightLEDs(LimeLightAiming limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_lime = limelight;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(m_lime.getPipeline() == 0)
      m_lime.setPipeline(1);
      else
      m_lime.setPipeline(0);
  }
}
