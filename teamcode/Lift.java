package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {
    private DcMotorEx lift;
    private  DcMotorEx liftDown;

    public Lift(HardwareMap hardwareMap) {
        lift = hardwareMap.get(DcMotorEx.class, "lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftDown = hardwareMap.get(DcMotorEx.class, "lift down");
        liftDown.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftDown.setDirection(DcMotorSimple.Direction.FORWARD);
        liftDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void liftUp() {
        liftUp(5350);
    }

    public void liftUp(int ticks) {

        lift.setTargetPosition(-ticks);
        liftDown.setTargetPosition(ticks + 10);
        lift.setPower(0.9);
        liftDown.setPower(0.93);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void  liftDown () {
        liftDown(0);
    }

    public void liftDown(int target) {

        lift.setTargetPosition(target+20);
        liftDown.setTargetPosition(target);
        lift.setPower(1);
        liftDown.setPower(0.95);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

}