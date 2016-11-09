package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;



/**
 * Course entity managed by Ebean
 */
@Entity 
public class Course extends com.avaje.ebean.Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long id;
    
    @Constraints.Required
    public String name;
    
    /**
     * Generic query helper for entity Course with id Long
     */
    public static Find<Long,Course> find = new Find<Long,Course>(){};
}