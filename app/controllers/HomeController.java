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
    	return ok("Student Manager");
    }
    
    public Result getAllCourses() {
    	List<Course> courses = Course.find.all();
    	JsonNode js = Json.toJson(courses);
    	return ok(js.toString());
    }
    
    public Result getAllStudents() {
    	List<Student> students = Student.find.all();
    	JsonNode js = Json.toJson(students);
    	return ok(js.toString());
    }
    
    public Result getCourse(Long id) {
    	Course course = Course.find.byId(id);
    	if (null == course)
    		return forbidden("Can not get the course");
    	JsonNode js = Json.toJson(course);
    	return ok(js.toString());
    }
    
    public Result newCourse() {
    	try {
        	Map<String, String[]> params = request().body().asFormUrlEncoded();
        	String[] ids = params.get("id");
        	String[] names = params.get("name"); 
        	if (null == ids || null == names)
        		throw new Exception("Id or name is empty!");
        	Course course = new Course();
        	course.id = Long.parseLong(ids[0]);
        	course.name = names[0];
        	course.save();
        	return ok("Ok");
    	}
    	catch (Exception ex){
    		return forbidden(ex.getMessage());
    	}
    }
    
    public Result updateCourse() {
    	try {
    		Map<String, String[]> params = request().body().asFormUrlEncoded();
    		String[] ids = params.get("id");
        	String[] names = params.get("name"); 
        	if (null == ids || null == names)
        		throw new Exception("Id or name is empty!");
    		Course course = Course.find.ref(Long.parseLong(ids[0]));
    		course.name = names[0];
        	course.update();
    		return ok("Ok");
    	}
    	catch (Exception ex){
    		return forbidden(ex.getMessage());
    	}
    }
    
    public Result deleteCourse() {
    	try {
    		Map<String, String[]> params = request().body().asFormUrlEncoded();
    		String[] ids = params.get("id");
        	if (null == ids)
        		throw new Exception("Id is empty!");
    		Course course = Course.find.ref(Long.parseLong(ids[0]));
    		if (course.delete())
    			return ok("Ok");
    		return forbidden("Can not delete the course!");
    	}
    	catch (Exception ex){
    		return forbidden(ex.getMessage());
    	}
    }
    
    public Result getStudent(Long id) {
    	Student student = Student.find.byId(id);
    	if (null == student)
    		return forbidden("Can not get the student!");
    	JsonNode js = Json.toJson(student);
    	return ok(js.toString());
    }
    
    public Result newStudent() {
    	try {
        	Map<String, String[]> params = request().body().asFormUrlEncoded();
        	String[] ids = params.get("id");
        	String[] names = params.get("name"); 
        	String[] course_ids = params.get("course_id");
        	if (null == ids || null == names)
        		throw new Exception("Id or name is empty!");
        	Student student = new Student();
        	student.id = Long.parseLong(ids[0]);
        	student.name = names[0];
        	if (null != course_ids)
        		student.course_id = Long.parseLong(course_ids[0]);
        	else
        		student.course_id = null;
        	student.save();
        	return ok("Ok");
    	}
    	catch (Exception ex){
    		return forbidden(ex.getMessage());
    	}
    }
    
    public Result updateStudent() {
    	try {
    		Map<String, String[]> params = request().body().asFormUrlEncoded();
    		String[] ids = params.get("id");
        	String[] names = params.get("name"); 
        	String[] course_ids = params.get("course_id");
        	if (null == ids)
        		throw new Exception("Id is empty!");
        	Student student = Student.find.ref(Long.parseLong(ids[0]));
        	if (null != names)
	        	student.name = names[0];
        	if (null != course_ids)
        		student.course_id = Long.parseLong(course_ids[0]);
        	student.update();
        	return ok("Ok");
    	}
    	catch (Exception ex){
    		return forbidden(ex.getMessage());
    	}
    }
    
    public Result deleteStudent() {
    	try {
    		Map<String, String[]> params = request().body().asFormUrlEncoded();
    		String[] ids = params.get("id");
    		if (null == ids)
    			throw new Exception("Id is empty!");
    		Student student = Student.find.ref(Long.parseLong(ids[0]));
    		if (student.delete())
    			return ok("Ok");
    		return forbidden("Can not delete the student!");
    	}
    	catch (Exception ex){
    		return forbidden(ex.getMessage());
    	}
    }
}
