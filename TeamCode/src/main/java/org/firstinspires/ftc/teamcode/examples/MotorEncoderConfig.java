package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// 1. Find and set our desired zero position.
// 2. Find all desired preset motor positions.
// 3. Establish a control scheme.
@TeleOp(name = "Motor Encoder Config")
public class MotorEncoderConfig extends LinearOpMode {
    @Override
    public void runOpMode() {
        int ARM_POSITION_ZERO = 0;
        int ARM_MID_POSITION = 100;
        int ARM_HIGH_POSITION = 200;
        int ARM_BACK_POSITION = 300;

        DcMotor test = hardwareMap.get(DcMotor.class, "test");
        test.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        test.setPower(0.2);
        
        waitForStart();

        while (opModeIsActive() && !gamepad1.a) {
            if (gamepad2.a) {
                test.setTargetPosition(ARM_POSITION_ZERO);
            } else if (gamepad2.b) {
                test.setTargetPosition(ARM_MID_POSITION);
            } else if (gamepad2.x) {
                test.setTargetPosition(ARM_HIGH_POSITION);
            } else if (gamepad2.y) {
                test.setTargetPosition(ARM_BACK_POSITION);
            }

            telemetry.clearAll();
            telemetry.addData("Current motor power: ", test.getPower());
            telemetry.addData("Current target position: ", test.getTargetPosition());
            telemetry.addData("Current motor position: ", test.getCurrentPosition());
            telemetry.update();
        }
    }
}
