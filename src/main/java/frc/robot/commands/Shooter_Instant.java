package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Shooter_Instant extends InstantCommand {
  public Shooter_Instant() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(!RobotContainer.m_shooter.isshooteron){
      RobotContainer.m_shooter.shooteron();
    } else{
      RobotContainer.m_shooter.shooteroff();
    }
    }
  }