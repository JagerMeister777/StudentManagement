package raisetech.StudentManagement.exceptions;

/**
 * 受講生情報の登録処理で受講生がコースを重複していた場合に例外をthrowする
 */
public class ExistStudentsCoursesException extends RuntimeException{
  public ExistStudentsCoursesException(String message) {
    super(message);
  }
}
