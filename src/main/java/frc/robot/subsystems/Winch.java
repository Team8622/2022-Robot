/**
 * @author Rachel Mohammed
 * March 8th, 2022
 */

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;
import frc.robot.Constants.CANBusID;

public class Winch extends SubsystemBase{
  
  //motor controllers
  private CANSparkMax m_lead;
  private CANSparkMax m_follow;
  public double winchspeed;
  public boolean iswinchon = false;

  public void init(){
    m_lead = new CANSparkMax(CANBusID.armLead, MotorType.kBrushless);
    m_follow = new CANSparkMax(CANBusID.armFollow, MotorType.kBrushless);

    m_follow.follow(m_lead);
    //winchspeed = Constants.SpeedConstants.winchmotorspeed;
  }

  @Override
  public void periodic(){
    SmartDashboard.putNumber("Winch Output", m_lead.getAppliedOutput());
  }

  public void release(){
    m_lead.set(0.1);
    SmartDashboard.putBoolean("Winch On", true);
    iswinchon = true;
  }

  public void winch(){
    m_lead.set(-0.5);
    SmartDashboard.putBoolean("Winch On", true);
    iswinchon = true;
  }

  public void winchoff(){
    m_lead.set(0);
    SmartDashboard.putBoolean("Winch On", false);
    iswinchon = false;
  }
}

