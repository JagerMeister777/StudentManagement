package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentInfo;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.service.StudentsCoursesService;
import raisetech.StudentManagement.service.StudentsService;

@RestController
public class StudentsController {

  /** 受講生情報のService */
  private StudentsService studentsService;

  /** 受講生コース情報のService */
  private StudentsCoursesService studentsCoursesService;

  @Autowired
  public StudentsController(StudentsService studentsService,
      StudentsCoursesService studentsCoursesService) {
    this.studentsService = studentsService;
    this.studentsCoursesService = studentsCoursesService;
  }


  /**
   * 受講生情報の全件検索
   * @return 受講生情報の全件リスト
   */
  @GetMapping("/students")
  public List<Student> getStudentsList() {
    return studentsService.getStudentsList();
  }

  /**
   * 受講生情報を出力
   * @return 受講生情報(受講生の名前、受講しているコース、受講開始日、受講終了日)
   */
  @GetMapping("/studentsCourses")
  public List<StudentInfo> getStudentsCoursesList() {
    return studentsCoursesService.getStudentsCoursesList();
  }
}
