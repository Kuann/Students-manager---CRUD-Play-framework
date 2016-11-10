package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;
/**
 * Student entity managed by Ebean
 */
@Entity 
public class Student extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long id;
    
    @Constraints.Required
    public String name;
    
    //@ManyToOne
    //public Course course;
    @Constraints.Required
    public Long course_id;
    /**
     * Generic query helper for entity Student with id Long
     */
    public static Find<Long,Student> find = new Find<Long,Student>(){};
}

