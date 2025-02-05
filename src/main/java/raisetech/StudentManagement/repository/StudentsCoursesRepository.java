package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.StudentsCourses;

/**　受講生コース情報のRepository　*/
@Mapper
public interface StudentsCoursesRepository {

  @Select("SELECT * FROM students_courses WHERE course_id = #{courseId}")
  List<StudentsCourses> javaStudentsList(int courseId);

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> getStudentsCoursesList(int studentId);
}
