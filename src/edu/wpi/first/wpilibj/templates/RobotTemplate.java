/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import eddsafjaslkdfju.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;

public class RobotTemplate extends SimpleRobot { 
    DigitalInput limitSwitch1 = new DigitalInput(1);
    
    RobotDrive chassis = new RobotDrive(1, 2, 3, 4);
    Joystick gamepad = new Joystick(1);
    Joystick stick = new Joystick(2);
    
    Jaguar wheelJag = new Jaguar(5); 
    Jaguar armJag = new Jaguar(6); 
   
    public void autonomous() {
        chassis.setSafetyEnabled(false);
        chassis.drive(-.17, 0.0);
        Timer.delay(4.5);
        chassis.drive(0.0, 0.0);
        wheelJag.set(.65);
        Timer.delay(4.0);
        wheelJag.set(0);
    }

    public void operatorControl() {
        while(isOperatorControl() && isEnabled()){
            
            
            //Right Handed
            double speedControl = stick.getZ();
            speedControl++;
            speedControl/=2;
            double axisY = gamepad.getRawAxis(2)*speedControl;
            double axisX = gamepad.getRawAxis(4)*speedControl;
            double axisZ;
            if(stick.getRawButton(2))
                axisZ = stick.getY()*.3;
            else
                axisZ = stick.getY()*.2;
            
            //Left Handed
            //double axisL = gamepad.getRawAxis(1)*.3;
            //double axisR = gamepad.getRawAxis(5)*.4;
            
            if(stick.getRawButton(1)&&stick.getRawButton(5))
                wheelJag.set(-.75);
            else if (stick.getRawButton(3)&&stick.getRawButton(5))
                wheelJag.set(.75);
            else if(stick.getRawButton(1)) //Button B
                wheelJag.set(-.65);
            else if(stick.getRawButton(3)) //Button X
                wheelJag.set(.65);
            else
                wheelJag.set(0);
            
            if(!limitSwitch1.get() && axisZ > 0)
                armJag.set(0);
            else
                armJag.set(axisZ);
            
            chassis.arcadeDrive(axisY, axisX, true);
            Timer.delay(0.005);
        }
    }
}