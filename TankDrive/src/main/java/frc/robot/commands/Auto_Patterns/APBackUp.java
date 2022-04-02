// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;
import frc.robot.commands.Drive_Commands.*;
import frc.robot.commands.Misc_Commands.*;
import frc.robot.commands.Trigger_Piston_Commands.RetractTriggerPiston;

import javax.swing.GroupLayout.ParallelGroup;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TriggerPistonSubsystem;
import frc.robot.subsystems.VisualFeedbackSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
// public class APBackUp extends SequentialCommandGroup {
  public class APBackUp extends ParallelCommandGroup {

  /** Creates a new AutoPatternOne. */
  public APBackUp(DriveTrainSubsystem drive, VisualFeedbackSubsystem vfs, TriggerPistonSubsystem piston) {
    addCommands(
      // new ChangeLimePipeline(1,drive),
      // new UpdateAllianceColor(vfs),
      new DriveToDistance(80, .6, drive)
      // new RetractTriggerPiston(piston)
      
      
    );
  }
}
