/*
 *  BHA Robotics Academy -- DEEP BLUE: KRC #5054
 *  FTC Relic Recovery: TeleOp FINAL (Drive, Lift, and Grab)
 *  January 25, 2018
 */

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="BHA TeleOp (FINAL)", group="BHA")
public class BHA_TeleOp_Trigger extends OpMode {

    /* Declare OpMode members. */
    HardwareBHA_Combine robot = new HardwareBHA_Combine(); //define a robot object from HardwareBHA_Test class

    double LServoOpen = 0.37;
    double RServoOpen = 0.65;

    double LServoClose = 0.58;
    double RServoClose = 0.45;

    double MAX_LIFT_POWER = 0.35;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        robot.init(hardwareMap);

        // Send telemetry message to signify that the robot is ready:
        telemetry.addData("Say", "HardwareBHA Initialized.");

        //Initially close the grabber
        robot.glimpseLServo.setPosition(LServoClose);
        robot.glimpseRServo.setPosition(RServoClose);

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
    public void loop()
    {
        double left;
        double right;
        double liftPower;

        /* GAMEPAD #1 TELEOP CONTROLS: DRIVER*/

        // The joystick goes negative when pushed forwards, so negate it
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);


        /* GAMEPAD #2 TELEOP CONTROLS: GRAB & LIFT*/

        // Gamepad 2 Trigger: LIFT WITH % UP TO MAX_LIFT_POWER
        liftPower = gamepad2.left_trigger * MAX_LIFT_POWER;

        if (liftPower != 0){
            robot.ballLift.setPower(liftPower);
        }
        else{
            liftPower = gamepad2.right_trigger * MAX_LIFT_POWER;
            robot.ballLift.setPower(-liftPower);
        }

        // BUTTON A: OPEN GRABBER
        if (gamepad2.a) {
            robot.glimpseLServo.setPosition(LServoOpen);
            robot.glimpseRServo.setPosition(RServoOpen);
        }

        // BUTTON B: CLOSE GRABBER
        if (gamepad2.b){
            robot.glimpseLServo.setPosition(LServoClose);
            robot.glimpseRServo.setPosition(RServoClose);
        }

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        robot.glimpseLServo.setPosition(LServoClose);
        robot.glimpseRServo.setPosition(RServoClose);

        robot.rightMotor.setPower(0);
        robot.leftMotor.setPower(0);

        robot.ballLift.setPower(0);
    }
}






