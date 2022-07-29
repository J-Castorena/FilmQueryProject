package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String description;
	private String releaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private double rating;
	private String specialFeatures;

	public Film() {
	}

	public Film(int id, String title, String description, String releaseYear, int languageId, int rentalDuration,
			double rentalRate, int length, double replacementCost, double rating, String specialFeatures) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Film [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", releaseYear=");
		builder.append(releaseYear);
		builder.append(", languageId=");
		builder.append(languageId);
		builder.append(", rentalDuration=");
		builder.append(rentalDuration);
		builder.append(", rentalRate=");
		builder.append(rentalRate);
		builder.append(", length=");
		builder.append(length);
		builder.append(", replacementCost=");
		builder.append(replacementCost);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", specialFeatures=");
		builder.append(specialFeatures);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, languageId, length, rating, releaseYear, rentalDuration, rentalRate,
				replacementCost, specialFeatures, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && id == other.id && languageId == other.languageId
				&& length == other.length && Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating)
				&& Objects.equals(releaseYear, other.releaseYear) && rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}

}
