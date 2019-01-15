package com.skytest.parentController.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.skytest.parentController.Exception.TechnicalFailureException;
import com.skytest.parentController.Exception.TitleNotFounException;
import com.skytest.parentController.Model.Rating;
import com.skytest.parentController.service.impl.ParentalControlServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ParentControlServiceImplTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Mock
	private MovieService mockMovieService;

	private ParentalControlServiceImpl target;
	
	private final String uRatingMovie = "URatingMovie";
	private final String pgRatingMovie = "PG";
	private final String twelveRatingMovie = "12RatingMovie";
	private final String fifteenRatingMovie = "15RatingMovie";
	private final String eighteenRatingMovie = "18RatingMovie";
	private final String invalidMovieName = "invalidMovie";
	
	
	private final String InvalidRating = "invalidRating";

	@BeforeClass
	public static void setUpTestClass() {
		
		
	}

	@Before
	public void setUptest() throws TitleNotFounException, TechnicalFailureException {

		mockMovieService = Mockito.mock(MovieService.class);
		
		when(mockMovieService.getParentalControlLevel(uRatingMovie)).thenReturn("U");
		when(mockMovieService.getParentalControlLevel(pgRatingMovie)).thenReturn("PG");
		when(mockMovieService.getParentalControlLevel(twelveRatingMovie)).thenReturn("12");
		when(mockMovieService.getParentalControlLevel(fifteenRatingMovie)).thenReturn("15");
		when(mockMovieService.getParentalControlLevel(eighteenRatingMovie)).thenReturn("18");
		
		when(mockMovieService.getParentalControlLevel(invalidMovieName)).thenThrow(TitleNotFounException.class);
		
	}

	@Test
	public void test_userWithCorrectParentControl_True() throws Exception {
		
		target = new ParentalControlServiceImpl(mockMovieService);

		assertTrue(target.isClientAllowedToAccess(uRatingMovie, Rating.U.toString()));
		assertTrue(target.isClientAllowedToAccess(pgRatingMovie, Rating.PG.toString()));
		assertTrue(target.isClientAllowedToAccess(twelveRatingMovie, Rating.TWELVE.toString()));
		assertTrue(target.isClientAllowedToAccess(fifteenRatingMovie, Rating.FIFTEEN.toString()));
		assertTrue(target.isClientAllowedToAccess(eighteenRatingMovie, Rating.EIGHTEEN.toString()));
				
		assertTrue(target.isClientAllowedToAccess(uRatingMovie, Rating.PG.toString()));
		assertTrue(target.isClientAllowedToAccess(uRatingMovie, Rating.TWELVE.toString()));
		assertTrue(target.isClientAllowedToAccess(uRatingMovie, Rating.FIFTEEN.toString()));
		assertTrue(target.isClientAllowedToAccess(uRatingMovie, Rating.EIGHTEEN.toString()));
		
		assertTrue(target.isClientAllowedToAccess(pgRatingMovie, Rating.TWELVE.toString()));
		assertTrue(target.isClientAllowedToAccess(pgRatingMovie, Rating.FIFTEEN.toString()));
		assertTrue(target.isClientAllowedToAccess(pgRatingMovie, Rating.EIGHTEEN.toString()));
		
		assertTrue(target.isClientAllowedToAccess(twelveRatingMovie, Rating.FIFTEEN.toString()));
		assertTrue(target.isClientAllowedToAccess(fifteenRatingMovie, Rating.EIGHTEEN.toString()));



	}

	@Test
	public void test_userWithWrongParentalControl_False() throws Exception {
		
		target = new ParentalControlServiceImpl(mockMovieService);

		assertFalse(target.isClientAllowedToAccess(eighteenRatingMovie, Rating.PG.toString()));
		assertFalse(target.isClientAllowedToAccess(eighteenRatingMovie, Rating.U.toString()));
		assertFalse(target.isClientAllowedToAccess(eighteenRatingMovie, Rating.TWELVE.toString()));
		assertFalse(target.isClientAllowedToAccess(eighteenRatingMovie, Rating.FIFTEEN.toString()));
		
		assertFalse(target.isClientAllowedToAccess(fifteenRatingMovie, Rating.PG.toString()));
		assertFalse(target.isClientAllowedToAccess(fifteenRatingMovie, Rating.U.toString()));
		assertFalse(target.isClientAllowedToAccess(fifteenRatingMovie, Rating.TWELVE.toString()));
		
				
		assertFalse(target.isClientAllowedToAccess(twelveRatingMovie, Rating.PG.toString()));
		assertFalse(target.isClientAllowedToAccess(twelveRatingMovie, Rating.U.toString()));
		
		assertFalse(target.isClientAllowedToAccess(pgRatingMovie, Rating.U.toString()));

	}

	@Test
	public void test_invalidRatingInput_throws_IllegalArgumentException() throws Exception {
		
		target = new ParentalControlServiceImpl(mockMovieService);

		expectedException.expect(TechnicalFailureException.class);

		target.isClientAllowedToAccess(fifteenRatingMovie, InvalidRating);

	}

	@Test
	public void test_lowerCaseValidRatingString_NoException() throws Exception {

		target = new ParentalControlServiceImpl(mockMovieService);
		
		try {
			target.isClientAllowedToAccess(fifteenRatingMovie, "u");
		} catch (Exception e) {
			Assert.assertNull(e);
		}

	}

	@Test
	public void test_nonExistentTitleId_throwsException() throws Exception {
		
		target = new ParentalControlServiceImpl(mockMovieService);

		expectedException.expect(TitleNotFounException.class);
		
		target.isClientAllowedToAccess(invalidMovieName, "u");

	}
	
	@Test
	public void test_nullInput_throwsException() throws Exception {
		
		target = new ParentalControlServiceImpl(mockMovieService);

		expectedException.expect(TechnicalFailureException.class);
				
		target.isClientAllowedToAccess(null, null);

	}

}
