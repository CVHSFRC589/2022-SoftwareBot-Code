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
import frc.robot.subsystems.*;
import frc.robot.commands.Drive_Commands.*;
import frc.robot.commands.Shooter_Commands.*;
import frc.robot.commands.Climber_Commands.*;
import frc.robot.LimeLightAiming;

 
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem m_drivetrain = new DriveTrainSubsystem();
  private final ShooterSubsystem m_shooter= new ShooterSubsystem();
  private final ClimberSubsystem m_climber = new ClimberSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final LimeLightAiming m_limeLight = new LimeLightAiming();
  private final VisualFeedbackSubsystem m_VFS = new VisualFeedbackSubsystem();//VFS means visualfeedbacksubsystem
  //joysticks we are going to use
  private final Joystick m_joystick0 = new Joystick(0);
  private final Joystick m_joystick1 = new Joystick(1);

  private double m_shooterSpeed = .8;

  SendableChooser<CommandBase> m_chooser = new SendableChooser<>();
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_chooser.setDefaultOption("Simple Auto", new AutoPatternOne(m_drivetrain, m_climber));
    m_chooser.addOption("Drive Forward", new DriveToDistance(36, 0.3, m_drivetrain));
    m_chooser.addOption("Simple Auto + Aim", new AutoPatternTwo(m_drivetrain, m_limeLight));
    SmartDashboard.putData(m_chooser);
    
    m_drivetrain.setDefaultCommand( //for arcade drive
       new Drive(
       () -> m_joystick0.getY(), () -> m_joystick1.getY(), () -> m_joystick0.getX(), m_drivetrain
        )
     );

     //  m_drivetrain.setDefaultCommand( //for arcade drive
    //    new Drive(
    //       () -> 0,() ->  0,() -> 0, m_drivetrain
    //     )
    //  );

      m_shooter.setDefaultCommand(new Shoot(() -> m_joystick0.getZ(), m_shooter));

      SmartDashboard.putData(m_drivetrain);
      SmartDashboard.putData(m_climber);
      SmartDashboard.putData(m_shooter);
      SmartDashboard.putData(m_intake);
      SmartDashboard.updateValues();

      m_drivetrain.updateShuffleboard();

    SmartDashboard.putNumber("ShooterEncoder", m_shooter.getShooterSpeed());
    System.out.println("Shooterworkingyay: " + m_shooter.getShooterSpeed());

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
    //hello
   /**Joystick 0 Buttons**/

    //Driving buttons
    JoystickButton j0DriveTurbo = new JoystickButton(m_joystick0, Constants.DRIVE_MAX_SPEED_BUTTON);
    JoystickButton j0FreezeDrive= new JoystickButton(m_joystick0, Constants.FREEZE_DRIVE_TRAIN_BUTTON);
    JoystickButton j0GoToDistance = new JoystickButton(m_joystick0, Constants.AUTO_DRIVE_DISTANCE_BUTTON);
    JoystickButton j0TurnRight= new JoystickButton(m_joystick0, Constants.TURN_RIGHT_BUTTON);
    JoystickButton j0ChangeDriveState= new JoystickButton(m_joystick0, Constants.TOGGLE_DRIVE_STATE_BUTTON);
    JoystickButton j0FaceTarget = new JoystickButton(m_joystick0, Constants.FACE_TARGET_BUTTON);
    JoystickButton j0LineUpTarget = new JoystickButton(m_joystick0, Constants.LINE_UP_TARGET_BUTTON);
    JoystickButton j0GoToTargetDistance = new JoystickButton(m_joystick0, Constants.GO_TO_TARGET_DISTANCE_BUTTON);


    j0DriveTurbo.whenHeld(new SetSpeedScale(0.85,m_drivetrain));
    j0DriveTurbo.whenReleased((new SetSpeedScale(0.5,m_drivetrain)));
    j0FreezeDrive.whenPressed(new SetSpeedScale(0,m_drivetrain));
    j0GoToDistance.whenPressed(new DriveToDistance(78,0.3, m_drivetrain));
    j0TurnRight.whenPressed(new TurnDegrees(0.1, 90, m_drivetrain));
    j0ChangeDriveState.whenPressed(new ToggleDriveState(m_drivetrain));
    j0FaceTarget.whenPressed(new FaceTarget(0.15, m_limeLight, m_drivetrain));
    j0LineUpTarget.whenPressed(new LineUpTarget(0.1, m_limeLight, m_drivetrain));
    j0GoToTargetDistance.whenPressed(new DriveToGivenTargetDistance(Constants.SHOOTING_DISTANCE, m_limeLight, m_drivetrain));



   //Intake buttons
     JoystickButton j1ToggleIntakeArms = new JoystickButton(m_joystick1, Constants.TOGGLE_INTAKE_ARMS_BUTTON);
     JoystickButton j1StartIntakeMotor = new JoystickButton(m_joystick1, Constants.TOGGLE_INTAKE_MOTOR_BUTTON);
     
     j1ToggleIntakeArms.whenPressed(new ToggleIntakeArm( m_intake));
     j1StartIntakeMotor.whenPressed(new SetIntakeMotor( m_intake, 0.4));
    
    /**Joystick 1 Buttons**/

    //Shooting buttons
    JoystickButton j1ToggleShooting = new JoystickButton(m_joystick1, Constants.TOGGLE_SHOOTING_BUTTON);
    JoystickButton j1Feed = new JoystickButton(m_joystick1, Constants.SHOOTER_FEEDER_MOTOR_BUTTON);
    JoystickButton j1IncreaseSpeed = new JoystickButton(m_joystick1, Constants.INCREASE_SHOOTER_SPEED_BUTTON);
    JoystickButton j1DecreaseSpeed = new JoystickButton(m_joystick1, Constants.DECREASE_SHOOTER_SPEED_BUTTON);
    JoystickButton j1ToggleAverageAmps = new JoystickButton(m_joystick1, Constants.TOGGLE_AVERAGE_SHOOTER_AMPS_BUTTON);
    JoystickButton j1ResetAverageAmps = new JoystickButton(m_joystick1, Constants.RESET_AVERAGE_SHOOTER_AMPS_BUTTON);

    j1Feed.whenHeld(new FeederStart(m_shooter));
    j1Feed.whenReleased(new FeederStop(m_shooter));
    j1ToggleShooting.whenPressed(new ToggleShooting(m_shooter));
    j1IncreaseSpeed.whenPressed(new ChangeShooterSpeed(.1, m_shooter));
    j1DecreaseSpeed.whenPressed(new ChangeShooterSpeed(-.1, m_shooter));
    j1ToggleAverageAmps.whenPressed(new ToggleAverageAmps(m_shooter));
    j1ResetAverageAmps.whenPressed(new ResetAverageAmps(m_shooter));

     //Climbing buttons
     JoystickButton j1Extend = new JoystickButton(m_joystick1, Constants.EXTEND_CLIMBER_ARMS_BUTTON);
     JoystickButton j1Retract = new JoystickButton(m_joystick1, Constants.RETRACT_CLIMBER_ARMS_BUTTON);
 
     j1Extend.whenPressed(new ExtendBothArms( m_climber));
     j1Retract.whenPressed(new RetractBothArms( m_climber));



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