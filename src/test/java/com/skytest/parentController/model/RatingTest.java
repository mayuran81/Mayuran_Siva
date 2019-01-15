package com.skytest.parentController.model;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.skytest.parentController.Exception.TechnicalFailureException;
import com.skytest.parentController.Model.Rating;

@RunWith(MockitoJUnitRunner.class)
public class RatingTest {
		
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Test
	public void test_validStringResolvedTo_validRating() throws TechnicalFailureException {
		
		Rating.getRatingEnumFromString("U").equals(Rating.U);
		Rating.getRatingEnumFromString("PG").equals(Rating.PG);
		Rating.getRatingEnumFromString("12").equals(Rating.TWELVE);
		Rating.getRatingEnumFromString("15").equals(Rating.FIFTEEN);
		Rating.getRatingEnumFromString("18").equals(Rating.EIGHTEEN);
	}
	
	@Test
	public void test_inputRatingGreatherRequireRating_true() {
		
		assertTrue(Rating.isInputRatingGreaterThanRequiredRating(Rating.EIGHTEEN, Rating.PG)== Boolean.TRUE);
		assertTrue(Rating.isInputRatingGreaterThanRequiredRating(Rating.EIGHTEEN, Rating.U)== Boolean.TRUE);
		assertTrue(Rating.isInputRatingGreaterThanRequiredRating(Rating.EIGHTEEN, Rating.FIFTEEN)== Boolean.TRUE);
		assertTrue(Rating.isInputRatingGreaterThanRequiredRating(Rating.EIGHTEEN, Rating.TWELVE)== Boolean.TRUE);
		assertTrue(Rating.isInputRatingGreaterThanRequiredRating(Rating.EIGHTEEN, Rating.PG)== Boolean.TRUE);
		
		
	}
	
	@Test
	public void test_inputRatingLessThanRequireRating_false() {
		
		assertTrue(Rating.isInputRatingGreaterThanRequiredRating(Rating.U, Rating.PG)== Boolean.FALSE);
		assertTrue(Rating.isInputRatingGreaterThanRequiredRating(Rating.U, Rating.TWELVE)== Boolean.FALSE);
		assertTrue(Rating.isInputRatingGreaterThanRequiredRating(Rating.U, Rating.FIFTEEN)== Boolean.FALSE);
		assertTrue(Rating.isInputRatingGreaterThanRequiredRating(Rating.U, Rating.EIGHTEEN)== Boolean.FALSE);
		
		
	}
	
	@Test
	public void test_invalidInputRatingString_Exception() throws TechnicalFailureException {
		
		expectedException.expect(TechnicalFailureException.class);
		System.out.println(Rating.EIGHTEEN.toString());
		
		Rating.getRatingEnumFromString("invalidEnumString");
		
		
	}
	
	public void test_toString_ReturnRatingName() {
		
		assertTrue(Rating.EIGHTEEN.toString().equals("18"));
		assertTrue(Rating.FIFTEEN.toString().equals("15"));
		assertTrue(Rating.TWELVE.toString().equals("12"));
		assertTrue(Rating.PG.toString().equals("PG"));
		assertTrue(Rating.U.toString().equals("U"));
	}
}
