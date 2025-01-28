package raisetech.StudentManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
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
    // 絞り込み検索。年齢が30代の人のみ抽出する。
    // 抽出したリストをコントローラーに渡す。
    // TODO 例外処理の実装
    return repository.getStudentsList()
        .stream()
        .filter(student -> student.getAge() == 30)
        .toList();
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

}
