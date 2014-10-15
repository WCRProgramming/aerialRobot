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
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class RobotTemplate extends SimpleRobot {
    
    RobotDrive chassis = new RobotDrive(5, 2, 3, 4);
    Joystick gamepad = new Joystick(1);
    Button button5 = new JoystickButton(gamepad, 5), 
            button4 = new JoystickButton(gamepad, 4);
    //Joystick rightStick = new Joystick(2);
    //Joystick leftStick = new Joystick(1);
    
   
    public void autonomous() {
       /* chassis.setSafetyEnabled(false);
        chassis.drive(-.5, 0.0);
        Timer.delay(2.0);
        chassis.drive(0.0, 0.0);*/
    }

        public void operatorControl() {
        int x = 0;
        //chassis.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        while(isOperatorControl() && isEnabled()){
            
            
            /*
            double rightS = rightStick.getRawAxis(2);
            double leftS = leftStick.getRawAxis(2);
            rightS = Math.abs(rightS) * rightS;
            leftS = Math.abs(leftS) * leftS;
            
            chassis.tankDrive(leftS, rightS, true);
            */     
            /*double[] speed = {.2, .4, .6, .8, 1};
            
            if(gamepad.getRawButton(4)){
                x++;
                if(x > 4)
                    x = 0;
            } */
            double axisL = gamepad.getRawAxis(2)/2;
            double axisR = gamepad.getRawAxis(4)/2;
            
            
            chassis.arcadeDrive(axisL, axisR, true);
            Timer.delay(0.005);
        }
    }
  
        public void test() {
    
    }
}