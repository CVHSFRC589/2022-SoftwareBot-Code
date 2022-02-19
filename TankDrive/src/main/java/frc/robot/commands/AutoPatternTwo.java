// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.commands.Climber_Commands.*;
import frc.robot.commands.Drive_Commands.*;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.LimeLight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoPatternTwo extends SequentialCommandGroup {
  /** Creates a new AutoPatternOne. */
  public AutoPatternTwo(DriveTrainSubsystem drive, LimeLight limeLight) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DriveToDistance(48,.3, drive), //.3 Minimum Speed
      new Pause(.5),
      new TurnDegrees(0.25, 90, drive),
      new Pause(.5),
      new DriveToDistance(36, 0.3, drive),
      new Pause(1),
      new TurnDegrees(0.25, 90, drive),
      new FaceTarget(0.075, limeLight, drive),
      new LineUpTarget(0.2, limeLight, drive)
    );
  }
}
