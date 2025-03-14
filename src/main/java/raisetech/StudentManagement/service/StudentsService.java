package raisetech.StudentManagement.service;

import java.util.ArrayList;
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

  /** 学生情報のRepository */
  private final StudentsRepository repository;

  @Autowired
  public StudentsService(StudentsRepository repository) {
    this.repository = repository;
  }

  /**
   * 受講生リストの出力
   *
   * @return 受講生リスト（削除済みの受講生は除外）
   */
  public List<Student> getStudentsList() {
    List<Student> studentList = new ArrayList<>();
    repository.getStudentsList().forEach(student -> {
      if (!student.isDeleted()) {
        studentList.add(student);
      }
    });
    return studentList;
  }

  /**
   * 受講生のフルネームをIDで検索
   *
   * @param id 受講生ID
   * @return 受講生のフルネーム
   */
  public Student findByStudentId(int id) {

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

  /**
   * 受講生情報の論理削除
   * @param id 受講生ID
   */
  public void deleteStudent(int id) {
    repository.deleteStudent(id, true);
  }
}
