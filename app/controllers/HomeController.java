package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.*;
import play.data.*;
import play.libs.Json;
import static play.data.Form.*;

import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
    	List<Course> courses = Course.find.all();
    	JsonNode js = Json.toJson(courses);
    	return ok(js.toString());
    }
    
    public Result getCourse(Long id) {
    	Course course = Course.find.byId(id);
    	if (null == course)
    		return ok("null");
    	JsonNode js = Json.toJson(course);
    	return ok(js.toString());
    }
    
    public Result newCourse() {
    	try {
        	Map<String, String[]> params = request().body().asFormUrlEncoded();
        	Course course = new Course();
        	course.id = Long.parseLong(params.get("id")[0]);
        	course.name = params.get("name")[0];
        	course.save();
        	return ok("Ok");
    	}
    	catch (Exception ex){
    		return ok("Null");
    	}
    }
    
    public Result updateCourse(Long id) {
    	try {
    		Course course = Course.find.ref(id);
    		Map<String, String[]> params = request().body().asFormUrlEncoded();
        	course.name = params.get("name")[0];
        	course.update();
    		return ok("Ok");
    	}
    	catch (Exception ex){
    		return ok(ex.getMessage());
    	}
    }
    
    public Result deleteCourse(Long id) {
    	try {
    		Course course = Course.find.ref(id);
    		if (course.delete())
    			return ok("Ok");
    		return ok("Null");
    	}
    	catch (Exception ex){
    		return ok(ex.getMessage());
    	}
    }
}
