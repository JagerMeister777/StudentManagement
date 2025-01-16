package raisetech.StudentManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {


	@Autowired
	private StudentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@GetMapping("/student")
	public String searchStudentInfo(@RequestParam("name") String name){
		Student student = repository.searchStudentInfo(name);
		return student.getName() + " " + student.getAge() + "歳";
	}

	@GetMapping("/student/list")
	public List<Student> getStudentList(){
    return repository.getAllStudent();
	}

	@PostMapping("/student")
	public void resisterStudent (String name, int age) {
		repository.resisterStudent(name,age);
	}

	@PatchMapping("/student")
	public void updateStudentInfo(@RequestParam("name") String name, @RequestParam("age") int age) {
		repository.updateStudentInfo(name, age);
	}

	@DeleteMapping("/student")
	public void deleteStudent(@RequestParam("name") String name) {
		repository.deleteStudent(name);
	}
}
