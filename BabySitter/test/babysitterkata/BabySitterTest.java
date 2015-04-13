/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babysitterkata;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rob
 */
public class BabySitterTest {

    public BabySitterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    // The Urge to DDT TDD is great.
    // @Test
    // public void hello() {}
    @Test
    public void isValidStartTimeTest() {
        BabySitter baby = new BabySitter();

        assertEquals(false, baby.isValidStartTime(-10)); //way outside
        assertEquals(false, baby.isValidStartTime(0));  // it's zero
        assertEquals(false, baby.isValidStartTime(4)); //just outside
        assertEquals(true, baby.isValidStartTime(5)); //boundary
        assertEquals(true, baby.isValidStartTime(6)); //just inside
        assertEquals(true, baby.isValidStartTime(10)); //middle type value
        assertEquals(true, baby.isValidStartTime(12)); //cause it's midnight, it's special
        assertEquals(true, baby.isValidStartTime(15)); //last hour available to start
        assertEquals(false, baby.isValidStartTime(16)); //boundary
        assertEquals(false, baby.isValidStartTime(17)); //just outside
        assertEquals(false, baby.isValidStartTime(2015)); //waaaay outside

    }

    @Test
    public void isValidBedTimeTest() {
        BabySitter baby = new BabySitter();
        assertEquals(false, baby.isValidBedTime(-10)); //way outside
        assertEquals(false, baby.isValidBedTime(0));  // it's zero
        assertEquals(false, baby.isValidBedTime(4)); //just outside
        assertEquals(true, baby.isValidBedTime(5)); //boundary
        assertEquals(true, baby.isValidBedTime(6)); //just inside
        assertEquals(true, baby.isValidBedTime(10)); //middle type value
        assertEquals(true, baby.isValidBedTime(12)); //cause it's midnight, it's special
        assertEquals(true, baby.isValidBedTime(15)); //just inside
        assertEquals(true, baby.isValidBedTime(16)); //boundary
        assertEquals(false, baby.isValidBedTime(17)); //just outside
        assertEquals(false, baby.isValidBedTime(2015)); //waaaay outside

    }

    @Test
    public void isValidEndTimeTest() {
        BabySitter baby = new BabySitter();
        assertEquals(false, baby.isValidEndTime(-10)); //way outside
        assertEquals(false, baby.isValidEndTime(0));  // it's zero
        assertEquals(false, baby.isValidEndTime(4)); //just outside
        assertEquals(false, baby.isValidEndTime(5)); //boundary
        assertEquals(true, baby.isValidEndTime(6)); //just inside
        assertEquals(true, baby.isValidEndTime(10)); //middle type value
        assertEquals(true, baby.isValidEndTime(12)); //cause it's midnight, it's special
        assertEquals(true, baby.isValidEndTime(15)); //just inside
        assertEquals(true, baby.isValidEndTime(16)); //boundary
        assertEquals(false, baby.isValidEndTime(17)); //just outside
        assertEquals(false, baby.isValidEndTime(2015)); //waaaay outside
    }

    @Test
    public void isValidTimeTest() {
        BabySitter baby = new BabySitter();
        assertEquals(false, baby.isValidTime(-10)); //way outside
        assertEquals(false, baby.isValidTime(0));  // it's zero
        assertEquals(false, baby.isValidTime(-1)); //just outside
        assertEquals(true, baby.isValidTime(1)); //boundary
        assertEquals(true, baby.isValidTime(2)); //just inside
        assertEquals(true, baby.isValidTime(10)); //middle type value
        assertEquals(true, baby.isValidTime(11)); //just inside
        assertEquals(true, baby.isValidTime(12)); //boundary
        assertEquals(false, baby.isValidTime(13)); //just outside
        assertEquals(false, baby.isValidTime(2015)); //waaaay outside

    }

    @Test
    public void timeAdjustTest() {
        BabySitter baby = new BabySitter();
        assertEquals(13, baby.timeAdjust(1));
        assertEquals(14, baby.timeAdjust(2));
        assertEquals(16, baby.timeAdjust(4));
        assertEquals(8, baby.timeAdjust(8));
        assertEquals(5, baby.timeAdjust(5));
        assertEquals(12, baby.timeAdjust(12));
        assertEquals(-32, baby.timeAdjust(-44));
    }

    @Test
    public void startToBedDurationConverterTest() {
        BabySitter baby = new BabySitter();

        assertEquals(6, baby.startToBedDurationConverter(5, 12, 11)); //bed after end
        assertEquals(1, baby.startToBedDurationConverter(5, 6, 6)); //double bed and end
        assertEquals(7, baby.startToBedDurationConverter(5, 12, 16)); //range
        assertEquals(0, baby.startToBedDurationConverter(12, 13, 16)); //more testing
        assertEquals(4, baby.startToBedDurationConverter(8, 12, 12)); //lots of 12
        assertEquals(0, baby.startToBedDurationConverter(13, 8, 13)); //start and end past 12
        assertEquals(0, baby.startToBedDurationConverter(5, 12, 5)); //same start and end
        assertEquals(1, baby.startToBedDurationConverter(7, 16, 8)); //bed after both
        assertEquals(1, baby.startToBedDurationConverter(5, 6, 12)); //more variety
        assertEquals(2, baby.startToBedDurationConverter(5, 7, 11)); //more variety
        assertEquals(2, baby.startToBedDurationConverter(8, 10, 13)); //more variety
        assertEquals(2, baby.startToBedDurationConverter(9, 11, 14)); //more variety
        assertEquals(0, baby.startToBedDurationConverter(5, 5, 6)); //double bed and end
        assertEquals(7, baby.startToBedDurationConverter(5, 16, 16)); //double bed and end

    }

    @Test
    public void bedToMidnightDurationConverterTest() {
        BabySitter baby = new BabySitter();
        assertEquals(0, baby.bedToMidnightDurationConverter(5, 12, 11)); //bed after end
        assertEquals(0, baby.bedToMidnightDurationConverter(5, 6, 6)); //double bed and end
        assertEquals(0, baby.bedToMidnightDurationConverter(5, 12, 16)); //range
        assertEquals(0, baby.bedToMidnightDurationConverter(12, 13, 16)); //more testing
        assertEquals(0, baby.bedToMidnightDurationConverter(8, 12, 12)); //lots of 12
        assertEquals(0, baby.bedToMidnightDurationConverter(13, 8, 13)); //start and end past 12
        assertEquals(0, baby.bedToMidnightDurationConverter(5, 12, 5)); //same start and end
        assertEquals(0, baby.bedToMidnightDurationConverter(7, 16, 8)); //more variety
        assertEquals(2, baby.bedToMidnightDurationConverter(5, 6, 8)); //more variety
        assertEquals(6, baby.bedToMidnightDurationConverter(5, 6, 12)); //more variety
        assertEquals(4, baby.bedToMidnightDurationConverter(5, 7, 11)); //more variety
        assertEquals(2, baby.bedToMidnightDurationConverter(8, 10, 13)); //more variety
        assertEquals(1, baby.bedToMidnightDurationConverter(9, 11, 14)); //more variety
        assertEquals(1, baby.bedToMidnightDurationConverter(5, 5, 6)); //double bed and end
        assertEquals(0, baby.bedToMidnightDurationConverter(5, 16, 16)); //double bed and end

    }

    @Test
    public void midnightToEndDurationConverterTest() {
        BabySitter baby = new BabySitter();
        assertEquals(0, baby.midnightToEndDurationConverter(5, 12, 11)); //bed after end
        assertEquals(0, baby.midnightToEndDurationConverter(5, 6, 6)); //double bed and end
        assertEquals(4, baby.midnightToEndDurationConverter(5, 12, 16)); //range
        assertEquals(4, baby.midnightToEndDurationConverter(12, 13, 16)); //more testing
        assertEquals(0, baby.midnightToEndDurationConverter(8, 12, 12)); //lots of 12
        assertEquals(0, baby.midnightToEndDurationConverter(13, 8, 13)); //start and end past 12
        assertEquals(0, baby.midnightToEndDurationConverter(5, 12, 5)); //same start and end
        assertEquals(0, baby.midnightToEndDurationConverter(7, 16, 8)); //more variety
        assertEquals(0, baby.midnightToEndDurationConverter(5, 6, 8)); //more variety
        assertEquals(0, baby.midnightToEndDurationConverter(5, 6, 12)); //more variety
        assertEquals(0, baby.midnightToEndDurationConverter(5, 7, 11)); //more variety
        assertEquals(1, baby.midnightToEndDurationConverter(8, 10, 13)); //more variety
        assertEquals(2, baby.midnightToEndDurationConverter(9, 11, 14)); //more variety
        assertEquals(0, baby.midnightToEndDurationConverter(5, 5, 6)); //double bed and end
        assertEquals(4, baby.midnightToEndDurationConverter(5, 16, 16)); //double bed and end

    }

    @Test
    public void babySitterPayCalculatorTest() {
        BabySitter baby = new BabySitter();
        assertEquals(3 * baby.startToBedRate + 4 * baby.bedToMidnightRate + 2 * baby.midnightToLateRate, baby.babySitterPayCalulator(5, 8, 14));//first test
        assertEquals(0 * baby.startToBedRate + 1 * baby.bedToMidnightRate + 0 * baby.midnightToLateRate, baby.babySitterPayCalulator(5, 5, 6));//1 hour in start no bed time
        assertEquals(0 * baby.startToBedRate + 2 * baby.bedToMidnightRate + 0 * baby.midnightToLateRate, baby.babySitterPayCalulator(5, 5, 7));//2 hours starting at bed time
        assertEquals(2 * baby.startToBedRate + 4 * baby.bedToMidnightRate + 2 * baby.midnightToLateRate, baby.babySitterPayCalulator(6, 8, 14));//start  1 hour late 
        assertEquals(0 * baby.startToBedRate + 0 * baby.bedToMidnightRate + 1 * baby.midnightToLateRate, baby.babySitterPayCalulator(12, 12, 13));//start at midnight
        assertEquals(0 * baby.startToBedRate + 0 * baby.bedToMidnightRate + 4 * baby.midnightToLateRate, baby.babySitterPayCalulator(12, 13, 16));//late night sit
        assertEquals(7 * baby.startToBedRate + 0 * baby.bedToMidnightRate + 4 * baby.midnightToLateRate, baby.babySitterPayCalulator(5, 16, 16));//max pay night
        assertEquals(6 * baby.startToBedRate + 0 * baby.bedToMidnightRate + 0 * baby.midnightToLateRate, baby.babySitterPayCalulator(5, 12, 11));//max pay night
        assertEquals(1 * baby.startToBedRate + 0 * baby.bedToMidnightRate + 0 * baby.midnightToLateRate, baby.babySitterPayCalulator(5, 6, 6));//max pay night
        assertEquals(7 * baby.startToBedRate + 0 * baby.bedToMidnightRate + 4 * baby.midnightToLateRate, baby.babySitterPayCalulator(5, 12, 16));//max pay night
        assertEquals(0 * baby.startToBedRate + 0 * baby.bedToMidnightRate + 4 * baby.midnightToLateRate, baby.babySitterPayCalulator(12, 13, 16));//max pay night
        assertEquals(4 * baby.startToBedRate + 0 * baby.bedToMidnightRate + 0 * baby.midnightToLateRate, baby.babySitterPayCalulator(8, 12, 12));//max pay night
        assertEquals(0 * baby.startToBedRate + 0 * baby.bedToMidnightRate + 0 * baby.midnightToLateRate, baby.babySitterPayCalulator(13, 8, 13));//max pay night
        assertEquals(0 * baby.startToBedRate + 0 * baby.bedToMidnightRate + 0 * baby.midnightToLateRate, baby.babySitterPayCalulator(5, 12, 5));//max pay night
        assertEquals(1 * baby.startToBedRate + 0 * baby.bedToMidnightRate + 0 * baby.midnightToLateRate, baby.babySitterPayCalulator(7, 16, 8));//max pay night
        assertEquals(1 * baby.startToBedRate + 2 * baby.bedToMidnightRate + 0 * baby.midnightToLateRate, baby.babySitterPayCalulator(5, 6, 8));//max pay night
        assertEquals(1 * baby.startToBedRate + 6 * baby.bedToMidnightRate + 0 * baby.midnightToLateRate, baby.babySitterPayCalulator(5, 6, 12));//max pay night
        assertEquals(2 * baby.startToBedRate + 2 * baby.bedToMidnightRate + 1 * baby.midnightToLateRate, baby.babySitterPayCalulator(8, 10, 13));//max pay night
        assertEquals(2 * baby.startToBedRate + 1 * baby.bedToMidnightRate + 2 * baby.midnightToLateRate, baby.babySitterPayCalulator(9, 11, 14));//max pay night
        assertEquals(0 * baby.startToBedRate + 1 * baby.bedToMidnightRate + 0 * baby.midnightToLateRate, baby.babySitterPayCalulator(5, 5, 6));//max pay night

    }
}
