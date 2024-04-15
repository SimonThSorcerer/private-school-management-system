package springbootproject.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
//
//    @Mock
//    private TeacherRepository teacherRepository;
//    @InjectMocks
//    private TeacherServiceImpl teacherService;
//
//    @Test
//    public void TeacherService_saveTeacher_SaveTeacherReturnsOneTeacher() {
//        Teacher expected = new Teacher(1, "Juan",
//                "juan@mail.com");
//
//        when(teacherRepository.save(new Teacher(1, "Juan", "juan@mail.com")))
//                .thenReturn(expected);
//
//        Teacher result = teacherService.saveTeacher(
//                new TeacherDto(1, "Juan", "juan@mail.com")
//                );
//
//        Assertions.assertEquals(expected, result);
//    }
//
//    @Test
//    public void TeacherService_getTeacherById_returnsTeacher() {
//
//        Teacher teacher = new Teacher(1, "Jon", "jon@mail.com");
//        teacherRepository.save(teacher);
//
//        when(teacherRepository.findById(1))
//                        .thenReturn(Optional.of(new Teacher(1, "Jon", "jon@mail.com")));
//
//        assertEquals(teacher, teacherService.getTeacherById(1));
//    }
//
//    @Test
//    public void TeacherService_getTeacherById_nonExistingIdThrowsException() {
//
//        assertThrows(TeacherWithSuchAnIdDoesNotExistException.class,
//                () -> teacherService.getTeacherById(5));
//
//    }
//
//    @Test
//    public void TeacherService_deleteTeacherById_deletesTeacher() {
//        TeacherDto teacherDto = TeacherDto.builder().courseDtoId(1)
//                .name("Jon").email("jon@mail.com").build();
//
//        TeacherDto teacherDto2 = TeacherDto.builder().courseDtoId(2)
//                .name("Jane").email("jane@mail.com").build();
//
//
//        when(teacherService.saveTeacher(teacherDto))
//                .thenReturn(new Teacher(1, "Jon", "jon@mail.com"));
//
//        when(teacherService.saveTeacher(teacherDto2))
//                .thenReturn(new Teacher(2, "Jane", "jane@mail.com"));
//
//        teacherService.saveTeacher(teacherDto);
//        teacherService.saveTeacher(teacherDto2);
//
////       Error when I use this line below: unnecessary stubbings
////        when(teacherRepository.save(teacher)).thenReturn(teacher);
//
//        assertAll(() -> teacherService.deleteTeacherById(3));
//        assertTrue(teacherService.getAllTeachers().isEmpty());
//
//    }

}