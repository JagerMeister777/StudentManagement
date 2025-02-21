package raisetech.StudentManagement.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.form.RegisterStudentForm;
import raisetech.StudentManagement.repository.StudentsRepository;

@Service
public class StudentsService {

  /** 学生情報のRepository */
  private StudentsRepository repository;

  @Autowired
  public StudentsService(StudentsRepository repository) {
    this.repository = repository;
  }

  /**
   * 30歳の受講生の検索
   * @return 30歳の受講生リスト
   */
  public List<Student> getStudentsList() {
    // 抽出したリストをコントローラーに渡す。
    // TODO 例外処理の実装
    return repository.getStudentsList();
  }

  /**
   * 受講生のフルネームをIDで検索
   * @param id 受講生ID
   * @return 受講生のフルネーム
   */
  public String findByStudentId(int id) {
    // TODO 例外処理の実装
    Student student = repository.findByStudentId(id);
    return student.getFullName();
  }

  /**
   * 受講生情報をメールアドレスで検索し、取得
   * @param email メールアドレス
   * @return 受講生情報、存在しなければEmptyを返す
   */
  public Optional<Student> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  /**
   * 受講生情報の登録
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
}
