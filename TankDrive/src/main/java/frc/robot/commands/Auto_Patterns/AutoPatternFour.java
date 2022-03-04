// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;
import frc.robot.commands.ChangeLimePipeline;
import frc.robot.commands.Pause;
import frc.robot.commands.Climber_Commands.*;
import frc.robot.commands.Drive_Commands.*;
import frc.robot.commands.Shooter_Commands.FeedOneBall;
import frc.robot.commands.Shooter_Commands.ShootLimeRPM;
import frc.robot.commands.Shooter_Commands.StopShooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystemPID;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoPatternFour extends SequentialCommandGroup {
  /** Creates a new AutoPatternOne. */
  public AutoPatternFour(DriveTrainSubsystem drive, ShooterSubsystemPID shooter, FeederSubsystem feeder) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ChangeLimePipeline(1,drive),
      new DriveToDistance(54,-.3, drive), //.3 Minimum Speed
      new FaceTarget(0.1, drive.getLimeLight(), drive),
      //new DEMO_AutoPatternFour(drive, shooter)
      // new FeedOneBall(shooter)
      new AutoStartShooter(1800, 5, shooter),
      new FeedOneBall(feeder),
      new StopShooter(shooter)
    );
  }
}
