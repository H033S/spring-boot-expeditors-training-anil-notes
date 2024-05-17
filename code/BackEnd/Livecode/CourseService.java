package expeditors.backend.service;

import expeditors.backend.dao.BaseDAO;
import expeditors.backend.domain.Course;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
public class CourseService {

    @Autowired
    private BaseDAO<Course> courseDAO;

    private RestClient ratingClient;

    public CourseService() {
        this.ratingClient = RestClient.builder()
              .baseUrl("http://localhost:10001/ratingService")
              .defaultHeader("Accept", "application/json")
              .defaultHeader("Content-Type", "application/json")
              .build();
        int stop = 0;
    }

    public Course createCourse(String code, String title) {
        Course course = new Course(code, title);
        course = courseDAO.insert(course);

        return course;
    }

    public Course createCourse(Course course) {
        course = courseDAO.insert(course);

        return course;
    }

    public boolean deleteCourse(int id) {
        Course course = courseDAO.findById(id);
        if (course != null) {
            courseDAO.delete(course);
            return true;
        }
        return false;
    }

    public boolean updateCourse(Course course) {
        return courseDAO.update(course);
    }

    public Course getCourseByCode(String code) {
        List<Course> courses = courseDAO.findAll();
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }

    public Course getCourse(int id) {
        var course = courseDAO.findById(id);
        return course;
    }

    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }

    public BaseDAO<Course> getCourseDAO() {
        return courseDAO;
    }

    public void setCourseDAO(BaseDAO<Course> courseDAO) {
        this.courseDAO = courseDAO;
    }
}