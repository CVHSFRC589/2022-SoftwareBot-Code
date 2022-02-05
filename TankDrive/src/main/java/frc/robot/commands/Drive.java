package frc.robot.commands;
 
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 
public class Drive extends CommandBase {
    private final DriveTrainSubsystem m_drivetrain;
    private final DoubleSupplier m_leftY;
    private final DoubleSupplier m_leftX;
    private final DoubleSupplier m_rightY;
    
 
    /**
     * Drive Command
     * takes in left and right control inputs for each motor
     * drivetrain is the subsystem to drive
     */
   
    public Drive(DoubleSupplier leftY, DoubleSupplier rightY, DoubleSupplier leftX, DriveTrainSubsystem drivetrain) {
        //setting parameters to local vars
        m_drivetrain = drivetrain;
        m_leftY = leftY;
        m_leftX = leftX;
        m_rightY = rightY;
        
 
        addRequirements(m_drivetrain);
    }
 
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        
        if(m_drivetrain.getDriveType().getAsBoolean()){
            m_drivetrain.drive(m_leftY.getAsDouble(), m_rightY.getAsDouble());
        }
        else
        {
            m_drivetrain.drive(m_leftY.getAsDouble(), m_leftX.getAsDouble());
        }
        
       // m_drivetrain.setMotors(m_leftY.getAsDouble(), m_rightY.getAsDouble());
        SmartDashboard.putNumber("LeftMotorEncoderDistanceInches", m_drivetrain.getLeftEncoderInches());
        SmartDashboard.putNumber("RightMotorEncoderDistanceInches", m_drivetrain.getRightEncoderInches());
        //System.out.println("encoderworkingyay: " + m_drivetrain.getLeft());
    }
 
  // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false; // Runs until interrupted
    }
    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_drivetrain.drive(0, 0);
    }
}