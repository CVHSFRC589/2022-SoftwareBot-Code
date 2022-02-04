package frc.robot.commands;
 
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 
public class Drive extends CommandBase {
    private final DriveTrainSubsystem m_drivetrain;
    private final DoubleSupplier m_left;
    private final DoubleSupplier m_right;
 
    /**
     * Drive Command
     * takes in left and right control inputs for each motor
     * drivetrain is the subsystem to drive
     */
   
    public Drive(DoubleSupplier left, DoubleSupplier right, DriveTrainSubsystem drivetrain) {
        //setting parameters to local vars
        m_drivetrain = drivetrain;
        m_left = left;
        m_right = right;
 
        addRequirements(m_drivetrain);
    }
 
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_drivetrain.drive(m_left.getAsDouble(), m_right.getAsDouble());
        SmartDashboard.putNumber("LeftMotorEncoderDistanceInches", m_drivetrain.getLeftEncoderInches());
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