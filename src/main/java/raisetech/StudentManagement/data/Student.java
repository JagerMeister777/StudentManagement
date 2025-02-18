package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private int id;
  private String fullName;
  private String furigana;
  private String nickName;
  private String email;
  private String livingArea;
  private int age;
  private String gender;
  private String remark;
  private boolean isDeleted;

  // 新規登録用コンストラクタ
  public Student(String full_name, String furigana, String nick_name, String email,
      String living_area, int age, String gender, String remark, boolean isDeleted) {
    this.fullName = full_name;
    this.furigana = furigana;
    this.nickName = nick_name;
    this.email = email;
    this.livingArea = living_area;
    this.age = age;
    this.gender = gender;
    this.remark = remark;
    this.isDeleted = isDeleted;
  }

  // データ取得用コンストラクタ
  public Student(int id, String full_name, String furigana, String nick_name, String email,
      String living_area, int age, String gender, String remark, boolean isDeleted) {
    this.id = id;
    this.fullName = full_name;
    this.furigana = furigana;
    this.nickName = nick_name;
    this.email = email;
    this.livingArea = living_area;
    this.age = age;
    this.gender = gender;
    this.remark = remark;
    this.isDeleted = isDeleted;
  }
}
