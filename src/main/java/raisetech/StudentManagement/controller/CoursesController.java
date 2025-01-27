package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.Course;
import raisetech.StudentManagement.service.CourseService;

@RestController
public class CourseController {

  private CourseService service;

  @Autowired
  public CourseController(CourseService service) {
    this.service = service;
  }

  @GetMapping("/courses")
  public List<Course> getCoursesList() {
    return service.getCoursesList();
  }


}
