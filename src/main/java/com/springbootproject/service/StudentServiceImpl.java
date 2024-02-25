package com.springbootproject.service;

import com.springbootproject.dto.student.StudentDto;
import com.springbootproject.dto.student.StudentDtoListDto;
import com.springbootproject.exception.student.*;
import com.springbootproject.object.Student;
import com.springbootproject.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public long countAllTheStudentTableRows() throws StudentTableIsEmptyException {
        log.debug("countAllTheRowsInTheStudentTable() was called");
        long numberOfRows = studentRepository.count();

        if (numberOfRows <= 0) {
            throw new StudentTableIsEmptyException("Student table is empty. Number of rows is 0. Method: countAllTheStudentTableRows() in StudentServiceImpl");
        } else {
            return numberOfRows;
        }
    }

    public Student saveStudent(@Validated StudentDto studentDto) {
        log.debug("saveStudent() was called");
        if (studentDto != null) {
            Student student = new Student(
                    studentDto.getId(), studentDto.getName(), studentDto.getAge(), studentDto.getEmail()
            );
            return studentRepository.save(student);
        } else {
            throw new StudentDtoNullException("StudentDto is null. Method: saveMultipleStudentsAtOnce() in StudentServiceImpl");
        }
    }

    public List<Student> saveMultipleStudentsAtOnce(@Validated StudentDtoListDto studentDtoListDto) throws StudentDtoNullException {
        log.debug("saveMultipleStudentsAtOnce() was called");
        List<Student> studentList = new ArrayList<>();

        for (int i = 0; i < studentDtoListDto.getStudentDtoListDto().size(); i++) {
            if (studentDtoListDto.getStudentDtoListDto() != null && studentDtoListDto.getStudentDtoListDto().get(i) != null) {
                Student student = new Student(studentDtoListDto.getStudentDtoListDto().get(i).getId(), studentDtoListDto.getStudentDtoListDto().get(i).getName(),
                        studentDtoListDto.getStudentDtoListDto().get(i).getAge(), studentDtoListDto.getStudentDtoListDto().get(i).getEmail());
                studentList.add(student);
            } else {
                throw new StudentDtoNullException("StudentDto is null in StudentServiceImpl saveMultipleStudentsAtOnce()");
            }
        }
        return studentRepository.saveAll(studentList);
    }

    public Student updateStudent(@Validated StudentDto studentDto) throws StudentWithSuchAnIdDoesNotExistException, StudentDtoNullException {
        log.debug("updateStudent() was called");
            Student existingStudent = findStudentById(studentDto.getId());
                existingStudent.setStudentName(studentDto.getName());
                existingStudent.setStudentAge(studentDto.getAge());
                existingStudent.setStudentEmail(studentDto.getEmail());
                return studentRepository.save(existingStudent);
    }

    //this is not used currently, delete this comment if you start using it
    public boolean checkIfStudentExistsById(Student student) throws StudentWithSuchAnIdDoesNotExistException {
        log.debug("checkIfStudentExistsById() was called");
        if (student == null && student.getStudentId() == 0) {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist. Method: checkIfStudentExistsById() in StudentServiceImpl");
        }
        if (student != null && student.getStudentId() != 0 && studentRepository.existsById(student.getStudentId())) {
            return true;
        } else {
            return false;
        }
    }

    public Student findStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException {
        log.debug("findStudentById() was called");
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent != null && optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist. Method: findStudentById() in StudentServiceImpl");
        }
    }

    //this is not used currently, delete this comment if you start using it
    public List<StudentDto> findAllStudentsButReturnAsStudentDtoList() {
        List<StudentDto> listOfCurrentStudentsWithValuesButConvertedToStudentDTO = new ArrayList<>();
        log.info("findAllStudentsButReturnAsStudentDtoList() was called");
        List<Student> studentListWithActualValues = findAllStudents();
        for (int i = 1; i < studentListWithActualValues.size(); i++) {
            if (studentListWithActualValues.get(i) != null) {
                StudentDto studentDto = new StudentDto();
                studentDto.setId(studentListWithActualValues.get(i).getStudentId());
                studentDto.setName(studentListWithActualValues.get(i).getStudentName());
                studentDto.setAge(studentListWithActualValues.get(i).getStudentAge());
                studentDto.setEmail(studentListWithActualValues.get(i).getStudentEmail());
                listOfCurrentStudentsWithValuesButConvertedToStudentDTO.add(studentDto);
            } else {
                throw new StudentNullException("Student in studentTable is null and therefore invalid. Method: findAllStudentsButReturnAsStudentDtoList() in StudentServiceImpl");
            }
        }
        return listOfCurrentStudentsWithValuesButConvertedToStudentDTO;
    }

    public List<Student> findAllStudents() throws StudentTableIsEmptyException {
        log.info("finAll() was called");
        List<Student> allStudents = studentRepository.findAll();

        if (allStudents.isEmpty()) {
            throw new StudentTableIsEmptyException("Student table does not have any students in it. Method: listAllStudents() is StudentServiceImpl");
        } else {
            return allStudents;
        }
    }

    public void deleteStudentById(int id) throws StudentWithSuchAnIdDoesNotExistException {
        log.info("deleteStudentById() was called");
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentWithSuchAnIdDoesNotExistException("Student with such an id does not exist and therefore can not be deleted. Method: deleteStudentById() in StudentServiceImpl");
        }
    }
}
