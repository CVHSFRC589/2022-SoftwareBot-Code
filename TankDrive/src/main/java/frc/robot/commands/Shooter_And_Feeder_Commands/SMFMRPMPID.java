// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_And_Feeder_Commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Feeder_Motor_Commands.FMRPM;
import frc.robot.commands.Shooter_Motor_Commands.SMRPM;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SMFMRPMPID extends ParallelCommandGroup {
  /** Creates a new SMFMRPMPID. */
  public SMFMRPMPID(double rpmS, double rpmF, ShooterSubsystem subsystem, FeederSubsystem fSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SMRPM(rpmS, subsystem),
      new FMRPM(rpmF, fSubsystem)
    );
  }
}
