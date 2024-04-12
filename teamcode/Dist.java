package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Dist {
    private DistanceSensor frontDist;
    private DistanceSensor leftDist;
    private DistanceSensor rightDist;
    private double frontMin = 999999;
    private double leftMin = 999999;
    private double rightMin = 999999;
    private Thread pollingThread;
    private boolean scanning = false;




    public Dist(HardwareMap hardwareMap) {
        frontDist = hardwareMap.get(DistanceSensor.class, "frontDist");
        leftDist = hardwareMap.get(DistanceSensor.class, "leftDist");
        rightDist = hardwareMap.get(DistanceSensor.class, "rightDist");
    }

    public void startScanning() {
        scanning = true;
        Thread distanceSensorThread = new Thread(() -> {
            while (!Thread.interrupted() && scanning) {
                frontMin = Math.min(frontMin, frontDist.getDistance(DistanceUnit.CM));
                leftMin = Math.min(leftMin, leftDist.getDistance(DistanceUnit.CM));
                rightMin = Math.min(rightMin, rightDist.getDistance(DistanceUnit.CM));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        distanceSensorThread.start();


    }

    public void stopScanning() {
        scanning = false;
    }

    public String getMinSensor() {

        if (frontMin < rightMin && frontMin < leftMin) {
            return "front";
        }
        if (leftMin < rightMin && leftMin < frontMin) {
            return "left";
        }
        if (rightMin < frontMin && rightMin < leftMin) {
            return "right";
        }
        return "failure";
    }

    public double getFrontSensor() {
        return frontMin;
    }


}
