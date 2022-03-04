// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drive_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.LimeLight;
import frc.robot.subsystems.DriveTrainSubsystem;

public class FaceTargetContinuous extends CommandBase {
  /** Creates a new FaceTarget. */
  private LimeLight m_limeLight;
  private DriveTrainSubsystem m_drivetrain;
  private double m_startSpeed;

  public FaceTargetContinuous(double startingSpeed, LimeLight limeLight, DriveTrainSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_limeLight = limeLight;
    m_drivetrain = subsystem;
    m_startSpeed = startingSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_limeLight.getIsTargetFound()){
      // if(Math.abs(m_limeLight.getdegRotationToTarget())/31/3>= 0.0)
      // {
      //   double speed = m_limeLight.getdegRotationToTarget()/31/3;
      //   m_drivetrain.setMotors(-speed, speed);
      // }
      // else{
        double direction = Math.abs(m_limeLight.getdegRotationToTarget())/m_limeLight.getdegRotationToTarget();
        m_drivetrain.setMotors(-direction*m_startSpeed, direction*m_startSpeed);
      // }
    }
    else
    {
      m_drivetrain.setMotors(-0.2, 0.2);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.setMotors(0,0);
    if(!interrupted){
      System.out.println("Target Found");
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
     return false;
  }
}
