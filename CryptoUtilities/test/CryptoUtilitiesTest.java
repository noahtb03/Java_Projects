import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Put your name here
 *
 */
public class CryptoUtilitiesTest {

    //Tests of reduceToGCD

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_8_0() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(8);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_1235812097_379() {
        NaturalNumber n = new NaturalNumber2(1235812097);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(379);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_1591_444() {
        NaturalNumber n = new NaturalNumber2(1591);
        NaturalNumber nExpected = new NaturalNumber2(37);
        NaturalNumber m = new NaturalNumber2(444);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_101286528_450583296() {
        NaturalNumber n = new NaturalNumber2(101286528);
        NaturalNumber nExpected = new NaturalNumber2(384);
        NaturalNumber m = new NaturalNumber2(450583296);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_1233122133() {
        NaturalNumber n = new NaturalNumber2(1233122133);
        NaturalNumber nExpected = new NaturalNumber2(1233122133);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_1248102138() {
        NaturalNumber n = new NaturalNumber2(1248102138);
        NaturalNumber nExpected = new NaturalNumber2(1248102138);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    //Tests of powerMod

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_5_7_13() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(8);
        NaturalNumber p = new NaturalNumber2(7);
        NaturalNumber pExpected = new NaturalNumber2(7);
        NaturalNumber m = new NaturalNumber2(13);
        NaturalNumber mExpected = new NaturalNumber2(13);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_10_0_9() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(9);
        NaturalNumber mExpected = new NaturalNumber2(9);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_12_1_12() {
        NaturalNumber n = new NaturalNumber2(12);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(1);
        NaturalNumber pExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(12);
        NaturalNumber mExpected = new NaturalNumber2(12);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_8_2_3() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(2);
        NaturalNumber pExpected = new NaturalNumber2(2);
        NaturalNumber m = new NaturalNumber2(3);
        NaturalNumber mExpected = new NaturalNumber2(3);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_999_888_1000() {
        NaturalNumber n = new NaturalNumber2(999);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(888);
        NaturalNumber pExpected = new NaturalNumber2(888);
        NaturalNumber m = new NaturalNumber2(1000);
        NaturalNumber mExpected = new NaturalNumber2(1000);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_999_888_650() {
        NaturalNumber n = new NaturalNumber2(999);
        NaturalNumber nExpected = new NaturalNumber2(52001);
        NaturalNumber p = new NaturalNumber2(888);
        NaturalNumber pExpected = new NaturalNumber2(888);
        NaturalNumber m = new NaturalNumber2(65000);
        NaturalNumber mExpected = new NaturalNumber2(65000);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Test isWitnessToCompositeness
     */
    @Test
    public void testisWitnessToCompositeness_260_896() {
        NaturalNumber w = new NaturalNumber2(260);
        NaturalNumber wExpected = new NaturalNumber2(260);
        NaturalNumber n = new NaturalNumber2(896);
        NaturalNumber nExpected = new NaturalNumber2(896);
        boolean isWitness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(isWitness, true);
    }

    @Test
    public void testisWitnessToCompositeness_2_4() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber wExpected = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        boolean isWitness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(isWitness, true);
    }

    @Test
    public void testisWitnessToCompositeness_2_53() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber wExpected = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(53);
        NaturalNumber nExpected = new NaturalNumber2(53);
        boolean isWitness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(isWitness, false);
    }

    @Test
    public void testisWitnessToCompositeness_123810_139719() {
        NaturalNumber w = new NaturalNumber2(123810);
        NaturalNumber wExpected = new NaturalNumber2(123810);
        NaturalNumber n = new NaturalNumber2(139719);
        NaturalNumber nExpected = new NaturalNumber2(139719);
        boolean isWitness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(isWitness, true);
    }

    @Test
    public void testisWitnessToCompositeness_8_6379() {
        NaturalNumber w = new NaturalNumber2(8);
        NaturalNumber wExpected = new NaturalNumber2(8);
        NaturalNumber n = new NaturalNumber2(6379);
        NaturalNumber nExpected = new NaturalNumber2(6379);
        boolean isWitness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(isWitness, false);
    }

    /*
     * test isPrime2
     */

    @Test
    public void testisPrime2_37() {
        NaturalNumber n = new NaturalNumber2(37);
        NaturalNumber nExpected = new NaturalNumber2(37);
        boolean checker = CryptoUtilities.isPrime2(n);
        assertEquals(checker, true);
        assertEquals(nExpected, n);

    }

    @Test
    public void testisPrime2_121() {
        NaturalNumber n = new NaturalNumber2(121);
        NaturalNumber nExpected = new NaturalNumber2(121);
        boolean checker = CryptoUtilities.isPrime2(n);
        assertEquals(checker, false);
        assertEquals(nExpected, n);

    }

    @Test
    public void testisPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean checker = CryptoUtilities.isPrime2(n);
        assertEquals(checker, true);
        assertEquals(nExpected, n);

    }

    @Test
    public void testisPrime2_7577() {
        NaturalNumber n = new NaturalNumber2(7577);
        NaturalNumber nExpected = new NaturalNumber2(7577);
        boolean checker = CryptoUtilities.isPrime2(n);
        assertEquals(checker, true);
        assertEquals(nExpected, n);

    }

    @Test
    public void testisPrime2_3435() {
        NaturalNumber n = new NaturalNumber2(3435);
        NaturalNumber nExpected = new NaturalNumber2(3435);
        boolean checker = CryptoUtilities.isPrime2(n);
        assertEquals(checker, false);
        assertEquals(nExpected, n);

    }

    @Test
    public void testgenerateNextLikelyPrime_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testgenerateNextLikelyPrime_8() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(11);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testgenerateNextLikelyPrime_14() {
        NaturalNumber n = new NaturalNumber2(14);
        NaturalNumber nExpected = new NaturalNumber2(17);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testgenerateNextLikelyPrime_370262() {
        NaturalNumber n = new NaturalNumber2(370262);
        NaturalNumber nExpected = new NaturalNumber2(370373);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testgenerateNextLikelyPrime_6377() {
        NaturalNumber n = new NaturalNumber2(6377);
        NaturalNumber nExpected = new NaturalNumber2(6379);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

}