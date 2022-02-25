package frc.robot.commands.Shooter_Commands;
 
import frc.robot.subsystems.ShooterSubsystemPID;
import edu.wpi.first.wpilibj2.command.InstantCommand;
 
public class TogglePIDShooting extends InstantCommand {
   
    private final ShooterSubsystemPID m_shooter;
    /**
     * ToggleShooting Command
     * Toggles on and off the shooter
     */
   
    public TogglePIDShooting(ShooterSubsystemPID shooter) {
        m_shooter = shooter;
        addRequirements(m_shooter);
    }
 
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_shooter.toggleShooting();
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {}
}