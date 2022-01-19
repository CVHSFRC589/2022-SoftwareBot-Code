package frc.robot.subsystems;
 
//might want to check any methods using this
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
 
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
 
public class DriveTrainSubsystem extends SubsystemBase {
    //not entirely sure about these two??
    private final CANSparkMax m_leftMotor = new CANSparkMax(Constants.kLeftMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_rightMotor = new CANSparkMax(Constants.kRightMotorPort, MotorType.kBrushless);
 
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    private double m_scaleFactor = 1.0;
 
    /**create a new drive train subsystem */
    public DriveTrainSubsystem() {
        super();
    }
 
    public void drive(double left, double right) {
      // left = Constants.LeftSpeed/m_scaleFactor;
      // right = Constants.RightSpeed/m_scaleFactor;
 
      m_drive.tankDrive(-left*m_scaleFactor, right*m_scaleFactor);
    }
 
    public void goFast()
    {
        m_scaleFactor = 1.0;
    }
 
    public void goSlow()
    {
        m_scaleFactor = 0.5;
    }
      /** Call log method every loop. */
  @Override
  public void periodic() {
    //log();
  }
}