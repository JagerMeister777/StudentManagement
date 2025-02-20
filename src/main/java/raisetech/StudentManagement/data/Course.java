package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {

  private int id;
  private String name;

  // 新規登録用コンストラクタ
  public Course(String name) {
    this.name = name;
  }

  // データ取得用コンストラクタ
  public Course(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
