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


//import java.util.PrimitiveIterator.OfDouble;
import frc.robot.Constants;
//import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.gyro;


//import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
//import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;
//import frc.robot.RobotContainer;
import frc.robot.Constants.CANBusID;
//import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.math.geometry.Pose2d;
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
  public double drivespeed = 0.85;
  public double rotationspeed = 0.6;
  public boolean fieldoriented = false;

  public RelativeEncoder m_rightEncoder = m_rightlead.getEncoder();
  public RelativeEncoder m_leftEncoder = m_leftlead.getEncoder();

  

  public static final gyro d_gyro = new gyro();

  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(24));
  public DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeadin(d_gyro), new Pose2d(8.0,4.0,new Rotation2d()));
  
  private double encoderDistanceL = 0.0;
  private double encoderDistanceR = 0.0;
  private double encoderDistanceLOld = 0.0;
  private double encoderDistanceROld = 0.0;

  private double encoderDistanceRO;
  private double encoderDistanceLO;

  public Field2d fieldObject = new Field2d();

  
  
  public void init(){
    encoderDistanceLO = 0.0;
    encoderDistanceRO = 0.0;
    
    //odometry.update(getHeadin(d_gyro), 0, 0);

    m_leftEncoder.setPositionConversionFactor(Constants.SpeedConstants.wheelConversion);
    m_rightEncoder.setPositionConversionFactor(Constants.SpeedConstants.wheelConversion);

    SmartDashboard.putNumber("Drive Speed", drivespeed);
    SmartDashboard.putNumber("Rotation Speed", rotationspeed);

    //odometry.resetPosition(new Pose2d(), getHeadin(d_gyro));
    //fieldObject.getObject("RRField");
    //Shuffleboard.getTab("Tab 3").add("fieldExtraWidget", fieldObject).withWidget(BuiltInWidgets.kField);
    //SmartDashboard.putData("please I need help", fieldObject);
  }
  @Override
  public void periodic(){
    
    encoderDistanceL = m_leftEncoder.getPosition();
    encoderDistanceL = m_leftEncoder.getPosition();
    SmartDashboard.putData("Field", fieldObject);
    encoderDistanceLO = encoderDistanceLOld - encoderDistanceL;
    encoderDistanceRO = encoderDistanceROld - encoderDistanceR;

    drivespeed = SmartDashboard.getNumber("Drive Speed", 0.9);
    rotationspeed = SmartDashboard.getNumber("Rotation Speed", 0.6);

    odometry.update(getHeadin(d_gyro), encoderDistanceLO,encoderDistanceRO);

    fieldObject.setRobotPose(odometry.getPoseMeters());


    SmartDashboard.putNumber("x", odometry.getPoseMeters().getX());
    SmartDashboard.putNumber("y", odometry.getPoseMeters().getY());

    
    encoderDistanceLOld = m_leftEncoder.getPosition();
    encoderDistanceROld = m_rightEncoder.getPosition();
    //SmartDashboard.putData("ree", fieldObject);
  }

  public void TankDrive(double left, double right){

      m_leftfollow.follow(m_leftlead);
      m_rightfollow.follow(m_rightlead);
      
      
      m_drive.tankDrive(left * drivespeed, right * drivespeed);

  }

  public void ArcadeDrive(double zRotation, double xSpeed){

      m_leftfollow.follow(m_leftlead);
      m_rightfollow.follow(m_rightlead);

      //I know this looks backwards but trust me.

      //no - nat
      //it literally is backwards
      m_drive.arcadeDrive(zRotation * rotationspeed, xSpeed * drivespeed);

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
    Rotation2d temp = new Rotation2d(
      Units.degreesToRadians(
        yro.getHeading()
      )
      );
    return temp;
  //} catch(Exception e) {
  //  return new Rotation2d();
  //}
}

  public void twist(double speed){ 
    this.ArcadeDrive(speed, 0);
    Timer.delay(0.1);
    this.ArcadeDrive(0,0);
  }

}
