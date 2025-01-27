package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.service.StudentsService;

@RestController
public class StudentsController {

  private StudentsService service;

  @Autowired
  public StudentsController(StudentsService service) {
    this.service = service;
  }

  @GetMapping("/students")
  public List<Student> getStudentsList() {
    return service.getStudentsList();
  }
}
