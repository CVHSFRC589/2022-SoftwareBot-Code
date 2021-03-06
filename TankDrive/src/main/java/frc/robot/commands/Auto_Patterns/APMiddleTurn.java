// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto_Patterns;
import frc.robot.Constants;
import frc.robot.commands.Drive_Commands.*;
import frc.robot.commands.Feeder_Motor_Commands.*;
import frc.robot.commands.Misc_Commands.*;
import frc.robot.commands.Shooter_Motor_Commands.*;
import frc.robot.commands.Trigger_Piston_Commands.ExtendTriggerPiston;
import frc.robot.commands.Trigger_Piston_Commands.RetractTriggerPiston;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TriggerPistonSubsystem;
import frc.robot.subsystems.VisualFeedbackSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class APMiddleTurn extends SequentialCommandGroup {
  /** Creates a new AutoPatternOne. */
  public APMiddleTurn(DriveTrainSubsystem drive, ShooterSubsystem shooter, FeederSubsystem feeder, VisualFeedbackSubsystem vfs, TriggerPistonSubsystem piston) {
    addCommands(
      new ChangeLimePipeline(1,drive),
      new UpdateAllianceColor(vfs),
      new DriveToDistance(70,-.6, drive), //.3 Minimum Speed
      new StartSMAndFM(2000, Constants.FEEDER_MOTOR_RPM, shooter, feeder),
      new RetractTriggerPiston(piston),
      //new FeederStart(feeder),
      new Pause(1),
      new SMStop(shooter),
      new FMStop(feeder),
      new ExtendTriggerPiston(piston),
      new TurnDegrees(.15, 120, drive)
    );
  }
}
