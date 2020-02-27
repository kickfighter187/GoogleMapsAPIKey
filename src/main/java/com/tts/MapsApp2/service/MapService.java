package com.tts.MapsApp2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tts.MapsApp2.model.GeocodingResponse;
import com.tts.MapsApp2.model.Location;

@Service
public class MapService {
	
	@Value("${api_key}")
	private String apiKey;
	
	public void addCoordinates (Location location) {
		//URL from root geocoding to get info about city, state
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + 
			    location.getCity() + "," + location.getState() + "&key=" + apiKey;
		//Make API call to google maps and store that information as response in regards to GeocodingResponse
		RestTemplate restTemplate = new RestTemplate();
		GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class);
		//parse through JSON response to get desired results, in this case location
		Location coordinates = response.getResults().get(0).getGeometry().getLocation();
		//Set aka Save the information to your Model Variable(Location Model)
		location.setLat(coordinates.getLat());
		location.setLng(coordinates.getLng());
	}

}
