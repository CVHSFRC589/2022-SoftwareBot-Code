package frc.robot.commands.Shooter_Commands;
 
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
 
public class ToggleShooting extends InstantCommand {
   
    private final ShooterSubsystem m_shooter;
    /**
     * ToggleShooting Command
     * Toggles on and off the shooter
     */
   
    public ToggleShooting(ShooterSubsystem shooter) {
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