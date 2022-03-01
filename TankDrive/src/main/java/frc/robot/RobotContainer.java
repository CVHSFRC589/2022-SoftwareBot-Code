// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot;
 
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.commands.*;
import frc.robot.commands.Auto_Patterns.AutoPatternOne;
import frc.robot.commands.Auto_Patterns.AutoPatternScrimmage;
import frc.robot.commands.Auto_Patterns.AutoPatternTwo;
import frc.robot.subsystems.*;
import frc.robot.commands.Drive_Commands.*;
import frc.robot.commands.Intake_Commands.SetIntakeMotor;
import frc.robot.commands.Intake_Commands.ToggleIntakeArm;
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
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final LimeLightAiming m_limeLight = new LimeLightAiming();
  private final VisualFeedbackSubsystem m_VFS = new VisualFeedbackSubsystem();//VFS means visualfeedbacksubsystem
  private final ShooterSubsystemPID m_shooterPID= new ShooterSubsystemPID();

  //joysticks we are going to use
  private final Joystick m_joystick0 = new Joystick(0);
  private final Joystick m_joystick1 = new Joystick(1);
  private final Joystick m_joystick2 = new Joystick(2);


  SendableChooser<CommandBase> m_chooser = new SendableChooser<>();
  
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_chooser.setDefaultOption("Simple Auto", new AutoPatternOne(m_drivetrain, m_climber));
    m_chooser.addOption("Simple Auto + Aim", new AutoPatternTwo(m_drivetrain, m_limeLight));
    m_chooser.addOption("Scrimmage Auto Drive+Shoot", new AutoPatternScrimmage(m_drivetrain, m_shooterPID));
    SmartDashboard.putData(m_chooser);
    SmartDashboard.putData("UpdateAllianceColor", new UpdateAllianceColor(m_VFS));
    SmartDashboard.putData("SetRPM", new SetRPMFromShuffleboard(m_shooterPID));
    SmartDashboard.putData("SwitchPiP", new SwitchPIP(m_drivetrain));
    // SmartDashboard.putData("SetPipeline", new ChangeLimePipeline(m_drivetrain));

    m_drivetrain.setDefaultCommand( //for arcade drive or tank drive
       new Drive(
       () -> m_joystick0.getY(), () -> m_joystick1.getY(), () -> m_joystick0.getX(), m_drivetrain //2nd getY should be joystick 1
        )
     );

      SmartDashboard.putData(m_drivetrain);
      SmartDashboard.putData(m_climber);
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
   /**Joystick 0 Buttons**/

    //Driving buttons
    JoystickButton j0DriveTurbo = new JoystickButton(m_joystick0, Constants.DRIVE_MAX_SPEED_BUTTON);
    JoystickButton j0ShootWithRPMInput = new JoystickButton(m_joystick0, Constants.SHOOTER_WITH_INPUT_RPM);
    // JoystickButton j0GoToDistance = new JoystickButton(m_joystick0, Constants.AUTO_DRIVE_DISTANCE_BUTTON);
    // JoystickButton j0TurnRight= new JoystickButton(m_joystick0, Constants.TURN_RIGHT_BUTTON);
    JoystickButton j0ChangeDriveState= new JoystickButton(m_joystick0, Constants.TOGGLE_DRIVE_STATE_BUTTON);
    JoystickButton j0FaceTarget = new JoystickButton(m_joystick0, Constants.FACE_TARGET_BUTTON);
    JoystickButton j0LineUpTarget = new JoystickButton(m_joystick0, Constants.LINE_UP_TARGET_BUTTON);
    JoystickButton j0GoToTargetDistance = new JoystickButton(m_joystick0, Constants.GO_TO_TARGET_DISTANCE_BUTTON);

    j0DriveTurbo.whenHeld(new SetSpeedScale(0.85,m_drivetrain));
    j0DriveTurbo.whenReleased((new SetSpeedScale(0.5,m_drivetrain)));
    j0ShootWithRPMInput.whenPressed(new ShootRPM(1900, m_shooterPID));
    // j0GoToDistance.whenPressed(new DriveToDistance(78,0.3, m_drivetrain));
    // j0TurnRight.whenPressed(new TurnDegrees(0.1, 90, m_drivetrain));
    j0ChangeDriveState.whenPressed(new ToggleDriveState(m_drivetrain));
    j0FaceTarget.whenPressed(new FaceTarget(0.15, m_limeLight, m_drivetrain));
    j0LineUpTarget.whenPressed(new LineUpTarget(0.1, m_limeLight, m_drivetrain));
    j0GoToTargetDistance.whenPressed(new DriveToGivenTargetDistance(Constants.SHOOTING_DISTANCE, m_limeLight, m_drivetrain));

     //Toggle VFS
     //Intake buttons
     JoystickButton j0ToggleVisualFeedback = new JoystickButton(m_joystick0, Constants.TOGGLE_LED_BUTTON);
     j0ToggleVisualFeedback.whenPressed(new ToggleLEDs(m_VFS));

    /**Joystick 2 Buttons**/

    //Shooting buttons
    JoystickButton j2ToggleShooting = new JoystickButton(m_joystick2, Constants.TOGGLE_SHOOTING_BUTTON);
    JoystickButton j2Feed = new JoystickButton(m_joystick2, Constants.SHOOTER_FEEDER_MOTOR_BUTTON);
    JoystickButton j2MiniShoot = new JoystickButton(m_joystick2, Constants.MINI_SHOOT_BUTTON);
    JoystickButton j2CloseShoot = new JoystickButton(m_joystick2, Constants.CLOSE_SHOOTING_BUTTON);
    JoystickButton j2FarShoot = new JoystickButton(m_joystick2, Constants.FAR_SHOOTING_BUTTON);
    
    j2Feed.whenHeld(new FeederStart(m_shooterPID));
    j2Feed.whenReleased(new FeederStop(m_shooterPID));
    j2ToggleShooting.toggleWhenPressed(new ShootPID(() -> m_joystick2.getZ(), m_shooterPID));
    j2CloseShoot.toggleWhenPressed(new ShootRPM(Constants.CLOSE_SHOOTING_RPM, () -> m_joystick2.getZ(), m_shooterPID));
    j2FarShoot.toggleWhenPressed(new ShootRPM(Constants.FAR_SHOOTING_RPM, () -> m_joystick2.getZ(), m_shooterPID));  
    j2MiniShoot.whenHeld(new ShootRPM(250,m_shooterPID));
    j2MiniShoot.whenReleased(new ShootRPM(0,m_shooterPID));

     //Climbing buttons
     JoystickButton j2Extend = new JoystickButton(m_joystick2, Constants.EXTEND_CLIMBER_ARMS_BUTTON);
     JoystickButton j2Retract = new JoystickButton(m_joystick2, Constants.RETRACT_CLIMBER_ARMS_BUTTON);
 
     j2Extend.whenPressed(new ExtendBothArms(m_climber));
     j2Retract.whenPressed(new RetractBothArms(m_climber));

      //Intake buttons
      JoystickButton j2ToggleIntakeArms = new JoystickButton(m_joystick2, Constants.TOGGLE_INTAKE_ARMS_BUTTON);
      JoystickButton j2StartIntakeMotor = new JoystickButton(m_joystick2, Constants.TOGGLE_INTAKE_MOTOR_BUTTON);
      
      j2ToggleIntakeArms.whenPressed(new ToggleIntakeArm(m_intake));
      j2StartIntakeMotor.whenPressed(new SetIntakeMotor(m_intake, 0.4));
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