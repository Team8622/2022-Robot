// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import frc.robot.gyro;
import frc.robot.commands.DriveTrain_ArcadeDrive;
//import frc.robot.subsystems.DriveTrain;
//import frc.robot.commands.Auto_DriveFwd;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.TimedRobot;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
//import edu.wpi.first.wpilibj2.command.Command;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kOneBall = "One Ball Auto";
  private static final String kTwoBall = "Two Ball Auto";
  private static final String kLineOnly = "Cross Line";
  private String m_autoSelected;
  Thread m_visionThread;

  public RobotContainer m_robotContainer;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static double autoTime;

  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    //camera code
    m_visionThread =
    new Thread(
        () -> {
          UsbCamera camera = CameraServer.startAutomaticCapture();
          camera.setResolution(640, 480);
          CvSink cvSink = CameraServer.getVideo();
          CvSource outputStream = CameraServer.putVideo("Rectangle", 640, 480);
          Mat mat = new Mat();
          while (!Thread.interrupted()) {
            if (cvSink.grabFrame(mat) == 0) {
              outputStream.notifyError(cvSink.getError());
              continue;
            }
            Imgproc.rectangle(
                mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);

            outputStream.putFrame(mat);
          }
        });
  m_visionThread.setDaemon(true);
  m_visionThread.start();
  //end camera code
  
    m_chooser.setDefaultOption("One Ball Auto", kOneBall);
    m_chooser.addOption("Two Ball Auto", kTwoBall);
    m_chooser.addOption("Cross Line", kLineOnly);
    SmartDashboard.putData("Auto choices", m_chooser);
    m_robotContainer = new RobotContainer();
}

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    MotorSafety.checkMotors();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    //m_robotContainer.getAutonomousCommand();
    m_autoSelected = m_chooser.getSelected();
    //m_autoSelected = SmartDashboard.getString("Auto Selector", kOneBall);
    System.out.println("Running Auto....?");// + m_autoSelected);

    RobotContainer.m_driveTrain.drivespeed = 0.75;
    RobotContainer.m_driveTrain.rotationspeed = 0.5;
    autoTime = Timer.getFPGATimestamp();
    // RobotContainer.m_driveTrain.ArcadeDrive(0.0, -0.75);
    // Timer.delay(2.0);
    // RobotContainer.m_driveTrain.ArcadeDrive(0.0, 0.0);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    //hey look, this works too!   
    RobotContainer.m_driveTrain.drivespeed = 0.75;
    RobotContainer.m_driveTrain.rotationspeed = 0.5;

    double time = Timer.getFPGATimestamp();
    double timeDiff = time - Robot.autoTime;
    
    //I know that this works
    //RobotContainer.m_driveTrain.ArcadeDrive(0, 0.5);
    //SmartDashboard.putBoolean("Auto On", true);

    switch (m_autoSelected) {
      case kOneBall:
            //drive robot backwards, turn off the shooter and intake (run for 1 seconds the first time, one second after we shoot)
            if(timeDiff < 1.0){
              RobotContainer.m_driveTrain.ArcadeDrive(0.0, -0.62);
              SmartDashboard.putBoolean("Auto On", true);
            //after driving for 2 seconds, shoot the preload (running for 1 sec)
            }else if (timeDiff >= 1.0 && timeDiff < 3.0){
              RobotContainer.m_shooter.shooteron();
              Timer.delay(1.0);
              RobotContainer.m_intake.intakeon();
            //turn 180 degrees
            }else if(timeDiff >= 3.0 && timeDiff < 4.0){
              RobotContainer.m_intake.intakeoff();
              RobotContainer.m_shooter.shooteroff();
              RobotContainer.m_driveTrain.ArcadeDrive(0.94, 0.0);
            //drive forward;
            }else if(timeDiff >= 4.5 && timeDiff < 7.25){
              RobotContainer.m_driveTrain.ArcadeDrive(0.0, 0.4);
            }else{
              RobotContainer.m_intake.intakeoff();
              RobotContainer.m_shooter.shooteroff();
              RobotContainer.m_driveTrain.ArcadeDrive(0.0, 0.0);
            }  
        break;
      case kTwoBall:
      default:
            //drive robot backwards, turn off the shooter and intake (run for 1 seconds the first time, one second after we shoot)
            if(timeDiff < 1.0){
              RobotContainer.m_driveTrain.ArcadeDrive(0.0, -0.62);
              SmartDashboard.putBoolean("Auto On", true);
            //after driving for 2 seconds, shoot the preload (running for 1 sec)
            }else if (timeDiff >= 1.0 && timeDiff < 3.0){
              RobotContainer.m_shooter.shooteron();
              Timer.delay(1.0);
              RobotContainer.m_intake.intakeon();
            //turn 180 degrees
            }else if(timeDiff >= 3.0 && timeDiff < 4.0){
              RobotContainer.m_intake.intakeoff();
              RobotContainer.m_shooter.shooteroff();
              RobotContainer.m_driveTrain.ArcadeDrive(0.94, 0.0);
            //drive forward; pick up preset ball in front
            }else if(timeDiff >= 4.5 && timeDiff < 7.25){
              RobotContainer.m_intake.intakeon();
              RobotContainer.m_driveTrain.ArcadeDrive(0.0, 0.4);
            //turn 180 degrees
            }else if(timeDiff >= 7.25 && timeDiff < 8.75){
              RobotContainer.m_intake.intakeoff();
              RobotContainer.m_driveTrain.ArcadeDrive(-.85,0);
            //move forward to shooting sweet spot
            }else if(timeDiff >= 8.75 && timeDiff < 10.5){
              RobotContainer.m_driveTrain.ArcadeDrive(0,0.45);
            //run the intake back briefly to set for shot
            }else if(timeDiff >= 10.5 && timeDiff < 10.65){
              RobotContainer.m_driveTrain.ArcadeDrive(0.0, 0.0);
              RobotContainer.m_intake.reverseon();
            //turn on the shot
            }else if(timeDiff >= 10.65 && timeDiff < 10.7){
              RobotContainer.m_intake.intakeoff();
              RobotContainer.m_shooter.shooteron();
            //feed ball to shot, thereby shooting
            }else if(timeDiff>=10.7 && timeDiff < 12){
              RobotContainer.m_intake.intakeon();
            //drive back enough to clear the initiation line
            }else if(timeDiff >= 12 && timeDiff < 14){
              RobotContainer.m_driveTrain.ArcadeDrive(0.0, -0.45);
            //turn it all off
            } else{
                RobotContainer.m_driveTrain.ArcadeDrive(0.0, 0.0); 
                RobotContainer.m_intake.intakeoff();
                RobotContainer.m_shooter.shooteroff();
            }
        break;
      case kLineOnly: 
            if(timeDiff < 1.0){
              RobotContainer.m_driveTrain.ArcadeDrive(0.0, -0.62);
              SmartDashboard.putBoolean("Auto On", true);
            }else{
              RobotContainer.m_driveTrain.ArcadeDrive(0,0);
            }
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    RobotContainer.m_driveTrain.setDefaultCommand(new DriveTrain_ArcadeDrive(RobotContainer.m_driveTrain));
    
    RobotContainer.m_driveTrain.drivespeed = 0.9;
    RobotContainer.m_driveTrain.rotationspeed = 0.6;
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    RobotContainer.m_gyro.periodic();
    
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}