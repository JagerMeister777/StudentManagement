package raisetech.StudentManagement.repository;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.StudentsCourses;

/**　受講生コース情報のRepository　*/
@Mapper
public interface StudentsCoursesRepository {

  /**
   * 受講生の全件取得
   * @return 受講生リスト
   */
  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> getAllStudentsCoursesList();

  /**
   * 特定のコースを受けている受講生リストの取得
   * @param courseId コースID
   * @return 特定のコースを受けている受講生リスト
   */
  @Select("SELECT * FROM students_courses WHERE course_id = #{courseId}")
  List<StudentsCourses> javaStudentsList(int courseId);

  /**
   * 受講生コース情報を受講生IDで検索し取得
   * @param studentId 受講生ID
   * @return 受講生情報
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> getStudentsCoursesList(int studentId);

  /**
   * 受講生IDとコースIDを引数でもらい、一致するものを返す
   * @param studentId 受講生ID
   * @param courseId コースID
   * @return 受講生コース情報、存在しなければEmptyを返す
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId} AND course_id = #{courseId}")
  Optional<StudentsCourses> isExistingCombination(int studentId, int courseId);

  /**
   * 受講生コース情報の登録
   * @param studentsCourses 受講生コース情報
   */
  @Select("INSERT INTO students_courses (student_id, course_id, course_start_date, course_end_date) VALUES (#{studentId}, #{courseId}, #{courseStartDate}, #{courseEndDate});")
  void registerStudentsCourses(StudentsCourses studentsCourses);

  @Update("UPDATE students_courses SET student_id = #{studentId}, course_id = #{courseId}, course_start_date = ##{courseStartDate}, course_end_date = #{courseEndDate} WHERE id = #{id}")
  void updateStudentsCourses(StudentsCourses studentsCourses);
}
