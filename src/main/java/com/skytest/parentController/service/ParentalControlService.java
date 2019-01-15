package com.skytest.parentController.service;

import com.skytest.parentController.Exception.TechnicalFailureException;
import com.skytest.parentController.Exception.TitleNotFounException;

public interface ParentalControlService {
	
	public boolean isClientAllowedToAccess(String titleId, String parentalControl) throws TitleNotFounException, TechnicalFailureException;

}
