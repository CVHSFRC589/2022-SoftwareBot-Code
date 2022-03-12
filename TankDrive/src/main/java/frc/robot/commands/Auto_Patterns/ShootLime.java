// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;
import frc.robot.commands.Feeder_Commands.FeedOneBall;
import frc.robot.commands.Shooter_Motor_Commands.SMLimeRPM;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystemPID;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootLime extends ParallelCommandGroup {
  /** Creates a new AutoPatternOne. */
  public ShootLime(DriveTrainSubsystem drive, ShooterSubsystemPID shooter, FeederSubsystem feeder) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SMLimeRPM(() -> 0, drive.getLimeLight(), shooter),
      new FeedOneBall(feeder)
    );
  }
}
