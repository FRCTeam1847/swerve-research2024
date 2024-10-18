// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoShootSequence;
import frc.robot.commands.DropCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.PullBackCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.util.Units;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  private final ShootingSubsystem m_shootingSubsystem = new ShootingSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  double maximumSpeed = Units.feetToMeters(4.5);
  File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(), "swerve");
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
      "swerve"));

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController driverXbox = new CommandXboxController(0);

  private final CommandXboxController driverXbox2 = new CommandXboxController(1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    Command driveFieldOrientedDirectAngle = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> driverXbox.getRightX(),
        () -> driverXbox.getRightY());

    Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getRightX(), OperatorConstants.LEFT_X_DEADBAND));

    drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocity);
  }

  private void configureBindings() {

    // Shooting Subsystem Bindings
    // Bind A button (button 1) to ShootCommand
    // driverXbox2.a().whileTrue(new ShootCommand(m_shootingSubsystem));

    // Bind B button (button 2) to DropCommand
    driverXbox2.b().whileTrue(new DropCommand(m_shootingSubsystem));

    // Intake Subsystem Bindings
    // Bind X button (button 3) to IntakeCommand
    driverXbox2.x().whileTrue(new IntakeCommand(m_intakeSubsystem));

    // Bind Y button (button 4) to PullBackCommand
    driverXbox2.y().whileTrue(new PullBackCommand(m_intakeSubsystem));
    // driverXbox.leftBumper().whileTrue()

    driverXbox2.a().onTrue(
        new ParallelCommandGroup(
            new ShootCommand(m_shootingSubsystem).withTimeout(2), // shoot
            new SequentialCommandGroup(
                new WaitCommand(0.5), // Wait for 0.5 seconds
                new IntakeCommand(m_intakeSubsystem).withTimeout(1.5) // Run intake after the delay)
            )));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    return new AutoShootSequence(m_shootingSubsystem, m_intakeSubsystem);
  }
}
