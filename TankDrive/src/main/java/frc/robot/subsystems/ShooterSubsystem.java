package frc.robot.subsystems;
 
//might want to check any methods using this
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder.Type;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
 
public class ShooterSubsystem extends SubsystemBase {
    //not entirely sure about these two??
    private final CANSparkMax m_shooterMotor = new CANSparkMax(Constants.kLeftMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_feederMotor = new CANSparkMax(Constants.kRightMotorPort, MotorType.kBrushless);

    private final RelativeEncoder shooterEncoder = m_shooterMotor.getEncoder();
    private final RelativeEncoder feederEncoder = m_feederMotor.getEncoder();
 
    private final DifferentialDrive m_drive = new DifferentialDrive(m_shooterMotor, m_feederMotor);
    private boolean m_joystickControl = true;
    private double m_shooterSpeed = 0;
    private double m_scaleFactor = 0.25;
 
    /**create a new shooter subsystem */
    public ShooterSubsystem() {
        super();
        
        shooterEncoder.setPosition(0);
        feederEncoder.setPosition(0);
    }
 
    // public void drive(double left, double right) {
    //   m_drive.tankDrive(-left*m_scaleFactor, right*m_scaleFactor);
    // }
    public void drive(double both) {
      if(m_joystickControl)
      {
        m_drive.tankDrive(-both*m_scaleFactor, both*m_scaleFactor);
      }
      else
      {
        m_shooterMotor.set(m_shooterSpeed);
      }
    }

    public void toggleConstantShooterSpeed(){
      m_joystickControl = !m_joystickControl;
      m_shooterSpeed = getShooterSpeed();
    }


    public double getShooterSpeed()
    {
        double shooterSpeed = shooterEncoder.getVelocity()*Constants.driveWheelCircum/Constants.gearRatio;
        System.out.println(shooterSpeed);
        return shooterSpeed;
    }

    public double getFeeder()
    {
        return feederEncoder.getVelocity();
    }

    public void setShooter(int position)
    {
        shooterEncoder.setPosition(position);
    }

    public void setFeeder(int position)
    {
        feederEncoder.setPosition(position);
    }
      /** Call log method every loop. */
  @Override
  public void periodic() {
    //log();
  }
}