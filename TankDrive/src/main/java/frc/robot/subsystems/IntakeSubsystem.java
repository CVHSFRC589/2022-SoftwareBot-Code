// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystemyay. */
  private final DoubleSolenoid m_leftArm;
  private final DoubleSolenoid m_rightArm;
  private final CANSparkMax m_intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_PORT, MotorType.kBrushless);;

  public IntakeSubsystem() {
    m_leftArm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1,2);
    m_rightArm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 3,4);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
