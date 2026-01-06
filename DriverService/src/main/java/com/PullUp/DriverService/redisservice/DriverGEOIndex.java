package com.PullUp.DriverService.redisservice;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.geo.Point;

@Service
public class DriverGEOIndex {
	private static final String KEY = "drivers:available";
	private final StringRedisTemplate redis;
	
	public DriverGEOIndex(StringRedisTemplate redis) {
		super();
		this.redis = redis;
	}
	
	public void addAvailableDriver(String driverId , Double lon , Double lat) {
		redis.opsForGeo().add(KEY,new Point(lon,lat), driverId);
	}
	
	public void removeDriver(String driverId) {
		redis.opsForZSet().remove(KEY, driverId);
	}
	
	public void updateLocation(String driverId, double lon, double lat) {
	    redis.opsForGeo().add(KEY, new Point(lon, lat), driverId);
	}

	
	
}
