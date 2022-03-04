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

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANBusID;

public class DriveTrain extends SubsystemBase{
  
  //Left Side Motor Controllers
  private final CANSparkMax m_leftlead = new CANSparkMax(CANBusID.driveLeft1, MotorType.kBrushless);  
  private final CANSparkMax m_leftfollow = new CANSparkMax(CANBusID.driveLeft2, MotorType.kBrushless);
  
  //Right Side Motor Controllers
  private final CANSparkMax m_rightlead = new CANSparkMax(CANBusID.driveRight1, MotorType.kBrushless);
  private final CANSparkMax m_rightfollow = new CANSparkMax(CANBusID.driveRight2, MotorType.kBrushless);

  DifferentialDrive m_drive = new DifferentialDrive(m_leftlead, m_rightlead);

  //setting initial restrictions on the drive and rotation speeds
  double drivespeed = 0.75;
  double rotationspeed = 0.5;
  public boolean fieldoriented = false;

  public void init(){

    SmartDashboard.putNumber("Drive Speed", drivespeed);
    SmartDashboard.putNumber("Rotation Speed", rotationspeed);
  }

  @Override
  public void periodic(){
    drivespeed = SmartDashboard.getNumber("Drive Speed", 0.75);
    rotationspeed = SmartDashboard.getNumber("Rotation Speed", 0.5);
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
      m_drive.arcadeDrive(xSpeed * rotationspeed, zRotation * drivespeed);
  }
}
