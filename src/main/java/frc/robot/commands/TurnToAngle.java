/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController; 
import frc.robot.subsystems.DriveTrain;
import frc.robot.gyro;
import frc.robot.Constants.DriveConstants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
//RobotContainer.m_driveTrain.TankDrive(ControlChooser.leftJoy.getY(), ControlChooser.rightJoy.getY())
public class TurnToAngle extends PIDCommand {
  // This will turn to a fixed angle determined by where the gyro was set to zero  
  public TurnToAngle( double targetAngleDegrees, DriveTrain drive, gyro sampleGyro, boolean relativeAngle) {
    super( 
         new PIDController(DriveConstants.kTurnP, DriveConstants.kTurnI, DriveConstants.kTurnD),
          // Close loop on heading
          sampleGyro::getHeading,
          // Set reference to target, make sure not bigger then +- 180
          // If relative angle then add to current heading
          Math.IEEEremainder(targetAngleDegrees+(relativeAngle ? sampleGyro.getHeading() : 0), 360),
          // Pipe output to turn robot
          output -> drive.ArcadeDrive( MathUtil.clamp(output, -DriveConstants.kMaxTurnPIDTurnSpeed, 
          DriveConstants.kMaxTurnPIDTurnSpeed), 0),
          // Require the drive
          drive);

    // Set the controller to be continuous (because it is an angle controller)
    getController().enableContinuousInput(-180, 180);
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController()
        .setTolerance(DriveConstants.kPositionToleranceInches, DriveConstants.kTurnRateToleranceDegPerS);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}

