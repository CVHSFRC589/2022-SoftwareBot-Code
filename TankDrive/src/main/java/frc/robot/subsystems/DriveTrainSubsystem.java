package frc.robot.subsystems;
 
//might want to check any methods using this
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import java.util.function.BooleanSupplier;

// import edu.wpi.first.util.sendable.Sendable;
// import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimeLightAiming;
 
public class DriveTrainSubsystem extends SubsystemBase {
    private final CANSparkMax m_leftMotor = new CANSparkMax(Constants.DRIVE_LEFT_MOTOR_PORT, MotorType.kBrushless);
    private final CANSparkMax m_rightMotor = new CANSparkMax(Constants.DRIVE_RIGHT_MOTOR_PORT, MotorType.kBrushless);

    private final RelativeEncoder leftEncoder = m_leftMotor.getEncoder();
    private final RelativeEncoder rightEncoder = m_rightMotor.getEncoder();
 
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    private static BooleanSupplier driveType = () -> true;
    private static LimeLightAiming m_aiming = new LimeLightAiming();
    

    private double m_scaleFactor = 0.5;
 
    /**create a new drive train subsystem */
    public DriveTrainSubsystem() {
        super();
        m_leftMotor.setInverted(true); //true
        m_rightMotor.setInverted(false); //false
        reset();
    }
 
    public void drive(double left, double right) {
      if(driveType.getAsBoolean())
      {
        m_drive.tankDrive(left*m_scaleFactor, right*m_scaleFactor);
      }
      else
      {
        m_drive.arcadeDrive(left*m_scaleFactor, -right*m_scaleFactor);
      }
    }

    public void reset(){
      setLeftEncoder(0);
      setRightEncoder(0);
      setMotors(0,0);
      m_scaleFactor = 0.5;
    }

    public void setMotors(double leftSpeed, double rightSpeed){
      m_leftMotor.set(leftSpeed);
      m_rightMotor.set(rightSpeed);
    }

    public void tankDrive(double leftSpeed, double rightSpeed){
      m_drive.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void setScaleFactor(double scaleFactor){
      m_scaleFactor = scaleFactor;
    }

    public void switchDrive() {
      if(driveType.getAsBoolean()) 
          driveType = () -> false;
      else
          driveType = () -> true;
    }

    public BooleanSupplier getDriveType(){
      return driveType;
    }

    public double getLeftEncoderInches()
    {
        return leftEncoder.getPosition()*Constants.DRIVE_WHEEL_CIRCUM/Constants.DRIVE_GEAR_RATIO;
    }

    public double getRightEncoderInches()
    {
        return rightEncoder.getPosition()*Constants.DRIVE_WHEEL_CIRCUM/Constants.DRIVE_GEAR_RATIO;
    }

    public double getAverageEncoderInches(){
      double avgEncoderInches = (Math.abs(getRightEncoderInches())+Math.abs(getLeftEncoderInches()))/2;
      return avgEncoderInches;
    }

    private void setLeftEncoder(int position)
    {
        leftEncoder.setPosition(position);
    }

    private void setRightEncoder(int position)
    {
        rightEncoder.setPosition(position);
    }

    public void updateShuffleboard()
    {
      SmartDashboard.putNumber("LeftMotorDistanceInches", getLeftEncoderInches());
      SmartDashboard.putNumber("RightMotorDistanceInches", getRightEncoderInches());
      // SmartDashboard.putNumber("LeftMotorRPM", leftEncoder.getVelocity());
      // SmartDashboard.putNumber("RightMotorRPM", rightEncoder.getVelocity());
    }

      /** Call log method every loop. */
  @Override
  public void periodic() {
    // m_aiming.updateLimelightValues();
    updateShuffleboard();
    //log();
  }
}