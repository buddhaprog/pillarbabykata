/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babysitterkata;

import java.util.Scanner;

/**
 *
 * @author Rob
 */
public class BabySitter {
//start time is stated as 5pm or later so a "2" will be considered 2am and the value will be 14, a value of 0 will be considered midnight.
    //input cannot be greater than 12.

    /**
     * @param args the command line arguments
     */
    int earliestStartTime;
    int maxEndTime;
    int startToBedRate;
    int bedToMidnightRate;
    int midnightToLateRate;
    int userBedTimeInput;
    int userStartInput;
    int userEndInput;

    public BabySitter() {
        this.initBabySitterData();
    }

    private void initBabySitterData() {
        //this method would read different babysitter files and assign values, but here,
        //just going to hard code them.
        this.earliestStartTime = 5;
        this.maxEndTime = 16;
        this.startToBedRate = 12;
        this.bedToMidnightRate = 8;
        this.midnightToLateRate = 16;

    }

    public int babySitterPayCalulator(int userStartInput, int userBedTimeInput, int userEndInput) {
        // System.out.println("entering paycalc start: " + userStartInput + " bed: " + userBedTimeInput + " end: " + userEndInput);
        int startToBedPay;
        int bedToMidnightPay;
        int midnightToEndPay;
        boolean validCheck = false;
        validCheck = this.isValidTime(userStartInput);

        startToBedPay = this.startToBedDurationConverter(userStartInput, userBedTimeInput, userEndInput) * this.startToBedRate;
        bedToMidnightPay = this.bedToMidnightDurationConverter(userStartInput, userBedTimeInput, userEndInput) * this.bedToMidnightRate;
        midnightToEndPay = this.midnightToEndDurationConverter(userStartInput, userBedTimeInput, userEndInput) * this.midnightToLateRate;
        //System.out.println("dur1: " + this.startToBedDurationConverter(userStartInput, userBedTimeInput, userEndInput) + " dur2:" + this.bedToMidnightDurationConverter(userStartInput, userBedTimeInput, userEndInput) + " dur3: " + this.midnightToEndDurationConverter(userStartInput, userBedTimeInput, userEndInput));

        int totalPay = startToBedPay + bedToMidnightPay + midnightToEndPay;
        //System.out.println("paycalc returning " + totalPay);
        return totalPay;
    }

    public int startToBedDurationConverter(int startTime, int bedTime, int endTime) {
        //takes times and returns payrate duration time period for start to bed
        if (startTime >= bedTime) {
            //System.out.println("s2b returning " + "0" + "on path 1");
            return 0;
        }
        if (startTime >= 12) {
            //System.out.println("s2b returning 0 on path 2");
            return 0;
        }
        //eg. if start at 5 and end at 11 but bedtime is 12 then p1 hours are 6
        if (endTime <= bedTime) {
            if (endTime >= 12) {
                //System.out.println("s2b returning " + 12 - startTime + " on path 3");
                return 12 - startTime;
            } else {
                // System.out.println("s2b returning " + endTime-startTime + "on path 4");
                return (endTime - startTime);
            }
        } else {
            if (bedTime >= 12) {
                // System.out.println("s2b returning " + 12- startTime+ "on path 5");
                return 12 - startTime;
            } else {
                //  System.out.println("s2b returning " + bedTime-startTime + " on path 6");
                return bedTime - startTime;
            }
        }
    }

    public int bedToMidnightDurationConverter(int startTime, int bedTime, int endTime) {
        //takes times and returns payrate duration time period for bed to midnight
        if (startTime > bedTime
                || bedTime >= 12
                || startTime >= 12
                || endTime <= bedTime) {
            //System.out.println("b2m returning 0 on path 1");
            return 0;
        }
        if (endTime <= 12) {
            // System.out.println("b2m returning" + endTime-bedTime + "on path 2");
            return endTime - bedTime;
        }
        if (bedTime <= 12) {
            // System.out.println("b2m returning " + 12-bedTime + "on path 3");
            return 12 - bedTime;
        }
        // System.out.println("b2m returning 0 because it skipped everything on all paths");
        return 0;
    }

    public int midnightToEndDurationConverter(int startTime, int bedTime, int endTime) {
        //takes times and returns payrate duration time period for after midnight
        //System.out.println("entering m2eduration start: " + startTime + " bed: " + bedTime + " end: " + endTime);
        if (endTime <= 12) {
            //System.out.println("m2e returning 0 on path 1");
            return 0;
        }
        if (startTime == endTime) {
//            System.out.println("m2e returning 0 on path 2");
            return 0;
        }
        if (endTime >= 12) {
            //System.out.println("m2e returning "+ endTime-12 +" on path 3");
            return endTime - 12;
        }
//        System.out.println("m2e returning crazy 5000 on path 4");
        return 5000;
    }

    public boolean isValidTime(int time) {
        //making sure time entered is within valid range
        //System.out.println("time check");
        return !(time <= 0 || time > 12);
    }

    public int timeAdjust(int time) {
//convert time to workable value by adding 12 to anything less than 4
//        System.out.println("entering TA");
        if (time <= 4) {
            time = time + 12;
        }
//        System.out.println("TA returning "+ time);
        return time;
    }

    public boolean isValidStartTime(int userStartInput) {
//        System.out.println("valid start time check");
        return !(userStartInput < this.earliestStartTime || userStartInput >= this.maxEndTime);
    }

    public boolean isValidBedTime(int userBedTimeInput) {
//        System.out.println("valid bed time check");
        return !(userBedTimeInput < this.earliestStartTime || userBedTimeInput > this.maxEndTime);
    }

    public boolean isValidEndTime(int userEndInput) {
//        System.out.println("valid end time check");
        return !(userEndInput <= this.earliestStartTime || userEndInput > this.maxEndTime);
    }

    public static void main(String[] args) {
        BabySitter baby = new BabySitter();
        //tried doing it with 0 libraries per spec, could not figure out how to do it without atleast using scanner
        Scanner sc = new Scanner(System.in);
        boolean isValidEnd = false;
        boolean isValidStart = false;
        boolean isValidBed = false;
        System.out.println("Welcome to Your babysitter pay calculator!");

        while (!isValidStart) {
            System.out.println("Please Enter a Start Time between 5pm-3am");

            while (!sc.hasNextInt()) {
                String input = sc.next();
                System.out.println("Sorry " + input + " is not a valid time, please try again");
            }
            baby.userStartInput = sc.nextInt();
            //System.out.println(baby.userStartInput);
            isValidStart = baby.isValidTime(baby.userStartInput);
            //System.out.println("got here");
            if (isValidStart) {
                baby.userStartInput = baby.timeAdjust(baby.userStartInput);
                isValidStart = baby.isValidStartTime(baby.userStartInput);
                //System.out.println(baby.userStartInput);
            }
        }
        System.out.println("Your Start time value is " + baby.userStartInput);

        while (!isValidBed) {
            System.out.println("Please Enter a bed Time between 5pm-4am");
            while (!sc.hasNextInt()) {
                String input = sc.next();
                System.out.println("Sorry " + input + " is not a valid time, please try again");
            }
            baby.userBedTimeInput = sc.nextInt();
            isValidBed = baby.isValidTime(baby.userBedTimeInput);
            if (isValidBed) {
                baby.userBedTimeInput = baby.timeAdjust(baby.userBedTimeInput);
                isValidBed = baby.isValidBedTime(baby.userBedTimeInput);
            }
        }
        System.out.println("Your bed time value is " + baby.userBedTimeInput);

        while (!isValidEnd) {
            System.out.println("Please Enter a end Time between 6pm-4am");

            while (!sc.hasNextInt()) {
                String input = sc.next();
                System.out.println("Sorry " + input + " is not a valid time, please try again");
            }
            baby.userEndInput = sc.nextInt();
            isValidEnd = baby.isValidTime(baby.userEndInput);
            if (isValidEnd) {
                System.out.println("here");
                baby.userEndInput = baby.timeAdjust(baby.userEndInput);
                isValidEnd = baby.isValidEndTime(baby.userEndInput);
            }
        }
        System.out.println("Your End time value is " + baby.userEndInput);
        int pay = baby.babySitterPayCalulator(baby.userStartInput, baby.userBedTimeInput, baby.userEndInput);
        System.out.println("Your Total Pay for the job is: " + pay);
    }
}
