package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Claw {
    private Servo leftClaw;
    private Servo angler;
    private Servo rightClaw;

    public Claw(HardwareMap hardwareMap) {
        leftClaw = hardwareMap.get(Servo.class, "left claw");
        angler = hardwareMap.get(Servo.class, "angler");
        rightClaw = hardwareMap.get(Servo.class, "right claw");
        angler.setDirection(Servo.Direction.REVERSE);
        angler.setPosition(0.29);
        leftClaw.setPosition(0.2);
        rightClaw.setPosition(0.51);
    }

    public void openLeft() {
        leftClaw.setPosition(0.5);
    }

    public void openRight() {
        rightClaw.setPosition(0.2);
    }

    public void lowerClaw() {
        angler.setPosition(0.04);
    }

    public void shoveClaw() {
        angler.setPosition(0.09);
    }

    public void raiseClaw() {
        angler.setPosition(0.28);
    }
    public void raiseClawer() {
        angler.setPosition(0.3);
    };
}
