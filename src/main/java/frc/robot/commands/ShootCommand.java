package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShootingSubsystem;

public class ShootCommand extends Command {
  private final ShootingSubsystem m_shootingSubsystem;

  public ShootCommand(ShootingSubsystem subsystem) {
    m_shootingSubsystem = subsystem;
    addRequirements(m_shootingSubsystem); // Ensures no other commands use the same subsystem
  }

  @Override
  public void initialize() {
    m_shootingSubsystem.ShootNote();
  }

  @Override
  public void end(boolean interrupted) {
    m_shootingSubsystem.Stop();
  }
}