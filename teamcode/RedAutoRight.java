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
@Autonomous(name = "RedAutoRight", group = "Autonomous")
public class RedAutoRight extends LinearOpMode {

    // I moved these up here so that we can use them in the methods without passing them around.
    private Claw claw;
    private Lift lift;
    private Dist dist;
    private MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
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
                        .lineToX(50)
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
                        .lineToX(25)
                        .build()
        );
        lift.liftUp(1500);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .turnTo(-1.48)
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(26.5, -38))
                        .build()
        );
        claw.openLeft();
        sleep(300);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(26.5, -34))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(43, -34))
                        .build()
        );
        lift.liftDown();
        sleep(300);
    }

    private void placeLeftPixel() {
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .turnTo(1.48)
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(30.5, -10))
                        .build()
        );
        claw.shoveClaw();
        sleep(200);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(30, 10))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(29, -2.5))
                        .build()
        );
        claw.lowerClaw();
        sleep(200);
        claw.openRight();
        sleep(300);
        claw.raiseClaw();
        sleep(200);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(32, -7))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .turnTo(-1.48)
                        .build()
        );
        lift.liftUp(1500);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(33, -39.5))
                        .build()
        );
        claw.openLeft();
        sleep(300);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(31, -35))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(45, -35))
                        .build()
        );
        lift.liftDown();
        sleep(600);
    }

    private void placeRightPixel() {
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToX(17)
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
                        .strafeTo(new Vector2d(17, 3.5))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(60, 3.5))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(28, 4))
                        .build()
        );
        claw.lowerClaw();
        sleep(200);
        claw.openRight();
        sleep(300);
        claw.raiseClaw();
        sleep(200);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(15, 0))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(20, -20))
                        .build()
        );
        lift.liftUp(1500);
        sleep(400);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(20, -39))
                        .build()
        );
        claw.openLeft();
        sleep(300);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(18, -35))
                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(new Vector2d(43, -35))
                        .build()
        );
        lift.liftDown();
        sleep(600);

    }




}

