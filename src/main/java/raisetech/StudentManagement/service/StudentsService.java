package raisetech.StudentManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.form.RegisterStudentForm;
import raisetech.StudentManagement.form.UpdateStudentForm;
import raisetech.StudentManagement.repository.StudentsRepository;

@Service
public class StudentsService {

  /**
   * 学生情報のRepository
   */
  private StudentsRepository repository;

  @Autowired
  public StudentsService(StudentsRepository repository) {
    this.repository = repository;
  }

  /**
   * 30歳の受講生の検索
   *
   * @return 30歳の受講生リスト
   */
  public List<Student> getStudentsList() {
    // 抽出したリストをコントローラーに渡す。
    // TODO 例外処理の実装
    return repository.getStudentsList();
  }

  /**
   * 受講生のフルネームをIDで検索
   *
   * @param id 受講生ID
   * @return 受講生のフルネーム
   */
  public Student findByStudentId(int id) {
    // TODO 例外処理の実装
    return repository.findByStudentId(id);
  }

  /**
   * 受講生情報をメールアドレスで検索し、取得
   *
   * @param email メールアドレス
   * @return 受講生情報、存在しなければEmptyを返す
   */
  public Optional<Student> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  /**
   * 受講生情報の登録
   *
   * @param form 登録フォームの情報
   */
  @Transactional
  public void registerStudent(RegisterStudentForm form) {
    Student student = new Student(
        form.getFullName(),
        form.getFurigana(),
        form.getNickName(),
        form.getEmail(),
        form.getLivingArea(),
        form.getAge(),
        form.getGender(),
        form.getRemark(),
        false
    );

    repository.registerStudent(student);
  }

  /**
   * 受講生情報の更新処理
   *
   * @param existStudent 既に登録されている受講生情報
   * @param form         受講生更新フォームに入力された情報
   */
  @Transactional
  public void updateStudent(Student existStudent, UpdateStudentForm form) {
    existStudent.setFullName(form.getFullName());
    existStudent.setFurigana(form.getFurigana());
    existStudent.setNickName(form.getNickName());
    existStudent.setEmail(form.getEmail());
    existStudent.setLivingArea(form.getLivingArea());
    existStudent.setAge(form.getAge());
    existStudent.setGender(form.getGender());
    existStudent.setRemark(form.getRemark());

    repository.updateStudent(existStudent);

  }
}
