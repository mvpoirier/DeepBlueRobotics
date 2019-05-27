/*
 *  BHA Robotics Academy -- DEEP BLUE: KRC #5054
 *  FTC Relic Recovery: Robot Hardware Profile (TeleOp and Autonomous)
 *  January 25, 2018
 */

package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
// import com.qualcomm.robotcore.hardware.I2cAddr;
// import com.qualcomm.robotcore.hardware.ColorSensor;

public class HardwareBHA_Combine
{
    /* Public OpMode members. */
    public DcMotor  leftMotor   = null;
    public DcMotor  rightMotor  = null;

    public DcMotor  ballLift = null;

    public Servo glimpseLServo = null; // Left Servo for Glimpse
    public Servo glimpseRServo = null; // Right Servo for Glimpse
    public double glimpseLServoInitial = 0.32;
    public double glimpseRServoInitial = 0.68;

    public Servo ballServo = null; // Servo to knock off enemy ball in autonomous
    public double ballServoInitial = 0.5;
    public double ballServoFinal = 0.6;

    public ColorSensor colorSensor1 = null; // Beacon color detection (0x3a, Port 0)
    public ModernRoboticsI2cGyro gyro = null;

    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareBHA_Combine(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map
        hwMap = ahwMap;

        // Intialize ALL installed servos - Phone Configuration Names are in "quotations"
        glimpseLServo = hwMap.servo.get("glimpse_l_servo");
        glimpseRServo = hwMap.servo.get("glimpse_r_servo");

        glimpseLServo.scaleRange(0.0,1.0);
        glimpseLServo.setPosition(glimpseLServoInitial); //Set position to closed inside lift

        glimpseRServo.scaleRange(0.0,1.0);
        glimpseRServo.setPosition(glimpseRServoInitial); //Set position to closed inside lift

        // Ball Servo
        ballServo = hwMap.servo.get("ball_servo");
        glimpseLServo.scaleRange(0.0,1.0);
        glimpseLServo.setPosition(ballServoInitial); //Set position to closed inside lift

        // Define and Initialize Motors - Phone Configuration Names are in "quotations"
        leftMotor = hwMap.dcMotor.get("left_motor");
        rightMotor = hwMap.dcMotor.get("right_motor");

        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE for AndyMark motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE for AndyMark motors

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // RUN WITH ENCODER
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // RUN WITH ENCODER

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        ballLift = hwMap.dcMotor.get("ball_lift");
        ballLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ballLift.setPower(0);

        colorSensor1 = hwMap.colorSensor.get("sensor_color");
        //colorSensor1.setI2cAddress(I2cAddr.create8bit(0x3a));

        gyro = (ModernRoboticsI2cGyro) hwMap.gyroSensor.get("sensor_gyro");
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