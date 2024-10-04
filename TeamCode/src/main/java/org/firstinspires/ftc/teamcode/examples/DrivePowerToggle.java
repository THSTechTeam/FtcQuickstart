package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "Drive Power Toggle")
public class DrivePowerToggle extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotorSimple leftFront  = hardwareMap.get(DcMotorSimple.class, "leftFront");
        DcMotorSimple leftBack   = hardwareMap.get(DcMotorSimple.class, "leftBack");
        DcMotorSimple rightFront = hardwareMap.get(DcMotorSimple.class, "rightFront");
        DcMotorSimple rightBack  = hardwareMap.get(DcMotorSimple.class, "rightBack");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        Deadline gamepadRateLimit = new Deadline(500, TimeUnit.MILLISECONDS);
        double lowPower = 0.6;
        double highPower = 0.8;
        double currentPower = lowPower;

        waitForStart();

        while (opModeIsActive()) {
            double lx = gamepad1.left_stick_x;
            double ly = -gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            double max = Math.max(Math.abs(lx) + Math.abs(ly) + Math.abs(rx), 1);

            if (gamepadRateLimit.hasExpired() && gamepad1.a) {
                if (currentPower == lowPower) {
                    currentPower = highPower;
                } else {
                    currentPower = lowPower;
                }

                gamepadRateLimit.reset();
            }

            leftFront.setPower(((ly + lx + rx) / max) * currentPower);
            leftBack.setPower(((ly - lx + rx) / max) * currentPower);
            rightFront.setPower(((ly - lx - rx) / max) * currentPower);
            rightBack.setPower(((ly + lx - rx) / max) * currentPower);
        }
    }
}
