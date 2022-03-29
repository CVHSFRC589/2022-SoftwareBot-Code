// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;
import frc.robot.Constants;
import frc.robot.LimeLightAiming;
import frc.robot.commands.Drive_Commands.*;
import frc.robot.commands.Misc_Commands.*;
import frc.robot.commands.Shooter_And_Feeder_Commands.StopSMAndFM;
import frc.robot.commands.Trigger_Piston_Commands.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveBackLimeShootOnce extends SequentialCommandGroup {
  /** Creates a new AutoPatternOne. */
  public DriveBackLimeShootOnce(DriveTrainSubsystem drive, ShooterSubsystem shooter, FeederSubsystem feeder, LimeLightAiming lime, VisualFeedbackSubsystem vfs, TriggerPistonSubsystem piston) {
    addCommands(
      new ChangeLimePipeline(1,drive),
      new UpdateAllianceColor(vfs),
      new DriveToGivenTargetDistance(Constants.SHOOTING_DISTANCE,drive.getLimeLight(), drive), //Change constant
      new StartSMAndFM(2000, Constants.FEEDER_MOTOR_RPM, shooter, feeder),
      new RetractTriggerPiston(piston),
      new Pause(1),
      new StopSMAndFM(shooter, feeder),
      new ExtendTriggerPiston(piston)
    );
  }
}
