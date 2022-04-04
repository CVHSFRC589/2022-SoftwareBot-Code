// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.Drive_Commands.DriveToDistance;
import frc.robot.commands.Intake_Commands.SetIntakeMotor;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RunIntakeWhileDriving extends ParallelRaceGroup {
  /** Creates a new RunIntakeWhileDriving. */
  public RunIntakeWhileDriving(double distance, DriveTrainSubsystem drive, IntakeSubsystem intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());1
    addCommands(
      new SetIntakeMotor(1, intake),
      new DriveToDistance(distance, .55, drive)
    );
  }
}
