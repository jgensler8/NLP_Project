package com.genslerj.SemanticAttachment;

import org.junit.Test;

/**
 * Created by genslerj on 5/2/16.
 */
public class PPHelperTest {
    @Test
    public void isYeraShouldReturnFalseWhenGivenABadString() {
        boolean result = PPHelper.isYear("asdf");
        assert(result == false);
    }

    @Test
    public void isYearShouldReturnTrueWhenGivenAYear() {
        boolean result = PPHelper.isYear("2000");
        assert(result == true);
    }

    @Test
    public void isOscarTypeShouldReturnFalseWhenGivenGarbage() {
        boolean result = PPHelper.isOscarType("ASDF");
        assert(result == false);
    }

    @Test
    public void isOscarTypeShouldReturnTrueWhenGivenBestActor() {
        boolean result = PPHelper.isOscarType("Best Actor");
        assert(result == true);
    }

    @Test
    public void isOscarTypeShouldReturnTrueWhenGivenBestActress() {
        boolean result = PPHelper.isOscarType("Best Actress");
        assert(result == true);
    }

    @Test
    public void isOscarTypeShouldReturnTrueWhenGivenBestPicture() {
        boolean result = PPHelper.isOscarType("Best Picture");
        assert(result == true);
    }

    @Test
    public void isOscarTypeShouldReturnTrueWhenGivenBestSupportingActor() {
        boolean result = PPHelper.isOscarType("Best Supporting Actor");
        assert(result == true);
    }

    @Test
    public void isOscarTypeShouldReturnTrueWhenGivenBestSupportingActress() {
        boolean result = PPHelper.isOscarType("Best Supporting Actress");
        assert(result == true);
    }

    @Test
    public void isOscarTypeShouldReturnTrueWhenGivenBestDirector() {
        boolean result = PPHelper.isOscarType("Best Director");
        assert(result == true);
    }
}