package com.skytest.parentController.service;

import com.skytest.parentController.Exception.TechnicalFailureException;
import com.skytest.parentController.Exception.TitleNotFounException;

public interface MovieService {
	
	String getParentalControlLevel(String movieId)
		throws TitleNotFounException,TechnicalFailureException;

}
