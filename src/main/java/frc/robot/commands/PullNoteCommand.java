package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShootingSubsystem;

public class PullNoteCommand extends Command {
  private final ShootingSubsystem m_shootingSubsystem;

  public PullNoteCommand(ShootingSubsystem subsystem) {
    m_shootingSubsystem = subsystem;
    addRequirements(m_shootingSubsystem); // Ensures no other commands use the same subsystem
  }

  @Override
  public void initialize() {
    m_shootingSubsystem.PullNote();
  }

  @Override
  public void end(boolean interrupted) {
    m_shootingSubsystem.Stop();
  }
}