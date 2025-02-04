package raisetech.StudentManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.dmain.StudentDetail;


@Component
public class StudentConverter {

  public List<StudentDetail> studentConverter(List<Student> students, List<StudentsCourses> studentsCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    students.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentsCourses> convertStudentCourses = studentsCourses.stream()
          .filter(studentsCourse -> student.getId() == studentsCourse.getStudentId())
          .collect(Collectors.toList());

      studentDetail.setStudentsCoursesList(convertStudentCourses);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }
}
