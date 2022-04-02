// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;
import frc.robot.commands.Drive_Commands.DriveToDistance;
import frc.robot.commands.Misc_Commands.*;
import frc.robot.commands.Shooter_And_Feeder_Commands.*;
import frc.robot.commands.Trigger_Piston_Commands.ExtendTriggerPiston;
import frc.robot.commands.Trigger_Piston_Commands.RetractTriggerPiston;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.TriggerPistonSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisualFeedbackSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveBackShootOnce extends SequentialCommandGroup {
  /** Creates a new AutoPatternOne. */
  public DriveBackShootOnce(DriveTrainSubsystem drive, ShooterSubsystem shooter, FeederSubsystem feeder, VisualFeedbackSubsystem vfs, TriggerPistonSubsystem piston) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ChangeLimePipeline(1,drive),
      new UpdateAllianceColor(vfs),
      new ShootWhileDriving(1975, 75, shooter, drive, feeder),
      // new StartSMAndFM(2075, 3, shooter, feeder),
      // new DriveToDistance(70, .6, drive),
      new RetractTriggerPiston(piston),
      new Pause(2),
      new StopSMAndFM(shooter, feeder),
      new ExtendTriggerPiston(piston)
    );
  }
}
