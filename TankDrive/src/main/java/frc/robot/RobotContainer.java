// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.commands.Auto_Patterns.*;
import frc.robot.subsystems.*;
import frc.robot.commands.Drive_Commands.*;
import frc.robot.commands.Feeder_Motor_Commands.*;
import frc.robot.commands.Intake_Commands.*;
import frc.robot.commands.Misc_Commands.*;
import frc.robot.commands.Shooter_And_Feeder_Commands.*;
import frc.robot.commands.Shooter_Motor_Commands.*;
import frc.robot.commands.Trigger_Piston_Commands.*;
import frc.robot.commands.Climber_Commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem m_drivetrain = new DriveTrainSubsystem();
  private final ClimberSubsystem m_climber = new ClimberSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final LimeLightAiming m_limeLight = new LimeLightAiming();
  private final VisualFeedbackSubsystem m_VFS = new VisualFeedbackSubsystem();// VFS means visualfeedbacksubsystem
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();
  private final FeederSubsystem m_feeder = new FeederSubsystem();
  private final TriggerPistonSubsystem m_piston = new TriggerPistonSubsystem();

  // joysticks we are going to use
  private final Joystick m_joystick0 = new Joystick(0);
  private final Joystick m_joystick1 = new Joystick(1);

  SendableChooser<CommandBase> m_chooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // m_chooser.addOption("Back Up", new APBackUp(m_drivetrain, m_VFS, m_piston));
    m_chooser.addOption("Shoot Once (Anywhere)", new DriveBackShootOnce(m_drivetrain, m_shooter, m_feeder, m_VFS, m_piston));
    // m_chooser.addOption("Shoot Twice Two Turns", new ShootTurnPickUpShoot(m_drivetrain, m_shooter, m_feeder, m_VFS, m_piston, m_intake));
    m_chooser.setDefaultOption("2 Ball Auto (Hangar Side)", new PickUpShoot2(m_drivetrain, m_shooter, m_feeder, m_VFS, m_piston, m_intake));
    m_chooser.addOption("2 Ball Auto (Terminal Side)", new PickUpShoot2ShortSide(m_drivetrain, m_shooter, m_feeder, m_VFS, m_piston, m_intake));
    SmartDashboard.putData(m_chooser);
    SmartDashboard.putData("UpdateAllianceColor", new UpdateAllianceColor(m_VFS));
    SmartDashboard.putData("Toggle Limelight LEDs", new ToggleLimelightLEDs(m_limeLight));
    // SmartDashboard.putData("SetRPM", new SMRPMFromShuffleboard(m_shooter));
    // SmartDashboard.putData("SwitchPiP", new SwitchPIP(m_drivetrain));
    // SmartDashboard.putData("SetPipeline", new ChangeLimePipeline(m_drivetrain));
    SmartDashboard.putData(m_feeder);
    SmartDashboard.updateValues();



    m_drivetrain.setDefaultCommand(
        new Drive(
            () -> m_joystick0.getY(), () -> m_joystick0.getX(), m_drivetrain
        ));

    m_shooter.setDefaultCommand(new SMControl(() -> m_joystick1.getZ(), m_shooter));
    m_feeder.setDefaultCommand(new FMControl(m_feeder));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    ///////*****/////// Button Creation ///////*****///////

    /* Joystick 0: */
    // Driving buttons
    JoystickButton j0GoToTargetDistance = new JoystickButton(m_joystick0, Constants.GO_TO_TARGET_DISTANCE_BUTTON);
    JoystickButton j0FaceTarget = new JoystickButton(m_joystick0, Constants.FACE_TARGET_BUTTON);
    JoystickButton j0ReverseDriving = new JoystickButton(m_joystick0,Constants.REVERSE_DRIVING_BUTTON);
    // JoystickButton j0DriveToDistance = new JoystickButton(m_joystick0, Constants.AUTO_DRIVE_DISTANCE_BUTTON);

    // Feeding Buttons
    JoystickButton j0TogglePiston = new JoystickButton(m_joystick0, Constants.TOGGLE_PISTON_BUTTON);
    // JoystickButton j0Feed = new JoystickButton(m_joystick0, Constants.SHOOTER_FEEDER_MOTOR_BUTTON);
    // JoystickButton j0ReverseFeeder = new JoystickButton(m_joystick0, Constants.REVERSE_FEEDER_MOTOR_BUTTON);

    // Intake buttons
    JoystickButton j0ToggleIntakeArms = new JoystickButton(m_joystick0, Constants.TOGGLE_INTAKE_ARMS_BUTTON);
    JoystickButton j0StartIntakeMotor = new JoystickButton(m_joystick0, Constants.TOGGLE_INTAKE_MOTOR_BUTTON);
    JoystickButton j0ReverseIntakeMotor = new JoystickButton(m_joystick0, Constants.REVERSE_INTAKE_MOTOR_BUTTON);


    /** Joystick 1: **/
    // Shooting buttons
    JoystickButton j1ToggleShooting = new JoystickButton(m_joystick1, Constants.TOGGLE_SHOOTING_BUTTON);
    JoystickButton j1SpitOutBall = new JoystickButton(m_joystick1, Constants.SPIT_OUT_BALL_BUTTON);
    JoystickButton j1CloseShoot = new JoystickButton(m_joystick1, Constants.CLOSE_SHOOTING_BUTTON);
    JoystickButton j1MediumShoot = new JoystickButton(m_joystick1, Constants.MEDIUM_SHOOTING_BUTTON);
    JoystickButton j1FarShoot = new JoystickButton(m_joystick1, Constants.FAR_SHOOTING_BUTTON);
    // JoystickButton j1ShootFeedTest = new JoystickButton(m_joystick1, Constants.STOP_SHOOTER_BUTTON);
    // JoystickButton j1LimelightShoot = new JoystickButton(m_joystick1, Constants.LIMELIGHT_RPM_SHOOT_BUTTON); // needs more testing

    // Climbing buttons
    JoystickButton j1ToggleClimberExtended = new JoystickButton(m_joystick1, Constants.TOGGLE_CLIMBER_ARMS_BUTTON);
    

    ///////*****/////// Button Implementation ///////*****///////

    /** Joystick 0: **/
    // Driving Buttons
    j0GoToTargetDistance.whenPressed(new DriveToGivenTargetDistance(Constants.SHOOTING_DISTANCE, m_limeLight, m_drivetrain));
    j0FaceTarget.toggleWhenPressed(new FaceTarget(m_limeLight, m_drivetrain));
    j0ReverseDriving.whenPressed(new ToggleReverseDriving(m_drivetrain));
    // j0DriveToDistance.whenPressed(new DriveToDistance(70, 0.6, m_drivetrain));
    // j0DriveRPM.toggleWhenPressed(new DriveRPM(1000, 1000, m_drivetrain));

    // Feeding Buttons
    j0TogglePiston.whenHeld(new RetractTriggerPiston(m_piston));
    j0TogglePiston.whenReleased(new ExtendTriggerPiston(m_piston));
    // j0Feed.whenHeld(new FMStart(m_feeder));
    // j0Feed.whenReleased(new FMStop(m_feeder));
    // j0ReverseFeeder.whenHeld(new ReverseFeeder(m_feeder));
    // j0ReverseFeeder.whenHeld(new ReverseFeeder(m_feeder));

    // Intake buttons
    j0ToggleIntakeArms.whenPressed(new ToggleIntakeArm(m_intake));
    j0StartIntakeMotor.toggleWhenPressed(new SetIntakeMotor(1, m_intake));
    j0ReverseIntakeMotor.toggleWhenPressed(new SetIntakeMotor(-0.7, m_intake));

    /* Joystick 1: */
    // Shooting Buttons
    // j1ShootFeedTest.whenPressed(new FMRunAtRPM(800, m_feeder));
    j1ToggleShooting.whenPressed(new ToggleSMAndFM(m_feeder, m_shooter));
    j1CloseShoot.whenPressed(new SMFMRPMPID(Constants.CLOSE_SHOOTING_RPM, Constants.FEEDER_MOTOR_RPM, m_shooter, m_feeder));
    j1MediumShoot.whenPressed(new SMFMRPMPID(Constants.MEDIUM_SHOOTING_RPM, Constants.FEEDER_MOTOR_RPM, m_shooter, m_feeder));
    j1FarShoot.whenPressed(new SMFMRPMPID(Constants.FAR_SHOOTING_RPM, Constants.FEEDER_MOTOR_RPM, m_shooter, m_feeder));
    j1SpitOutBall.whenPressed(new SMFMRPMPID(1000, Constants.FEEDER_MOTOR_RPM, m_shooter, m_feeder));
    
    // j1LimelightShoot.toggleWhenPressed(new SMLimeRPM(() -> m_joystick2.getZ(), m_limeLight, m_shooterPID));

    // Climbing Buttons
    j1ToggleClimberExtended.whenPressed(new ToggleClimberArms(m_climber));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}