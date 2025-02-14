package raisetech.StudentManagement.repository;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.form.RegisterStudentForm;

/**　受講生コース情報のRepository　*/
@Mapper
public interface StudentsCoursesRepository {

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> getAllStudentsCoursesList();

  @Select("SELECT * FROM students_courses WHERE course_id = #{courseId}")
  List<StudentsCourses> javaStudentsList(int courseId);

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> getStudentsCoursesList(int studentId);

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId} AND course_id = #{courseId}")
  Optional<StudentsCourses> isExistingCombination(int studentId, int courseId);

  @Select("INSERT INTO students_courses (student_id, course_id, course_start_date, course_end_date) VALUES (#{studentId}, #{courseId}, #{courseStartDate}, #{courseEndDate});")
  void save(StudentsCourses studentsCourses);
}
