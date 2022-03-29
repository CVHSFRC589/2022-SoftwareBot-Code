// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;
import frc.robot.commands.Drive_Commands.*;
import frc.robot.commands.Intake_Commands.*;
import frc.robot.commands.Misc_Commands.*;
import frc.robot.commands.Shooter_And_Feeder_Commands.StopSMAndFM;
import frc.robot.commands.Trigger_Piston_Commands.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootTurnPickUpShoot extends SequentialCommandGroup {
  /** Creates a new AutoPatternOne. */
  public ShootTurnPickUpShoot(DriveTrainSubsystem drive, ShooterSubsystem shooter, FeederSubsystem feeder, VisualFeedbackSubsystem vfs, TriggerPistonSubsystem piston, IntakeSubsystem intake) {
    addCommands(
      new ChangeLimePipeline(1,drive),
      new UpdateAllianceColor(vfs),
      new ShootDriveBack(2200, 80, shooter, drive, feeder),
      new RetractTriggerPiston(piston),
      new Pause(1),
      new ExtendTriggerPiston(piston),
      new TurnDegrees(0.3, 120, drive),
      new ToggleIntakeArm(intake),
      new SetIntakeMotor(1, intake),
      new DriveToDistance(20, 0.5, drive),
      new TurnDegrees(0.3, -120, drive),
      new RetractTriggerPiston(piston),
      new Pause(1),
      new StopSMAndFM(shooter, feeder),
      new SetIntakeMotor(0, intake),
      new ExtendTriggerPiston(piston)
    );
  }
}
