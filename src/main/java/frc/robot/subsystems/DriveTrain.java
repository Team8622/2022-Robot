/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * This is the class definition for the DriveTrain object
 */

/** */
package frc.robot.subsystems;

import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.gyro;


import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.CANBusID;

import edu.wpi.first.math.geometry.Rotation2d;


public class DriveTrain extends SubsystemBase{
  
  //Left Side Motor Controllers
  private final CANSparkMax m_leftlead = new CANSparkMax(CANBusID.driveLeft1, MotorType.kBrushless);  
  private final CANSparkMax m_leftfollow = new CANSparkMax(CANBusID.driveLeft2, MotorType.kBrushless);
  
  //Right Side Motor Controllers
  private final CANSparkMax m_rightlead = new CANSparkMax(CANBusID.driveRight1, MotorType.kBrushless);
  private final CANSparkMax m_rightfollow = new CANSparkMax(CANBusID.driveRight2, MotorType.kBrushless);

  DifferentialDrive m_drive = new DifferentialDrive(m_leftlead, m_rightlead);

Rotation2d poopsTemp = new Rotation2d();

  //setting initial restrictions on the drive and rotation speeds
  double drivespeed = 0.75;
  double rotationspeed = 0.5;
  public boolean fieldoriented = false;

  private Encoder m_rightEncoder;
  private Encoder m_leftEncoder;

  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(28));
  public DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeadin(RobotContainer.m_gyro));

 

  public void init(){

    SmartDashboard.putNumber("Drive Speed", drivespeed);
    SmartDashboard.putNumber("Rotation Speed", rotationspeed);

  }
  @Override
  public void periodic(){
    drivespeed = SmartDashboard.getNumber("Drive Speed", 0.75);
    rotationspeed = SmartDashboard.getNumber("Rotation Speed", 0.5);
    odometry.update(getHeadin(RobotContainer.m_gyro), m_rightEncoder.getDistance(), m_leftEncoder.getDistance());
    // timer.reset();
    SmartDashboard.putNumber("x", odometry.getPoseMeters().getX());
    SmartDashboard.putNumber("y", odometry.getPoseMeters().getY());

  }

  public void TankDrive(double left, double right){
      m_leftfollow.follow(m_leftlead);
      m_rightfollow.follow(m_rightlead);

      m_drive.tankDrive(left * drivespeed, right * drivespeed);
  }

  public void ArcadeDrive(double xSpeed, double zRotation){
      m_leftfollow.follow(m_leftlead);
      m_rightfollow.follow(m_rightlead);

      //I know this looks backwards but trust me.
      //no - nat
      m_drive.arcadeDrive(xSpeed * rotationspeed, zRotation * drivespeed);
  }

  public void setMotors(double left, double right){
      m_leftfollow.follow(m_leftlead);
      m_rightfollow.follow(m_rightlead);
    
      m_leftlead.set(left);
      m_rightlead.set(right);
  }
  public DifferentialDriveWheelSpeeds getSpeeds(){
    return new DifferentialDriveWheelSpeeds(
      m_leftlead.getEncoder().getVelocity() / 1 * 2 * Math.PI * Units.inchesToMeters(3.0) / 60,
      m_rightlead.getEncoder().getVelocity() / 1 * 2 * Math.PI * Units.inchesToMeters(3.0) / 60
      //change 1 to gear ratio
      );
  }
  public Rotation2d getHeadin(gyro yro) {
    
  //return Rotation2d.fromDegrees(RobotContainer.m_gyro.getHeading());
  //try {
    Rotation2d temp = new Rotation2d(Units.degreesToRadians(yro.getHeading()));
    return temp;
  //} catch(Exception e) {
  //  return new Rotation2d();
  //}
}
}
