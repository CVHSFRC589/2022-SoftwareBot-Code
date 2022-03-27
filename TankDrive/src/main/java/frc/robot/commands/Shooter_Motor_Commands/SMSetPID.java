// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Motor_Commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.PIDConstants;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SMSetPID extends CommandBase {
  private ShooterSubsystem m_shooter;
  private NetworkTable m_table;
  private NetworkTableEntry m_kP;
  private NetworkTableEntry m_kI;
  private NetworkTableEntry m_kD;
  private NetworkTableEntry m_kIz;
  private NetworkTableEntry m_kFF;
  


  public SMSetPID(ShooterSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem;

    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_kP = m_table.getEntry("kP");
    m_kI = m_table.getEntry("kI");
    m_kD = m_table.getEntry("kD");
    m_kIz = m_table.getEntry("kIz");
    m_kFF = m_table.getEntry("kFF");

    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override
  public void execute(){
    m_shooter.setPID(m_kP.getDouble(PIDConstants.SHOOTER_P),m_kI.getDouble(PIDConstants.SHOOTER_I),m_kD.getDouble(PIDConstants.SHOOTER_D),m_kIz.getDouble(PIDConstants.SHOOTER_Iz),m_kFF.getDouble(PIDConstants.SHOOTER_FF));
  }


  @Override
  public void end(boolean interrupted) {}

  

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
