package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Course;
import raisetech.StudentManagement.repository.CoursesRepository;

@Service
public class CoursesService {

  private CoursesRepository repository;

  @Autowired
  public CoursesService(CoursesRepository repository) {
    this.repository = repository;
  }

  public List<Course> getCoursesList(){
    return repository.getCoursesList();
  }
}
