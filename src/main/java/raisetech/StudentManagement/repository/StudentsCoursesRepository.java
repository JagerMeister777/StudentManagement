package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.StudentsCourses;

/**　受講生コース情報のRepository　*/
@Mapper
public interface StudentsCoursesRepository {

  /**
   * 受講生コース情報を全件検索
   * @return 受講生の受講しているコース情報
   */
  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> getStudentsCourses();

  @Select("SELECT * FROM students_courses WHERE course_id = #{courseId}")
  List<StudentsCourses> javaStudentsList(int courseId);
}
