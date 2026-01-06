package com.PullUp.MatchingService.service;

import java.util.Optional;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class DriverGeoQueryService {

	private static final String KEY = "drivers:available";
    private final StringRedisTemplate redis;

    public DriverGeoQueryService(StringRedisTemplate redis) {
        this.redis = redis;
    }
    
    public Optional<String> findNearestDriver( double pickupLat, double pickupLon, double radiusKm){
    		// First we will define the Search Area
    	Circle searchArea = new Circle(new Point(pickupLon,pickupLat),new Distance(radiusKm,Metrics.KILOMETERS));
    	
    	GeoResults<RedisGeoCommands.GeoLocation<String>> results =
                redis.opsForGeo().radius(KEY, searchArea);

        if (results == null || results.getContent().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(
                results.getContent().get(0).getContent().getName()
        );		
    }
    
    
    
    
    
    
}
