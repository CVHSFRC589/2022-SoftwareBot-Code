// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;
import frc.robot.commands.Pause;
import frc.robot.commands.Drive_Commands.*;
import frc.robot.commands.Shooter_Commands.*;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoPatternScrimmage extends SequentialCommandGroup {
  /** Creates a new AutoPatternOne. */
  public AutoPatternScrimmage(DriveTrainSubsystem drive, ShooterSubsystem shoot) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DriveToDistance(-84,.3, drive), //.3 Minimum Speed
      new Pause(.5),
      new ToggleShooting(shoot),
      new Shoot(0, shoot),
      new Pause(3),
      new FeederStart(shoot),
      new Pause(1.5),
      new FeederStop(shoot),
      new ToggleShooting(shoot)
    );
  }
}
