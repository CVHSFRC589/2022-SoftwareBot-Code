// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.Drive_Commands.DriveToDistance;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootWhileDriving extends ParallelRaceGroup {
  /** Creates a new AutoStartShooter. */
  public ShootWhileDriving(double rpm, double distance, ShooterSubsystem shooter, DriveTrainSubsystem drive, FeederSubsystem feeder) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new StartSMAndFM(rpm, 3, shooter, feeder),
      new DriveToDistance(distance, .55, drive)
    );
  }
}
