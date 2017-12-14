/*
Copyright BHA Beariers Robotics Academy 2016-2017 :-)
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.HardwareBHA;

@Disabled
@TeleOp(name="BHA: TeleOp (No Shooter)", group="BHA")
public class OLD_BHA_TeleOp_NoShooter extends OpMode{

    /* Declare OpMode members. */
    HardwareBHA robot       = new HardwareBHA(); //define a robot object from HardwareBHA class

    // Set power settings for motors
    double BALL_COLLECTOR_POWER = -0.2;
    double BALL_LIFT_POWER = 0.5;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double left;
        double right;

        // The joystick goes negative when pushed forwards, so negate it
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);

        // BUTTON A: Start Ball Collector with Power BALL_COLLECTOR_POWER
        if (gamepad1.a && robot.ballCollector.getPower() == 0.0) {
            robot.ballCollector.setPower(BALL_COLLECTOR_POWER);
        }

        // BUTTON B: Stop Ball Collector
        if (gamepad1.b && robot.ballCollector.getPower() == BALL_COLLECTOR_POWER){
            robot.ballCollector.setPower(0);
        }

        // BUTTON X: Start Ball Lift with Power BALL_LIFT_POWER
        if (gamepad1.x && robot.ballLift.getPower() == 0.0) {
            robot.ballLift.setPower(BALL_LIFT_POWER);
        }

        // BUTTON Y: Stop Ball Lift
        if (gamepad1.y && robot.ballLift.getPower() == BALL_LIFT_POWER){
            robot.ballLift.setPower(0);
        }

        // D-PAD CONTROL OF BEACON SERVO MOTOR [right, left, up (middle)]
        if (gamepad1.dpad_left) {
            //robot.beaconServo.setPosition(1.5);
            robot.beaconServo.setPosition(Servo.MAX_POSITION);
        }

        if (gamepad1.dpad_right) {
            //robot.beaconServo.setPosition(-1.5);
            robot.beaconServo.setPosition(Servo.MIN_POSITION);

        }

        if (gamepad1.dpad_up) {
            robot.beaconServo.setPosition(robot.MID_SERVO);
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
