// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake_Commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ToggleIntakeMotorRunning extends InstantCommand {
  private IntakeSubsystem m_intake;
  public ToggleIntakeMotorRunning(IntakeSubsystem intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intake = intake;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(m_intake.getIntakeMotorRunning())
      m_intake.stopMotor();
    else
      m_intake.startMotor(Constants.INTAKE_MOTOR_SPEED);
  }
}
