// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.Feeder_Motor_Commands.FMRunAtRPM;
import frc.robot.commands.Misc_Commands.*;
import frc.robot.subsystems.FeederSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class StartFM extends ParallelRaceGroup {
  /** Creates a new AutoStartShooter. */
  public StartFM(double rpmS, double seconds, FeederSubsystem feeder) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new FMRunAtRPM(rpmS, feeder),
      new Pause(seconds)
    );
  }
}
