package com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ThePropertiesFinderUserManagementApplication.class);
	}

}
