package com.skytest.parentController.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.skytest.parentController.Exception.TechnicalFailureException;
import com.skytest.parentController.util.Messages;

public enum Rating {

	U("U", 0), PG("PG", 1), TWELVE("12", 12), FIFTEEN("15", 15), EIGHTEEN("18", 18);

	protected static Map<Rating, Integer> ratingMap = new HashMap<Rating, Integer>();

	public static Rating getRatingEnumFromString(String ratingString) throws TechnicalFailureException {

		return Arrays.stream(Rating.values()).filter(rating -> rating.ratingName.equalsIgnoreCase(ratingString))
				.findFirst().orElseThrow(() -> new TechnicalFailureException(Messages.INVALID_RATING + ratingString));

	}

	public static Map<Rating, Integer> getRatingMap() {
		return ratingMap;
	}

	public static boolean isInputRatingGreaterThanRequiredRating(Rating inputRating, Rating requiredRating)
			throws IllegalArgumentException {

		return inputRating.getAgeRestrictionLevel() >= requiredRating.getAgeRestrictionLevel();

	}

	protected static Map<Rating, Integer> ratingMappings() {

		return ratingMap;

	}

	public static void setRatingMap(Map<Rating, Integer> ratingMap) {
		Rating.ratingMap = ratingMap;
	}

	String ratingName;

	Integer maxAgeForRating;

	Rating(String ratingName, Integer ageRestrictionLevel) {

		this.ratingName = ratingName;
		this.maxAgeForRating = ageRestrictionLevel;

	}

	public Integer getAgeRestrictionLevel() {
		return maxAgeForRating;
	}

	public String getRatingName() {
		return ratingName;
	}

	public void setAgeRestrictionLevel(Integer maxAgeForRating) {
		this.maxAgeForRating = maxAgeForRating;
	}

	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}

	@Override
	public String toString() {
		return this.ratingName;
	}

}
