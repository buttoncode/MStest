package com.buttoncode.mstest.admin.security;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MenuConfiguration 
{
	private static Map<String, String> MENU_URL_PATTERN_MAP = new HashMap<>();
	
	static
	{
		MENU_URL_PATTERN_MAP.put("/home", "Home");

		MENU_URL_PATTERN_MAP.put("/departments", "Departments");

		MENU_URL_PATTERN_MAP.put("/employees", "Employees");

		MENU_URL_PATTERN_MAP.put("/compliances", "Compliances");
		MENU_URL_PATTERN_MAP.put("/compliances/code_of_ethic", "Compliances_code_of_ethic");
		MENU_URL_PATTERN_MAP.put("/compliances/anticorruption_policy", "Compliances_anticorruption_policy");

		MENU_URL_PATTERN_MAP.put("/stocktakings", "Stocktakings");
		MENU_URL_PATTERN_MAP.put("/stocktakings/configure", "Stocktakings_configure");

		MENU_URL_PATTERN_MAP.put("/providers", "Providers");

		MENU_URL_PATTERN_MAP.put("/users", "Users");
		MENU_URL_PATTERN_MAP.put("/roles", "Roles");
		MENU_URL_PATTERN_MAP.put("/permissions", "Permissions");
	}
	
	public static Map<String, String> getMenuUrlPatternMap() {
		return Collections.unmodifiableMap(MENU_URL_PATTERN_MAP);
	}

	public static String getMatchingMenu(String uri) {
		Set<String> keySet = MENU_URL_PATTERN_MAP.keySet();
		for (String key : keySet) {
			if(uri.startsWith(key)){
				return MENU_URL_PATTERN_MAP.get(key);
			}
		}
		return "";
	}
}
