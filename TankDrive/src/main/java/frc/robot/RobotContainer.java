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

import frc.robot.commands.*;
import frc.robot.commands.Auto_Patterns.*;
import frc.robot.subsystems.*;
import frc.robot.commands.Drive_Commands.*;
// import frc.robot.commands.Intake_Commands.*;
import frc.robot.commands.Shooter_Commands.*;
import frc.robot.commands.Climber_Commands.*;

 
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem m_drivetrain = new DriveTrainSubsystem();
  //private final ShooterSubsystem m_shooter= new ShooterSubsystem();
  private final ClimberSubsystem m_climber = new ClimberSubsystem();
  // private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final LimeLightAiming m_limeLight = new LimeLightAiming();
  private final VisualFeedbackSubsystem m_VFS = new VisualFeedbackSubsystem();//VFS means visualfeedbacksubsystem
  private final ShooterSubsystemPID m_shooterPID= new ShooterSubsystemPID();
  private final FeederSubsystem m_feeder = new FeederSubsystem();

  //joysticks we are going to use
  private final Joystick m_joystick0 = new Joystick(0);
  private final Joystick m_joystick1 = new Joystick(1);
  private final Joystick m_joystick2 = new Joystick(2);


  SendableChooser<CommandBase> m_chooser = new SendableChooser<>();
  
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
    m_chooser.setDefaultOption("Wait, back up once", new APBackUpOnce(m_drivetrain, m_shooterPID, m_feeder, m_VFS));
    m_chooser.addOption("Old Comp Auto", new APCompetition1(m_drivetrain, m_shooterPID, m_feeder, m_VFS));
    // m_chooser.addOption("Drive+Shoot, Turn Right", new AutoPatternMiddleSpin(m_drivetrain, m_shooterPID, m_feeder, m_VFS));
    // m_chooser.addOption("Drive Lime, Shoot, Turn Right", new AutoPatternLimelight(m_drivetrain, m_shooterPID, m_feeder, m_limeLight, m_VFS));
    SmartDashboard.putData(m_chooser);
    SmartDashboard.putData("UpdateAllianceColor", new UpdateAllianceColor(m_VFS));
    // SmartDashboard.putData("SetRPM", new SetRPMFromShuffleboard(m_shooterPID));
    // SmartDashboard.putData("SwitchPiP", new SwitchPIP(m_drivetrain));
    // SmartDashboard.putData("SetPipeline", new ChangeLimePipeline(m_drivetrain));
    SmartDashboard.putData("Toggle Limelight LEDs", new ToggleLimelightLEDs(m_limeLight));
    SmartDashboard.putData(new SetShooterPID(m_shooterPID));
    m_drivetrain.setDefaultCommand( //for arcade drive or tank drive
       new Drive(
       () -> m_joystick0.getY(), () -> m_joystick1.getY(), () -> m_joystick0.getX(), m_drivetrain //2nd getY should be joystick 1
        )
     );

     m_shooterPID.setDefaultCommand(new ShootPID(() -> m_joystick2.getZ(), m_shooterPID));

      SmartDashboard.putData(m_drivetrain);
      // SmartDashboard.putData(m_climber);
      SmartDashboard.putData(m_shooterPID);
      //SmartDashboard.putData(m_intake);
      SmartDashboard.updateValues();

    // Configure the button bindings
    configureButtonBindings();
  }
 
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
  {

    /*** Button Creation ***/
    
    /*Joystick 0:*/
      //Driving buttons
    //JoystickButton j0DriveTurbo = new JoystickButton(m_joystick0, Constants.DRIVE_MAX_SPEED_BUTTON);
    //JoystickButton j0ChangeDriveState= new JoystickButton(m_joystick0, Constants.TOGGLE_DRIVE_STATE_BUTTON);
    JoystickButton j0GoToTargetDistance = new JoystickButton(m_joystick0, Constants.GO_TO_TARGET_DISTANCE_BUTTON);
    JoystickButton j0ToggleFaceTarget = new JoystickButton(m_joystick0, Constants.FACE_TARGET_CONTINUOUS_BUTTON);
    JoystickButton j0FaceTarget = new JoystickButton(m_joystick0, Constants.FACE_TARGET_BUTTON);
    JoystickButton j0AbortDriveCommand = new JoystickButton(m_joystick0, Constants.STOP_DRIVE_TRAIN_BUTTON);

      //Feeding Buttons
    JoystickButton j0Feed = new JoystickButton(m_joystick0, Constants.SHOOTER_FEEDER_MOTOR_BUTTON);
    JoystickButton j0FeedOne = new JoystickButton(m_joystick0, Constants.FEED_ONE_BALL_BUTTON);
    JoystickButton j0EatBall = new JoystickButton(m_joystick0, Constants.EAT_BALL_BUTTON);

      //LED Control
    // JoystickButton j0ToggleVisualFeedback = new JoystickButton(m_joystick0, Constants.TOGGLE_LED_BUTTON);


    /**Joystick 2:**/
      //Shooting buttons
    JoystickButton j2ToggleShooting = new JoystickButton(m_joystick2, Constants.TOGGLE_SHOOTING_BUTTON);
    JoystickButton j2SpitOutBall = new JoystickButton(m_joystick2, Constants.SPIT_OUT_BALL_BUTTON);
    JoystickButton j2CloseShoot = new JoystickButton(m_joystick2, Constants.CLOSE_SHOOTING_BUTTON);
    JoystickButton j2FarShoot = new JoystickButton(m_joystick2, Constants.FAR_SHOOTING_BUTTON);
    JoystickButton j2LimelightShoot = new JoystickButton(m_joystick2, Constants.LIMELIGHT_RPM_SHOOT_BUTTON);
    JoystickButton j2StopShooter = new JoystickButton(m_joystick2, Constants.STOP_SHOOTER_BUTTON);
    

      //Climbing buttons
    JoystickButton j2ToggleClimberExtended = new JoystickButton(m_joystick2, Constants.TOGGLE_CLIMBER_ARMS_BUTTON);





    /*** Button Implementation ***/

    /*Joystick 0:*/
      //Driving Buttons
    //j0DriveTurbo.whenHeld(new SetSpeedScale(1.0,m_drivetrain));
    //j0DriveTurbo.whenReleased((new SetSpeedScale(0.8,m_drivetrain)));
    //j0ChangeDriveState.whenPressed(new ToggleDriveState(m_drivetrain));
    j0ToggleFaceTarget.toggleWhenPressed(new FaceTargetContinuous(0.1, m_limeLight, m_drivetrain));
    j0GoToTargetDistance.whenPressed(new DriveToGivenTargetDistance(Constants.SHOOTING_DISTANCE, m_limeLight, m_drivetrain));
    j0AbortDriveCommand.whenPressed(new StopDriveTrain(m_drivetrain));
    j0FaceTarget.toggleWhenPressed(new FaceTarget(0.1, m_limeLight, m_drivetrain));

      //Feeding Buttons
    j0Feed.whenHeld(new FeederStart(m_feeder));
    j0Feed.whenReleased(new FeederStop(m_feeder));
    j0FeedOne.whenPressed(new FeedOneBall(m_feeder));

      //LED Control
     //j0ToggleVisualFeedback.whenPressed(new ToggleLEDs(m_VFS));

    /*Joystick 2:*/
      //Shooting Buttons
    j2StopShooter.whenPressed(new StopShooter(m_shooterPID));
    j2ToggleShooting.toggleWhenPressed(new ToggleShooting(m_shooterPID));
    j2CloseShoot.toggleWhenPressed(new ShootRPM(Constants.CLOSE_SHOOTING_RPM, () -> m_joystick2.getZ(), m_shooterPID));
    j2FarShoot.toggleWhenPressed(new ShootRPM(Constants.FAR_SHOOTING_RPM, () -> 0, m_shooterPID));  
    j2SpitOutBall.toggleWhenPressed(new ShootRPM(250,m_shooterPID));
    //j2MiniShoot.whenReleased(new ShootRPM(0,m_shooterPID));
    j2LimelightShoot.toggleWhenPressed(new ShootLimeRPM(() -> m_joystick2.getZ(),m_limeLight, m_shooterPID));
    j0EatBall.whenHeld(new ReverseShooterAndFeeder(m_feeder));

      //Climbing Buttons
    j2ToggleClimberExtended.whenPressed(new ToggleClimberArms(m_climber));


    //Intake buttons
    // JoystickButton j2ToggleIntakeArms = new JoystickButton(m_joystick2, Constants.TOGGLE_INTAKE_ARMS_BUTTON);
    // JoystickButton j2StartIntakeMotor = new JoystickButton(m_joystick2, Constants.TOGGLE_INTAKE_MOTOR_BUTTON);
    
    // j2ToggleIntakeArms.whenPressed(new ToggleIntakeArm(m_intake));
    // j2StartIntakeMotor.whenPressed(new SetIntakeMotor(m_intake, 0.4));
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