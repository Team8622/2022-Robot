package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Winch extends SubsystemBase{

private CANSparkMax m_L_armLead;
private CANSparkMax m_R_armFollow;
private RelativeEncoder m_encoder;
public double motorspeed;
public void init(){
    try{
        m_L_armLead = new CANSparkMax(Constants.CANBusID.armLead, MotorType.kBrushless);
        m_R_armFollow = new CANSparkMax(Constants.CANBusID.armFollow, MotorType.kBrushless);
        m_L_armLead.restoreFactoryDefaults();
        m_R_armFollow.restoreFactoryDefaults();
        m_R_armFollow.follow(m_L_armLead, true);
        m_encoder = m_L_armLead.getEncoder();
        } catch (RuntimeException ex){
            DriverStation.reportError("error loading failed" + ex.getMessage(), true);
        } 
    motorspeed = Constants.SpeedConstants.winchmotorspeed;
        
    
  }

  @Override
  public void periodic() {
    // double velocity_out;
    motorspeed = SmartDashboard.getNumber("Winch Velocity", 0);

    double velocity_out;
    try {
      velocity_out = m_encoder.getVelocity();
    } catch (RuntimeException ex){
      DriverStation.reportError("Winch: Not able to get velocity " + ex.getMessage(),true);
      velocity_out = 0;
    }
    SmartDashboard.putNumber("Winch motor Velocity",velocity_out);
    SmartDashboard.putNumber("Output", m_L_armLead.getAppliedOutput());
  }

 public boolean iswinchon = false;
 public void winchon(){
   
    m_L_armLead.set(-motorspeed);
   //m_pidController.setReference(-motorspeed, ControlType.kVelocity);
   m_R_armFollow.set(motorspeed);
   SmartDashboard.putBoolean("Winch On", true);
   iswinchon = true;
 }
 public void reverseon(){
  m_L_armLead.set(-motorspeed * 0.25);
  m_R_armFollow.set(-motorspeed * 0.25);
  SmartDashboard.putBoolean("Intake On", true);
  iswinchon = true;
}
 public void winchoff(){
   
  //m_pidController.setReference(motorspeed, ControlType.kVelocity);
  m_L_armLead.set(0);
  m_R_armFollow.set(0);

  SmartDashboard.putBoolean("Winch On", false);
  iswinchon = false;
 }
}
