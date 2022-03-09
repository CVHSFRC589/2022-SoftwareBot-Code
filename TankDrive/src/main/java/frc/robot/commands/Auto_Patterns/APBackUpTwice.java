// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;
import frc.robot.commands.ChangeLimePipeline;
import frc.robot.commands.Pause;
import frc.robot.commands.Climber_Commands.*;
import frc.robot.commands.Drive_Commands.*;
import frc.robot.commands.Shooter_Commands.FeedOneBall;
import frc.robot.commands.Shooter_Commands.FeederStart;
import frc.robot.commands.Shooter_Commands.FeederStop;
import frc.robot.commands.Shooter_Commands.ShootLimeRPM;
import frc.robot.commands.Shooter_Commands.StopShooter;
import frc.robot.commands.UpdateAllianceColor;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystemPID;
import frc.robot.subsystems.VisualFeedbackSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class APBackUpTwice extends SequentialCommandGroup {
  /** Creates a new AutoPatternOne. */
  public APBackUpTwice(DriveTrainSubsystem drive, ShooterSubsystemPID shooter, FeederSubsystem feeder, VisualFeedbackSubsystem vfs) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ChangeLimePipeline(1,drive),
      new UpdateAllianceColor(vfs),
      new Pause(6),
      new AutoStartShooter(2000, 5, shooter),
      // new AutoShootDrive(2000, 52, shooter, drive),
      // new Pause(2),
      new DriveToDistance(52, -.6, drive),
      new FeederStart(feeder),
      new Pause(1),
      new StopShooter(shooter),
      new FeederStop(feeder),
      new DriveToDistance(24, -.6, drive)
    );
  }
}
