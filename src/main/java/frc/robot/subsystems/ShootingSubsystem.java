// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShootingSubsystem extends SubsystemBase {
  private final TalonFX m_motor = new TalonFX(5);
  private final TalonFX m_motor2 = new TalonFX(6);

  /** Creates a new IntakeSubsystem. */
  public ShootingSubsystem() {
  }

  public void ShootNote() {
    m_motor.set(0.75);
    m_motor2.set(0.75);
  }

  public void DropNote() {
    m_motor.set(0.5);
    m_motor2.set(0.5);
  }

   public void PullNote() {
    m_motor.set(-0.3);
    m_motor2.set(-0.3);
  }

  public void Stop() {
    m_motor.stopMotor();
    m_motor2.stopMotor();
  }

  public Command StopCommand() {
    return new Command() {
      @Override
      public void execute() {
        Stop();
      }
    };
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}