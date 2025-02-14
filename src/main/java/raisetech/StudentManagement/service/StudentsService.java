package raisetech.StudentManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
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

  public Optional<Student> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public void registerStudent(RegisterStudentForm form) {
    //TODO 受講生登録処理
    Student student = new Student();
    student.setFullName(form.getFullName());
    student.setFurigana(form.getFurigana());
    student.setNickName(form.getNickName());
    student.setEmail(form.getEmail());
    student.setLivingArea(form.getLivingArea());
    student.setAge(form.getAge());
    student.setGender(form.getGender());
    student.setRemark(form.getRemark());
    student.setDeleted(false);

    repository.save(student);
  }
}
