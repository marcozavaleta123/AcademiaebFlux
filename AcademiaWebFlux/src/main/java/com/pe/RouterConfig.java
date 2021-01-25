package com.pe;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.pe.handler.CourseHandler;
import com.pe.handler.RegistrationHandler;
import com.pe.handler.StudentHandler;

@Configuration
public class RouterConfig {
    
    private String routeStudent = "/v2/students";
    private String routeCourse = "/v2/courses";
    private String routeRegister = "/v2/register";

    @Bean
    public RouterFunction<ServerResponse> routesStudents(StudentHandler handler) {
	return route(GET(routeStudent), handler::list)
		.andRoute(POST(routeStudent), handler::save)
		.andRoute(PUT(routeStudent), handler::update)
		.andRoute(DELETE(routeStudent + "/{id}"), handler::delete);
    }
    
    @Bean
    public RouterFunction<ServerResponse> routesCourses(CourseHandler handler) {
	return route(GET(routeCourse), handler::list)
		.andRoute(POST(routeCourse), handler::save)
		.andRoute(PUT(routeCourse), handler::update);
    }
    
    @Bean
    public RouterFunction<ServerResponse> routesRegisters(RegistrationHandler handler) {
	return route(GET(routeRegister + "/{id}"), handler::findById)
		.andRoute(POST(routeRegister), handler::save);
    }

}
