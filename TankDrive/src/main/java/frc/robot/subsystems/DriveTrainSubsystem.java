package frc.robot.subsystems;
 
//might want to check any methods using this
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
 
public class DriveTrainSubsystem extends SubsystemBase {
    private final CANSparkMax m_leftMotor = new CANSparkMax(Constants.kLeftMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_rightMotor = new CANSparkMax(Constants.kRightMotorPort, MotorType.kBrushless);

    private final RelativeEncoder leftEncoder = m_leftMotor.getEncoder();
    private final RelativeEncoder rightEncoder = m_rightMotor.getEncoder();
 
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    private boolean m_normalDrive = true;
    private double m_scaleFactor = 0.25;
    private double m_driveInches = 0;
 
    /**create a new drive train subsystem */
    public DriveTrainSubsystem() {
        super();
        m_rightMotor.setInverted(false);
        m_leftMotor.setInverted(true); //swap inverted
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }
 
    public void drive(double left, double right) {
      if(m_normalDrive)
      {
        // m_drive.tankDrive(left*m_scaleFactor, right*m_scaleFactor);
        m_drive.arcadeDrive(left*m_scaleFactor, -right*m_scaleFactor);

      }
      else
      {
        goToDistance();
      }
    }

    public void setMotors(double leftSpeed, double rightSpeed){
      m_leftMotor.set(leftSpeed);
      m_rightMotor.set(rightSpeed);
    }
 
    public void goFast()
    {
        m_scaleFactor = 1.0;
    }
 
    public void goSlow()
    {
        m_scaleFactor = 0.5;
    }

    public void goFreeze()
    {
      m_scaleFactor = 0;
  
    }

    public void goToDistance()
    {
     // m_driveInches = distanceInches;
      if(getLeftEncoderInches()<= m_driveInches){ 
        setMotors(.25,-.25);
        System.out.println("it is working");
      }
    }
    public void driveToDistance(double distanceInches)
    {
      m_normalDrive = false;
      leftEncoder.setPosition(0);
      rightEncoder.setPosition(0);
      m_driveInches = distanceInches;
    }

    
    public void setScaleFactor(double scaleFactor){
      m_scaleFactor = scaleFactor;
    }

    public void startTurning(double direction, double speed)
    {
      m_drive.tankDrive(0.0, 0.0);
      setLeftEncoder(0);
      setRightEncoder(0);
      //m_drive.tankDrive(direction*speed, -direction*speed);
      setMotors(direction*speed,-direction*speed);
    }

    public double getLeftEncoderInches()
    {
        double distanceInches = leftEncoder.getPosition()*Constants.driveWheelCircum/Constants.gearRatio;
        return distanceInches;
    }

    public double getRightEncoderInches()
    {
        return rightEncoder.getPosition()*Constants.driveWheelCircum/Constants.gearRatio;
    }

    public void setLeftEncoder(int position)
    {
        leftEncoder.setPosition(position);
    }

    public void setRightEncoder(int position)
    {
        rightEncoder.setPosition(position);
    }

      /** Call log method every loop. */
  @Override
  public void periodic() {
    //log();
  }
}