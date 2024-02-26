package springbootproject.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class StudentControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("#1: Testing addNewStudentFormDisplay(): should return the right view name and attribute")
    public void StudentControllerImpl_addNewStudentFormDisplay_addsTheRightViewNameOrAttribute() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/student/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("studentDto"))
                .andExpect(MockMvcResultMatchers.view().name("/student/register"));
    }

    @Test
    @DisplayName("#2: Testing addNewStudentFormAction(): positive test, should not throw exceptions in case the studentDto is valid, returns the right view name and attribute")
    public void StudentControllerImpl_addNewStudentAction_addsTheRightViewNameAndAttribute() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/student/addnewstudentsubmit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "12")
                        .param("name", "John Doe")
                        .param("age", "24")
                        .param("email", "simon@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("successfulRegistration", true))
                .andExpect(MockMvcResultMatchers.model().attributeExists("successfulRegistrationMessage"))
                .andExpect(MockMvcResultMatchers.view().name("/student/register"));
    }

//    @Test
//    @DisplayName("#3: Testing addNewStudentFormAction(): should throw exception in case the studentDto is invalid")
//    public void StudentControllerImpl_addNewStudentAction_throwExceptionAndDoesNotTheRightViewNameAndAttribute() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/student/addnewstudentsubmit")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("courseId", "1")
//                        .param("name", " ")
//                        .param("age", "24")
//                        .param("email", "simon@gmail.com"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.model().attributeExists("failureAtRegistrationMessage"))
//                .andExpect(MockMvcResultMatchers.view().name("/student/register"));
//    }

//    @Test
//    public void testAddNewStudentAction_Success() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/student/addnewstudentsubmit")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("firstName", "John")
//                        .param("lastName", "Doe")
//                        .param("age", "25"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("student/register"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("successfulRegistration"))
//                .andExpect(MockMvcResultMatchers.model().attribute("successfulRegistration", true))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("successfulRegistrationMessage"))
//                .andExpect(MockMvcResultMatchers.model().attribute("successfulRegistrationMessage", "Student registered successfully."));
//    }

//    @PostMapping("student/addnewstudentsubmit")
//    public String addNewStudentFormAction(@ModelAttribute StudentDto studentDto, Model model) throws StudentDtoNullException {
//        log.info("@Controller addNewStudentFormAction() was called");
//        if (studentDto != null) {
//            studentServiceImpl.saveStudent(studentDto);
//            model.addAttribute("successfulRegistration", true);
//            model.addAttribute("successfulRegistrationMessage", "Student registered successfully.");
//            return "student/register";
//        } else {
//            model.addAttribute("successfulRegistration", true);
//            model.addAttribute("failureAtRegistrationMessage", "Student registration failed.");
//            throw new StudentDtoNullException("StudentDto is null in StudentController/addNewStudentFormAction()");
//        }
//    }
//
//    @Test
//    @DisplayName("#3: Testing addStudentDtoListPageWithForm(): should return the right view name and attribute")
//    public void StudentControllerImpl_addStudentDtoListPage_returnTheRightStringAndPassTheRightValue() {
//
//        StudentDto studentDto1 = new StudentDto(1,"Test Name",12,"test@email.com");
//        StudentDto studentDto2 = new StudentDto(2,"Test Name2",122,"test@email2.com");
//
//        List<StudentDto> studentDtoListDto =  new ArrayList<>();
//
//        studentDtoListDto.add(studentDto1);
//        studentDtoListDto.add(studentDto2);
//
////        when()
//
//        String viewName = studentControllerImpl.addStudentDtoListPageWithForm(model);
//
//        assertEquals("/student/addnewstudentlist", viewName, "View name does not match");
//
//        verify(model).addAttribute("studentDtoListDtoForm", studentDtoListDto);
//
////        this.mockMvc
////                .perform(MockMvcRequestBuilders.get("/teachers"))
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.view().name("teacher/teachers"))
////                .andExpect(MockMvcResultMatchers.model().attributeExists("teachers"));
//    }
//
//
//
////    @GetMapping("/student/addnewstudentlist")
////    public String addStudentDtoListPageWithForm(Model model) {
////        log.info("@Controller addStudentDtoListPageWithForm() was called");
////        StudentDtoListDto studentDtoListDto = new StudentDtoListDto();
////        for (int i = 1; i <= 3; i++) {
////            studentDtoListDto.addStudentDto(new StudentDto());
////        }
////        model.addAttribute("studentDtoListDtoForm", studentDtoListDto);
////        return "/student/addnewstudentlist";
////    }
//
////
//    @Test
//    @DisplayName("#4: Testing addStudentDtoListPageWithForm(): should return the right view name and attribute")
//    public void StudentControllerImpl_addStudentDtoListPage_returnTheRightStringAndPassTheRightValue() {
//
//        StudentDto studentDto1 = new StudentDto(1,"Test Name",12,"test@email.com");
//        StudentDto studentDto2 = new StudentDto(2,"Test Name2",122,"test@email2.com");
//
//        List<StudentDto> studentDtoListDto =  new ArrayList<>();
//
//        studentDtoListDto.add(studentDto1);
//        studentDtoListDto.add(studentDto2);
//
////        when()
//
//        String viewName = studentControllerImpl.addStudentDtoListPageWithForm(model);
//
//        assertEquals("/student/addnewstudentlist", viewName, "View name does not match");
//
//        verify(model).addAttribute("studentDtoListDtoForm", studentDtoListDto);
//
////        this.mockMvc
////                .perform(MockMvcRequestBuilders.get("/teachers"))
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.view().name("teacher/teachers"))
////                .andExpect(MockMvcResultMatchers.model().attributeExists("teachers"));
//    }
//
////
////    @PostMapping("/student/addnewstudentlistform")
////    public String addNewStudentListFormAction(@ModelAttribute StudentDtoListDto studentDtoListDto, Model model) throws StudentDtoListDtoInputException {
////        log.info("@Controller addNewStudentListFormAction() was called");
////        if (studentServiceImpl.testIfStudentListDtoInputIsCorrect(studentDtoListDto)) {
////            studentServiceImpl.saveMultipleStudentsAtOnce(studentDtoListDto.getStudentDtoListDto());
////        }
////        model.addAttribute("studentDtoListDto", studentServiceImpl.findAllStudentsButReturnAsStudentDtoList());
////        return "redirect:/student/listallstudents";
////    }
////


}
