package raisetech.StudentManagement.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentsCoursesService;


@Component
public class StudentConverter {

  /** 受講生コース情報のService */
  private final StudentsCoursesService studentsCoursesService;

  @Autowired
  public StudentConverter(StudentsCoursesService studentsCoursesService) {
    this.studentsCoursesService = studentsCoursesService;
  }

  /**
   * 受講生情報とそれに紐づく受講しているコース情報を統合する
   * @param students 受講生リスト
   * @return List型のstudentDetails
   */
  public List<StudentDetail> studentConverter(List<Student> students) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    students.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentsCourses> convertStudentCourses = studentsCoursesService.getStudentsCoursesList(student.getId());
      studentDetail.setStudentsCoursesList(convertStudentCourses);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }
}
