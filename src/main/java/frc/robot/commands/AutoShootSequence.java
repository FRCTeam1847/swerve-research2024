package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoShootSequence extends SequentialCommandGroup {
    public AutoShootSequence(ShootingSubsystem shootingSubsystem, IntakeSubsystem intakeSubsystem) {
        addCommands(
                // Step 1: Pull back intake
                new PullBackCommand(intakeSubsystem).withTimeout(1), // Ensure pull back runs for 1 second

                // Step 2: Wait for 1 second
                new WaitCommand(1),

                // Step 3: Shoot
                new ShootCommand(shootingSubsystem).withTimeout(2), // Run the shoot command for 2 seconds

                // Step 4: Run intake for 2 seconds
                new IntakeCommand(intakeSubsystem).withTimeout(2),

                // Step 5: Stop everything
                intakeSubsystem.StopCommand(),
                shootingSubsystem.StopCommand());
    }
}