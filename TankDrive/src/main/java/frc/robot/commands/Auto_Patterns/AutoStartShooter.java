// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.Pause;
import frc.robot.commands.Shooter_Commands.ShootRPM;
import frc.robot.subsystems.ShooterSubsystemPID;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoStartShooter extends ParallelRaceGroup {
  /** Creates a new AutoStartShooter. */
  public AutoStartShooter(double rpm, double seconds, ShooterSubsystemPID subsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ShootRPM(rpm, subsystem),
      new Pause(seconds)
    );
  }
}