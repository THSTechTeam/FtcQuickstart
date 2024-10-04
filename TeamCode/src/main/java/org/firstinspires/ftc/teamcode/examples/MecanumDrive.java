package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Basic Mecanum Drive")
public class MecanumDrive extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotorSimple leftFront = hardwareMap.get(DcMotorSimple.class, "leftFront");
        DcMotorSimple leftBack = hardwareMap.get(DcMotorSimple.class, "leftBack");
        DcMotorSimple rightFront = hardwareMap.get(DcMotorSimple.class, "rightFront");
        DcMotorSimple rightBack = hardwareMap.get(DcMotorSimple.class, "rightBack");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            double lx = gamepad1.left_stick_x;
            double ly = -gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            double max = Math.max(Math.abs(lx) + Math.abs(ly) + Math.abs(rx), 1);

            leftFront.setPower((ly + lx + rx) / max);
            leftBack.setPower((ly - lx + rx) / max);
            rightFront.setPower((ly - lx - rx) / max);
            rightBack.setPower((ly + lx - rx) / max);
        }
    }
}
