package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This is the BHA Robotics Academy Hardware Profile. THIS IS NOT AN OpMode!
 *
 * This Hardware Profile is derived from HardwarePushbot in FtcRobotController/...
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 */
public class JIU_HardwareBHA_Grabber_Test
{
    /* Public OpMode members. */

    public Servo glimpseLServo = null; // Left Servo for Glimpse
    public Servo glimpseRServo = null; // Right Servo for Glimpse
    public double glimpseLServoInitial = 200;
    public double glimpseRServoInitial = 100;

    // public GyroSensor gyro = null;

    // Static variables for Servos from Hardware Pushbot
    // (may need to change for each individual servo)

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public JIU_HardwareBHA_Grabber_Test(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors: Phone Configuration Variables

        glimpseLServo = hwMap.servo.get("glimpse_l_servo");
        glimpseRServo = hwMap.servo.get("glimpse_r_servo");

        // gyro = (ModernRoboticsI2cGyro)hwMap.gyroSensor.get("gyro");

        // Intialize ALL installed servos
        glimpseLServo.scaleRange(1,250);
        glimpseLServo.setPosition(glimpseLServoInitial); //Set position to top/middle

        glimpseRServo.scaleRange(1,250);
        glimpseRServo.setPosition(glimpseRServoInitial); //Set position to low-state inside the lift

    }
    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}