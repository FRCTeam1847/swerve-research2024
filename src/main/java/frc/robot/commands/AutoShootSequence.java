package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoShootSequence extends SequentialCommandGroup {
    public AutoShootSequence(ShootingSubsystem shootingSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(

                // Step 1: Pull back intake
                new ParallelCommandGroup(
                        new PullNoteCommand(shootingSubsystem).withTimeout(1),
                        new PullBackCommand(intakeSubsystem).withTimeout(3) // Pull back runs for 0.5 second
                ),
                // Step 2: Wait for 0.5 seconds before shooting
                new WaitCommand(2),

                // Step 3: Shoot and Intake in parallel with a small delay on the intake
                new ParallelCommandGroup(
                        new ShootCommand(shootingSubsystem).withTimeout(1), // Run shoot for 0.5 seconds
                        new SequentialCommandGroup(
                                new WaitCommand(1), // Add a 0.1 second delay for intake
                                new IntakeCommand(intakeSubsystem).withTimeout(1.5) // Intake runs for 0.5 seconds
                        )),

                // Step 4: Stop everything
                intakeSubsystem.StopCommand(),
                shootingSubsystem.StopCommand());
    }
}