// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;
import frc.robot.commands.Drive_Commands.DriveToDistance;
import frc.robot.commands.Drive_Commands.FaceTarget;
import frc.robot.commands.Drive_Commands.TurnDegrees;
import frc.robot.commands.Intake_Commands.*;
import frc.robot.commands.Misc_Commands.*;
import frc.robot.commands.Shooter_And_Feeder_Commands.StopSMAndFM;
import frc.robot.commands.Trigger_Piston_Commands.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PickUpShoot2ShortSide extends SequentialCommandGroup {
  /** Creates a new TurnPickUpShootx2. */
  public PickUpShoot2ShortSide(DriveTrainSubsystem drive, ShooterSubsystem shooter, FeederSubsystem feeder, VisualFeedbackSubsystem vfs, TriggerPistonSubsystem piston, IntakeSubsystem intake) {
    addCommands(
      new ChangeLimePipeline(1,drive),
      new UpdateAllianceColor(vfs),
      new ToggleIntakeArm(intake),
      new Pause(0.25),
      new StartSMAndFM(2200, 0, shooter, feeder),
      new RunIntakeWhileDriving(6, drive, intake),
      new DriveToDistance(5,0.5,drive),
      new TurnDegrees(0.5, 90, drive),
      new DriveToDistance(-25, 0.5, drive),
      new FaceTarget(drive.getLimeLight(),drive),
      new RetractTriggerPiston(piston),
      new Pause(0.5),
      new ExtendTriggerPiston(piston),
      // new Pause(1.5),
      new ToggleIntakeArm(intake),
      new Pause(2),
      new ToggleIntakeArm(intake),
      new RetractTriggerPiston(piston),
      new Pause(1.5),
      new ExtendTriggerPiston(piston),
      // new ToggleIntakeArm(intake),
      new StopSMAndFM(shooter,feeder)
    );
  }
}
