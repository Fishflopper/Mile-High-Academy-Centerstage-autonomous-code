package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous(name = "RedAutoLeft", group = "Autonomous")
public class RedAutoLeft extends LinearOpMode {

    // I moved these up here so that we can use them in the methods without passing them around.
    private Claw claw;
    private Lift lift;
    private Dist dist;
    private MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        // instantiate your MecanumDrive at a particular pose.
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        // make a Claw instance
        claw = new Claw(hardwareMap);
        // make a Lift instance
        lift = new Lift(hardwareMap);
        dist = new Dist(hardwareMap);

        waitForStart();
        moveToBlocks();

        String blockPos = dist.getMinSensor();
        if (blockPos.equals("front")) {
            placeFrontPixel();
            telemetry.addData(dist.getMinSensor(), 0);
            telemetry.update();
        }
        else if (blockPos.equals("left")) {
            placeLeftPixel();
            telemetry.addData(dist.getMinSensor(), 0);
            telemetry.update();
        }
        else if (blockPos.equals("right")) {
            placeRightPixel();
            telemetry.addData(dist.getMinSensor(), 0);
            telemetry.update();
        }

    }

    private void moveToBlocks() {
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToX(20)
                        .build()
        );

        dist.startScanning();
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToX(27)
                        .build()
        );
        telemetry.addData("front dist", dist.getFrontSensor());
        telemetry.update();
        if (dist.getFrontSensor() > 20) {
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .lineToX(32)
                            .build()
            );
        }
        dist.stopScanning();

    }

    private void placeFrontPixel() {
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToX(17)
                        .build()
        );
        claw.shoveClaw();
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToX(30)
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToX(24)
                        .build()
        );

        claw.lowerClaw();
        sleep(200);
        claw.openRight();
        sleep(300);
        claw.raiseClaw();
        sleep(300);

        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToX(5)
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .turnTo(-1.48)
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(5,-75))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(30.5, -75))
                        .build()
        );
        lift.liftUp(700);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(30.5, -81))
                        .build()
        );
        claw.openLeft();
        sleep(300);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(43, -75))
                        .build()
        );
        lift.liftDown();
        sleep(300);

    }

    private void placeLeftPixel() {
        claw.shoveClaw();
        sleep(200);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .turnTo(1.48)
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(29, -3.5))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(32, -3.5))
                        .build()
        );
        claw.lowerClaw();
        sleep(200);
        claw.openRight();
        sleep(300);
        claw.raiseClawer();
        sleep(400);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(16, 8))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(16, 10))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(16, 5))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(5, 0))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .turnTo(1.48)
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(5, -75))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .turnTo(-1.48)
                        .build()
        );
        lift.liftUp(1000);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(31, -75))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(31, -81))
                        .build()
        );
        claw.openLeft();
        sleep(300);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(43, -75))
                        .build()
        );
        lift.liftDown();



    }

    private void placeRightPixel() {
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(32, 10))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .turnTo(-1.48)
                        .build()
        );
        claw.shoveClaw();
        sleep(200);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(32, 3))
                        .build()
        );
        claw.lowerClaw();
        sleep(200);
        claw.openRight();
        sleep(100);
        claw.raiseClaw();
        sleep(200);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(32, 10))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .turnTo(-1.48)
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(5, 0))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(5, -75))
                        .build()
        );
        lift.liftUp(1000);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(26, -75))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(24.5, -81))
                        .build()
        );
        claw.openLeft();
        sleep(300);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(42, -78))
                        .build()
        );
        lift.liftDown();

    }




}



