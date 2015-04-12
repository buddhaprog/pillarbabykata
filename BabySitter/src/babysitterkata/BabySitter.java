/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babysitterkata;

/**
 *
 * @author Rob
 */
public class BabySitter {

    /**
     * @param args the command line arguments
     */
    int earliestStartTime;
    int maxEndTime;
    int startToBedRate;
    int bedToMidnightRate;
    int midnightToLateRate;
    static int userBedTimeInput = 0;
    int bedTime = userBedTimeInput;
    static int userStartInput = 0;
    int startTime = userStartInput;
    static int userEndInput = 0;
    int endTime = userEndInput;
    int startToBedDuration;
    int bedToMidnightDuration;
    int midnightToEndDuration;

    public BabySitter() {
        this.initBabySitterData();
    }

    public int inputStartValidation(int userStartInput) {

        return this.startTime;
    }

    public int inputEndValidation(int userEndInput) {

        return this.endTime;
    }

    private void initBabySitterData() {
        //this method would read different babysitter files and assign values, but here,
        //just goin to hard code them.
        this.earliestStartTime = 5;
        this.maxEndTime = 16;
        this.startToBedRate = 12;
        this.bedToMidnightRate = 8;
        this.midnightToLateRate = 16;

    }

    public void babySitterPayCalulator(int startTime, int endTime) {
        
        
    }

    public static void main(String[] args) {
        // TODO code application logic here

    }
}
