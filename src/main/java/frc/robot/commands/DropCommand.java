package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShootingSubsystem;

public class DropCommand extends Command {
    private final ShootingSubsystem m_shootingSubsystem;

    public DropCommand(ShootingSubsystem subsystem) {
        m_shootingSubsystem = subsystem;
        addRequirements(m_shootingSubsystem);
    }

    @Override
    public void initialize() {
        m_shootingSubsystem.DropNote();
    }

    @Override
    public void end(boolean interrupted) {
        m_shootingSubsystem.Stop();
    }
}