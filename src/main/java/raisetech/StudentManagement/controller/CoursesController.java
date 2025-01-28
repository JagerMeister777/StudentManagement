package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.Course;
import raisetech.StudentManagement.service.CoursesService;

@RestController
public class CoursesController {

  /**　コース情報のService　*/
  private CoursesService service;

  @Autowired
  public CoursesController(CoursesService service) {
    this.service = service;
  }

  /**
   * コース情報を全件検索
   * @return コース情報の全件リスト
   */
  @GetMapping("/courses")
  public List<Course> getCoursesList() {
    return service.getCoursesList();
  }

}
