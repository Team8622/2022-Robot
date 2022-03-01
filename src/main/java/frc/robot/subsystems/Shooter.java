package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Shooter extends SubsystemBase{
/**
 * create new shooter.
 */
 //shooter Controlers(right side)
private CANSparkMax m_leftleadMotor;
private CANSparkMax m_rightfollowMotor;
private SparkMaxPIDController m_pidController;
private RelativeEncoder m_encoder;
public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;
public double motorspeed;
public void init(){
    try{
        m_leftleadMotor = new CANSparkMax(Constants.CANBusID.leftShooterMotor, MotorType.kBrushless);
        m_rightfollowMotor = new CANSparkMax(Constants.CANBusID.rightShooterMotor, MotorType.kBrushless);
        m_leftleadMotor.restoreFactoryDefaults();
        m_rightfollowMotor.restoreFactoryDefaults();
        m_rightfollowMotor.follow(m_leftleadMotor, true); //Reverses motor
        m_pidController = m_leftleadMotor.getPIDController();
        m_encoder = m_leftleadMotor.getEncoder();
        } catch (RuntimeException ex){
            DriverStation.reportError("error loading failed" + ex.getMessage(), true);
        }
    motorspeed = Constants.SpeedConstants.shootermotorspeed;
    
    //PID coefficients
    kP = 6e-5; 
    kI = 0;
    kD = 0; 
    kIz = 0; 
    kFF = 0.000015; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    maxRPM = 5700;

    //set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Shooter Velocity", motorspeed);
    
  }

  @Override
  public void periodic() {
    // double velocity_out;
    motorspeed = SmartDashboard.getNumber("Shooter Velocity", 0);
    
    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { m_pidController.setP(p); kP = p; }
    if((i != kI)) { m_pidController.setI(i); kI = i; }
    if((d != kD)) { m_pidController.setD(d); kD = d; }
    if((iz != kIz)) { m_pidController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { m_pidController.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
    m_pidController.setOutputRange(min, max); 
    kMinOutput = min; kMaxOutput = max; 
    }

    double velocity_out;
    try {
      velocity_out = m_encoder.getVelocity();
    } catch (RuntimeException ex){
      DriverStation.reportError("Shooter: Not able to get velocity " + ex.getMessage(),true);
      velocity_out = 0;
    }
    SmartDashboard.putNumber("Shooter motor Velocity",velocity_out);
    SmartDashboard.putNumber("Output", m_leftleadMotor.getAppliedOutput());
  }

 public boolean isshooteron = false;
 public void shooteron(){
   
   //m_leftleadMotor.set(motorspeed);
   m_pidController.setReference(-motorspeed, ControlType.kVelocity);
   //m_rightfollowMotor.set(-motorspeed);
   SmartDashboard.putBoolean("shooteron", true);
   isshooteron = true;
 }
 public void shooteroff(){
   
  m_pidController.setReference(motorspeed, ControlType.kVelocity);
  m_leftleadMotor.set(0);

  SmartDashboard.putBoolean("shooteron", false);
  isshooteron = false;
 }
}

