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
    // m_drivetrain.setDefaultCommand( //for tank drive
    //   new TankDrive(
    //      () -> m_joystick1.getY(), () -> m_joystick1.getY(), m_drivetrain
    //    )
    // );
    m_drivetrain.setDefaultCommand( //for arcade drive
       new Drive(
          () -> m_joystick0.getY(), () -> m_joystick0.getX(), m_drivetrain
        )
     );
    SmartDashboard.putNumber("LeftMotorEncoder", m_drivetrain.getLeftEncoderInches());
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
    JoystickButton j1SpeedIncr = new JoystickButton(m_joystick0, Constants.button3);
    j1SpeedIncr.whenPressed(new goFast(m_drivetrain));
    JoystickButton j1SpeedDecr = new JoystickButton(m_joystick0, Constants.button2);
    j1SpeedDecr.whenPressed(new goSlow(m_drivetrain));
    JoystickButton j1GoToDistance = new JoystickButton(m_joystick0, Constants.button6);
    j1GoToDistance.whenPressed(new driveToDistance(78, .25, m_drivetrain));
    JoystickButton j1FreezeDrive= new JoystickButton(m_joystick0, Constants.button4);
    j1FreezeDrive.whenPressed(new goFreeze(m_drivetrain));

    
    JoystickButton j1TurnRight= new JoystickButton(m_joystick0, Constants.button9);
    j1TurnRight.whenPressed(new turn(1, 0.1, 30, m_drivetrain));//-1 mean turn left
   

    JoystickButton j1MaxSpeed = new JoystickButton(m_joystick1, 1);
    j1MaxSpeed.whenPressed(new SetShooterSpeed(1, m_shooter));
    JoystickButton j1Stop = new JoystickButton(m_joystick1, 2);
    j1Stop.whenPressed(new SetShooterSpeed(0, m_shooter));
    JoystickButton j1Shoot = new JoystickButton(m_joystick1, 3);
    j1Shoot.whenPressed(new SetShooterSpeed(m_shooterSpeed, m_shooter));
    JoystickButton j1IncreaseSpeed = new JoystickButton(m_joystick1, 4);
    j1IncreaseSpeed.whenPressed(new ChangeShooterSpeed(.1, m_shooter));
    JoystickButton j1DecreaseSpeed = new JoystickButton(m_joystick1, 5);
    j1DecreaseSpeed.whenPressed(new ChangeShooterSpeed(-.1, m_shooter));
    JoystickButton j1ToggleAverageAmps = new JoystickButton(m_joystick1, 6);
    j1ToggleAverageAmps.whenPressed(new ToggleAverageAmps(m_shooter));
    JoystickButton j1ResetAverageAmps = new JoystickButton(m_joystick1, 7);
    j1ResetAverageAmps.whenPressed(new ResetAverageAmps(m_shooter));

    // JoystickButton j2SpeedIncr = new JoystickButton(m_joystick2, Constants.button3);
    // j2SpeedIncr.whenPressed(new goFast(m_drivetrain));
    // JoystickButton j2SpeedDecr = new JoystickButton(m_joystick2, Constants.button2);
    // j2SpeedDecr.whenPressed(new goSlow(m_drivetrain));
    // JoystickButton j2SetDead= new JoystickButton(m_joystick2, Constants.button4);
    // j2SetDead.whenPressed(new goFreeze(m_drivetrain));
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