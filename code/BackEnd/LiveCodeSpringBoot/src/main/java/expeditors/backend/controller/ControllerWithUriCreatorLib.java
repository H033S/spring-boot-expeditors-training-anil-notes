package expeditors.backend.controller;


import expeditors.backend.app.TryWithResourcesDemo;
import expeditors.backend.domain.Student;
import expeditors.backend.service.StudentService;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ttl.liburicreator.LibUriCreator;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/student2")
public class ControllerWithUriCreatorLib {

   private Logger logger = LoggerFactory.getLogger(this.getClass());

   @Autowired
   private StudentService studentService;

   @Autowired
   private TryWithResourcesDemo twrDemo;

//   @Autowired
//   private UriCreator uriCreator;

   @Autowired
   private LibUriCreator libUriCreator;

   @GetMapping
   public List<Student> getAll() throws Exception{
      List<Student> students = studentService.getStudents();

      return students;
   }

   @GetMapping("/{id}")
   public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
      Student student = studentService.getStudent(id);
      if (student == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Student with id: " + id);
      }
      return ResponseEntity.ok(student);
   }

   @Autowired
   private Validator validator;

   @PostMapping
   public ResponseEntity<?> addStudent(@RequestBody /*@Valid*/ Student student, Errors errors) {
      validator.validate(student, errors);

      if (errors.hasErrors()) {
         List<String> errmsgs = errors.getFieldErrors().stream()
               .map(error -> "Manual Validation error:" + error.getField() + ": " + error.getDefaultMessage()
                     + ", supplied Value: " + error.getRejectedValue())
               .collect(toList());
         return ResponseEntity.badRequest().body(errmsgs);
      }

      Student newStudent = studentService.createStudent(student);

      //http://localhost:8080/student/newStudent.getId()

      //URI newResource = uriCreator.getURI(student.getId());
      URI newResource = libUriCreator.getURI(student.getId());
//      URI newResource = ServletUriComponentsBuilder
//            .fromCurrentRequest()
//            .path("/{id}")
//            .buildAndExpand(student.getId())
//            .toUri();

      logger.info("Added Student: " + newStudent);

      //return ResponseEntity.created(newResource).body(newStudent);
      return ResponseEntity.created(newResource).build();
   }

//   private boolean validateStudent(Student student) {
//      if(student.getName() == null || student.getDob() == null) {
//         return false;
//      }
//      return true;
//   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
      boolean result = studentService.deleteStudent(id);
      if (!result) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Student with id: " + id);
      }

      return ResponseEntity.noContent().build();
   }

   @PutMapping
   public ResponseEntity<?> updateStudent(@RequestBody Student student) {
      boolean result = studentService.updateStudent(student);
      if (!result) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Student with id: " + student.getId());
      }

      return ResponseEntity.noContent().build();
   }


//   @ExceptionHandler(Exception.class)
//   public void handleControllerExceptions(Exception e) {
//      System.out.println("In Controller Exception Handler");
//   }
}

//REpresantational State Transfer

