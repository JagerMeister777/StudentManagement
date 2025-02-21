package raisetech.StudentManagement.service;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
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

  public Map<String, String> generateChangeFieldMap(Student existStudent, UpdateStudentForm form) {
    Map<String, String> changeFieldMap = new HashMap<>();
    Map<String, Object> existStudentFieldMap = new HashMap<>();
    Map<String, Object> updateStudentFieldMap = new HashMap<>();

    // Studentのフィールド値をMapに格納
    existStudentFieldMap.put("名前", existStudent.getFullName());
    existStudentFieldMap.put("フリガナ", existStudent.getFurigana());
    existStudentFieldMap.put("ニックネーム", existStudent.getNickName());
    existStudentFieldMap.put("メールアドレス", existStudent.getEmail());
    existStudentFieldMap.put("住んでいる地域", existStudent.getLivingArea());
    existStudentFieldMap.put("年齢", existStudent.getAge());
    existStudentFieldMap.put("性別", existStudent.getGender());
    existStudentFieldMap.put("備考", existStudent.getRemark());

    // フォームの入力値をMapに格納
    updateStudentFieldMap.put("名前", form.getFullName());
    updateStudentFieldMap.put("フリガナ", form.getFurigana());
    updateStudentFieldMap.put("ニックネーム", form.getNickName());
    updateStudentFieldMap.put("メールアドレス", form.getEmail());
    updateStudentFieldMap.put("住んでいる地域", form.getLivingArea());
    updateStudentFieldMap.put("年齢", form.getAge());
    updateStudentFieldMap.put("性別", form.getGender());
    updateStudentFieldMap.put("備考", form.getRemark());

    // フィールドごとに比較して変更されていればリストに追加
    for (String key : existStudentFieldMap.keySet()) {
      if (!existStudentFieldMap.get(key).equals(updateStudentFieldMap.get(key))) {
        changeFieldMap.put(key,updateStudentFieldMap.get(key).toString());
      }
    }

    return changeFieldMap;
  }

  public String updateStudentHandling(@Valid UpdateStudentForm form, int id, BindingResult result) {
    if(result.hasErrors()) {
      return "エラー: " + result.getAllErrors();
    }

    Student existStudent = findByStudentId(id);
    Map<String,String> changeFieldMap = generateChangeFieldMap(existStudent,form);

    if(!changeFieldMap.isEmpty()) {
      updateStudent(existStudent,form);
      return changeFieldMap.toString();
    }

    return "変更がありませんでした。";
  }
}
