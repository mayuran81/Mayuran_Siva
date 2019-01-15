package com.skytest.parentController.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.skytest.parentController.Exception.TechnicalFailureException;
import com.skytest.parentController.util.Messages;

public enum Rating {
	
	U("U",0),
	PG("PG",1),
	TWELVE("12",12),
	FIFTEEN("15",15),
	EIGHTEEN("18",18); 
	
	String ratingName;
	public String getRatingName() {
		return ratingName;
	}

	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}

	public static Map<Rating, Integer> getRatingMap() {
		return ratingMap;
	}

	public static void setRatingMap(Map<Rating, Integer> ratingMap) {
		Rating.ratingMap = ratingMap;
	}

	Integer maxAgeForRating;
	
	public Integer getAgeRestrictionLevel() {
		return maxAgeForRating;
	}

	public void setAgeRestrictionLevel(Integer maxAgeForRating) {
		this.maxAgeForRating = maxAgeForRating;
	}

	protected static Map<Rating, Integer> ratingMap = new HashMap<Rating, Integer>();
	
	
	protected static Map<Rating, Integer> ratingMappings() {
		
		return ratingMap;
		
	}
	
	Rating(String ratingName, Integer ageRestrictionLevel) {
		
		this.ratingName = ratingName;		
		this.maxAgeForRating = ageRestrictionLevel;
		
	}

	public static boolean isInputRatingGreaterThanRequiredRating(Rating inputRating,
			Rating requiredRating) throws IllegalArgumentException {
								
		return inputRating.getAgeRestrictionLevel() >= requiredRating.getAgeRestrictionLevel(); 
									
		
	}
		
	
	public static Rating getRatingEnumFromString(String ratingString) throws TechnicalFailureException {
		
		return Arrays.stream(Rating.values())
					  .filter(rating->rating.ratingName.equalsIgnoreCase(ratingString))
					  .findFirst()
					  .orElseThrow(() -> new TechnicalFailureException(Messages.INVALID_RATING + ratingString));
	}
	
	@Override
	public String toString() {
		return this.ratingName;
	}

}
