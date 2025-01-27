package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Course;
import raisetech.StudentManagement.repository.CoursesRepository;

@Service
public class CourseService {

  private CoursesRepository repository;

  @Autowired
  public CourseService(CoursesRepository repository) {
    this.repository = repository;
  }

  public List<Course> getCoursesList(){
    return repository.getCoursesList();
  }
}
