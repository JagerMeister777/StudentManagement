package raisetech.StudentManagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentsCourses> studentsCoursesList;

  public StudentDetail() {

  }

  public StudentDetail(Student student, List<StudentsCourses> studentsCoursesList) {
    this.student = student;
    this.studentsCoursesList = studentsCoursesList;
  }

}
