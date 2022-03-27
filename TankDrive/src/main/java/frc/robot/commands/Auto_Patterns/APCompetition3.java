// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;
import frc.robot.commands.Drive_Commands.*;
import frc.robot.commands.Feeder_Motor_Commands.*;
import frc.robot.commands.Intake_Commands.SetIntakeMotor;
import frc.robot.commands.Intake_Commands.ToggleIntakeArm;
import frc.robot.commands.Misc_Commands.*;
import frc.robot.commands.Shooter_Motor_Commands.*;
import frc.robot.commands.Trigger_Piston_Commands.ExtendTriggerPiston;
import frc.robot.commands.Trigger_Piston_Commands.RetractTriggerPiston;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.TriggerPistonSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisualFeedbackSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class APCompetition3 extends SequentialCommandGroup {
  /** Creates a new AutoPatternOne. */
  public APCompetition3(DriveTrainSubsystem drive, ShooterSubsystem shooter, FeederSubsystem feeder, VisualFeedbackSubsystem vfs, TriggerPistonSubsystem piston, IntakeSubsystem intake) {
    addCommands(
      new ChangeLimePipeline(1,drive),
      new UpdateAllianceColor(vfs),
      new ShootDriveBack(2200, 80, shooter, drive, feeder),
      new RetractTriggerPiston(piston),
      // new FeederStart(feeder),
      new Pause(1),
      new ExtendTriggerPiston(piston),
      new TurnDegrees(0.3, 120, drive),
      new ToggleIntakeArm(intake),
      new SetIntakeMotor(intake, 1200),
      new DriveToDistance(20, 0.5, drive),
      new TurnDegrees(0.3, -120, drive),
      new RetractTriggerPiston(piston),
      new Pause(1),
      new SMStop(shooter),
      new FMStop(feeder),
      new SetIntakeMotor(intake, 0),
      new ExtendTriggerPiston(piston)
    );
  }
}
