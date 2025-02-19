package raisetech.StudentManagement.exceptions;

/**
 * 受講生情報の登録処理で受講生がコースを重複していた場合に例外をthrowする
 */
public class ExistedStudentsCoursesException extends RuntimeException{
  public ExistedStudentsCoursesException(String message) {
    super(message);
  }
}
