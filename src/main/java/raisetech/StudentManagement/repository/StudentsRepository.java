package raisetech.StudentManagement.repository;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;

/**　受講生情報のRepository　*/
@Mapper
public interface StudentsRepository {

  /**
   * 受講生の全件検索
   * @return 全件受講生情報
   */
  @Select("SELECT * FROM students")
  List<Student> getStudentsList();

  /**
   * 受講生情報をIDで検索
   * @param id 受講生ID
   * @return 受講生情報
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findByStudentId(int id);

  /**
   * 受講生情報をメールアドレスで検索して、取得。
   * @param email メールアドレス
   * @return 受講生情報、存在しなければEmptyを返す
   */
  @Select("SELECT * FROM students WHERE email = #{email}")
  Optional<Student> findByEmail(String email);

  /**
   * 受講生情報の登録
   * @param student 受講生情報
   */
  @Insert("INSERT INTO students (full_name, furigana, nick_name, email, living_area, age, gender, remark, isDeleted) VALUES (#{fullName}, #{furigana}, #{nickName}, #{email}, #{livingArea}, #{age}, #{gender}, #{remark}, #{isDeleted});")
  void registerStudent(Student student);

  /**
   * 受講生情報の更新
   * @param student 受講生情報
   */
  @Update("UPDATE students SET full_name = #{fullName}, furigana = #{furigana}, nick_name = #{nickName}, email = #{email}, living_area = #{livingArea}, age = #{age}, gender = #{gender}, remark = #{remark}, isDeleted = #{isDeleted} WHERE id = #{id}")
  void updateStudent(Student student);
}
