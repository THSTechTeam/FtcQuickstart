package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Drive Power Toggle 2")
public class DrivePowerToggle2 extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotorSimple leftFront  = hardwareMap.get(DcMotorSimple.class, "leftFront");
        DcMotorSimple leftBack   = hardwareMap.get(DcMotorSimple.class, "leftBack");
        DcMotorSimple rightFront = hardwareMap.get(DcMotorSimple.class, "rightFront");
        DcMotorSimple rightBack  = hardwareMap.get(DcMotorSimple.class, "rightBack");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        Gamepad previous1 = new Gamepad();
        double lowPower = 0.6;
        double highPower = 0.8;
        double currentPower = lowPower;

        waitForStart();

        while (opModeIsActive()) {
            double lx = gamepad1.left_stick_x;
            double ly = -gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            double max = Math.max(Math.abs(lx) + Math.abs(ly) + Math.abs(rx), 1);

            if (gamepad1.a && !previous1.a) {
                if (currentPower == lowPower) {
                    currentPower = highPower;
                } else {
                    currentPower = lowPower;
                }
            }

            leftFront.setPower(((ly + lx + rx) / max) * currentPower);
            leftBack.setPower(((ly - lx + rx) / max) * currentPower);
            rightFront.setPower(((ly - lx - rx) / max) * currentPower);
            rightBack.setPower(((ly + lx - rx) / max) * currentPower);

            previous1.copy(gamepad1);
        }
    }
}
