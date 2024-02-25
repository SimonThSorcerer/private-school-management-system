package springbootproject.controller;

import com.springbootproject.controller.TeacherController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {
//    @MockBean
//    private TeacherService teacherService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void TeacherController_listTeachers_returnsTeachers() throws Exception {
//        List<Teacher> teacherList = new ArrayList<>();
//        teacherList.add(new Teacher(1, "Jon", "jon@mail.com"));
//        teacherList.add(new Teacher(2, "Jane", "jane@mail.com"));
//
//        when(teacherService.getAllTeachers()).thenReturn(teacherList);
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.get("/teachers"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("teacher/teachers"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("teachers"));
//    }
}