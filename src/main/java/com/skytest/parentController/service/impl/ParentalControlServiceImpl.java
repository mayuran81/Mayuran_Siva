package com.skytest.parentController.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skytest.parentController.Exception.TechnicalFailureException;
import com.skytest.parentController.Exception.TitleNotFounException;
import com.skytest.parentController.Model.Rating;
import com.skytest.parentController.service.MovieService;
import com.skytest.parentController.service.ParentalControlService;
import com.skytest.parentController.util.Messages;

public class ParentalControlServiceImpl implements ParentalControlService {
	
	private static final Logger log = LoggerFactory.getLogger(ParentalControlServiceImpl.class);

	private MovieService movieService;

	public ParentalControlServiceImpl(MovieService movieService) {

		this.movieService = movieService;

	}

	
	public boolean isClientAllowedToAccess(String titleId, String inputRating)
			throws TitleNotFounException, TechnicalFailureException {

		if (titleId == null || inputRating == null)
			throw new TechnicalFailureException(Messages.NULL_INPUT_EXCEPTION_MESSAGE);
		
		try {
			Rating inputRatingEnum=Rating.getRatingEnumFromString(inputRating);
			
			String movieParentalLevel = this.movieService.getParentalControlLevel(titleId);

			Rating requiredRating = Rating.getRatingEnumFromString(movieParentalLevel);		

			return Rating.isInputRatingGreaterThanRequiredRating(inputRatingEnum, requiredRating);
		}catch(TitleNotFounException tnfe) {			
			log.error(tnfe.getMessage(),tnfe);
			throw tnfe;
		}catch(TechnicalFailureException tfe) {			
			log.error(tfe.getMessage(),tfe);
			throw tfe;
		}
		

		

	}

}
