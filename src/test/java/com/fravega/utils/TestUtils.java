package com.fravega.utils;

import java.util.concurrent.ThreadLocalRandom;

public class TestUtils {
    
    public static double getRandomLongitude(){
		return ThreadLocalRandom.current().nextDouble(-180, 180);
	}

	public static double getRandomLatitude(){
		return ThreadLocalRandom.current().nextDouble(-90, 90);
	}
}
