/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import java.lang.*;

public class RobotTemplate extends SimpleRobot {
    
    RobotDrive chassis = new RobotDrive(2, 1);
    Joystick rightStick = new Joystick(2);
    Joystick leftStick = new Joystick(1);
    //double rAxis = rightStick.getRawAxis(1);
    
   
    /*public void autonomous() {
        chassis.setSafetyEnabled(false);
        chassis.drive(-.5, 0.0);
        Timer.delay(2.0);
        chassis.drive(0.0, 0.0);
    }*/

        public void operatorControl() {
        
        chassis.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        while(isOperatorControl() && isEnabled()){
            //chassis.arcadeDrive(rightStick, true);
        double rightS = rightStick.getRawAxis(2);
        double leftS = leftStick.getRawAxis(2);
        rightS = Math.abs(rightS) * rightS;
        leftS = Math.abs(leftS) * leftS;
        
        // ATTN:   IF the robot doesn't work, comment out these 3 lines
        //This (should) enable the third axis(the switch on the back middle) to control acceleration of the robot
        double z = rightStick.getZ()+1;
        z/=2;
        rightS*=z;
        leftS*=z;
        
            //chassis.arcadeDrive(rightS);
            chassis.tankDrive(leftS, rightS, true);
            Timer.delay(0.005);
        }
    }
  
        public void test() {
    
    }
}
