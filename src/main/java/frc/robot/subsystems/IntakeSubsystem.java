// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  private final WPI_VictorSPX m_motor = new WPI_VictorSPX(3);
  private final WPI_VictorSPX m_motor2 = new WPI_VictorSPX(2);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
  }

  public void IntakeNote() {
    m_motor.set(0.6);
    m_motor2.set(0.6);
  }

  public void PullBack() {
    m_motor.set(-0.4);
    m_motor2.set(-0.4);
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