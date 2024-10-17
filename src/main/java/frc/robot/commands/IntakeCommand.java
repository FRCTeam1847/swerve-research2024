package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

// Command to run intake
public class IntakeCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;

    public IntakeCommand(IntakeSubsystem subsystem) {
        m_intakeSubsystem = subsystem;
        addRequirements(m_intakeSubsystem); // Ensure no other commands use the same subsystem
    }

    @Override
    public void initialize() {
        m_intakeSubsystem.IntakeNote(); // Start intake
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.Stop(); // Stop intake when command ends
    }

    @Override
    public boolean isFinished() {
        return false; // Run until interrupted
    }
}