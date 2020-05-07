package com.school.api.common.utils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ScUtil {

	public static boolean isAllPresent(Object... objects) {

		if (objects == null || objects.length <= 0)
			return false;

		for (int i = 0; i < objects.length; i++) {

			Object obj = objects[i];

			if (obj == null)
				return false;
			else {

				if (obj instanceof String)
					return !((String) obj).isEmpty();
				else if (obj instanceof List || obj instanceof Map || obj instanceof Set)
					return !((Collection<?>) obj).isEmpty();
			}
		}

		return true;
	}

	public static String getGeneratedNumber(String prefix) {

		LocalDateTime now = LocalDateTime.now();
		int month = now.getMonth().getValue();
		int year = now.getYear();
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		Random rand = new Random();
		int rndNo = rand.nextInt(100) + 1;

		prefix += year + "" + month + "" + hour + "" + minute + "" + second + "" + rndNo;

		return prefix;

	}

}
