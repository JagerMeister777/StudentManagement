package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.repository.StudentsRepository;

@Service
public class StudentService {

  private StudentsRepository repository;

  @Autowired
  public StudentService(StudentsRepository repository) {
    this.repository = repository;
  }

  public List<Student> getStudentsList(){
    return repository.getStudentsList();
  }

}
