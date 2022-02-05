// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot;
 
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.*;
import frc.robot.commands.Shooter_Commands.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
 
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
 
  //joysticks we are going to use
  private final Joystick m_joystick0 = new Joystick(0);
  private final Joystick m_joystick1 = new Joystick(1);

  private double m_shooterSpeed = .2;

  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
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

       //m_shooter.setDefaultCommand(new Shoot(m_shooter));

    SmartDashboard.putNumber("LeftMotorEncoder", m_drivetrain.getLeftEncoderInches());
    SmartDashboard.putNumber("RightMotorEncoder", m_drivetrain.getRightEncoderInches());

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
    //Driving buttons
    JoystickButton j0SpeedIncr = new JoystickButton(m_joystick0, Constants.INCREASE_DRIVE_SPEED_BUTTON);
    JoystickButton j0SpeedDecr = new JoystickButton(m_joystick0, Constants.DECREASE_DRIVE_SPEED_BUTTON);
    JoystickButton j0FreezeDrive= new JoystickButton(m_joystick0, Constants.FREEZE_DRIVE_TRAIN_BUTTON);
    JoystickButton j0GoToDistance = new JoystickButton(m_joystick0, Constants.AUTO_DRIVE_DISTANCE_BUTTON);
    JoystickButton j0TurnRight= new JoystickButton(m_joystick0, Constants.TURN_RIGHT_BUTTON);
    JoystickButton j0ChangeDriveState= new JoystickButton(m_joystick0, Constants.TOGGLE_DRIVE_STATE_BUTTON);

    j0SpeedIncr.whenPressed(new setSpeedScale(1,m_drivetrain));
    j0SpeedDecr.whenPressed(new setSpeedScale(0.5,m_drivetrain));  
    j0FreezeDrive.whenPressed(new setSpeedScale(0,m_drivetrain));
    j0GoToDistance.whenPressed(new driveToDistance(78,.3, m_drivetrain));
    j0TurnRight.whenPressed(new turnDegrees(.1, 90, m_drivetrain));
    j0ChangeDriveState.whenPressed(new toggleDriveState(m_drivetrain));
    

    //Shooting buttons
    JoystickButton j1Shoot = new JoystickButton(m_joystick1, Constants.START_SHOOTER_MOTOR_BUTTON);
    JoystickButton j1MaxSpeed = new JoystickButton(m_joystick1, Constants.MAX_SHOOTER_SPEED_BUTTON);
    JoystickButton j1Stop = new JoystickButton(m_joystick1, Constants.SHOOTER_STOP_BUTTON);
    JoystickButton j1IncreaseSpeed = new JoystickButton(m_joystick1, Constants.INCREASE_SHOOTER_SPEED_BUTTON);
    JoystickButton j1DecreaseSpeed = new JoystickButton(m_joystick1, Constants.DECREASE_SHOOTER_SPEED_BUTTON);
    JoystickButton j1ToggleAverageAmps = new JoystickButton(m_joystick1, Constants.TOGGLE_AVERAGE_SHOOTER_AMPS_BUTTON);
    JoystickButton j1ResetAverageAmps = new JoystickButton(m_joystick1, Constants.RESET_AVERAGE_SHOOTER_AMPS_BUTTON);

    j1MaxSpeed.whenPressed(new SetShooterSpeed(1, m_shooter));
    j1Stop.whenPressed(new SetShooterSpeed(0, m_shooter));
    j1Shoot.whenPressed(new SetShooterSpeed(m_shooterSpeed, m_shooter));
    j1IncreaseSpeed.whenPressed(new ChangeShooterSpeed(.1, m_shooter));
    j1DecreaseSpeed.whenPressed(new ChangeShooterSpeed(-.1, m_shooter));
    j1ToggleAverageAmps.whenPressed(new ToggleAverageAmps(m_shooter));
    j1ResetAverageAmps.whenPressed(new ResetAverageAmps(m_shooter));
  }
 
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
 // }
 
}