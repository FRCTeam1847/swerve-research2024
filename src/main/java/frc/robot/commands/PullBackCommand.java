package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class PullBackCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;

    public PullBackCommand(IntakeSubsystem subsystem) {
        m_intakeSubsystem = subsystem;
        addRequirements(m_intakeSubsystem); // Ensure no other commands use the same subsystem
    }

    @Override
    public void initialize() {
        m_intakeSubsystem.PullBack(); // Start pull-back
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.Stop(); // Stop pull-back when command ends
    }

    @Override
    public boolean isFinished() {
        return false; // Run until interrupted
    }
}
