package com.springbootproject.service;

import com.springbootproject.exception.ElementNotFoundException;
import com.springbootproject.object.Course;
import com.springbootproject.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService{
    private final CourseRepository courseRepository;

    @Autowired
    public AdminServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Course show(int id) {
        return courseRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Course not found with courseId: " + id));
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void delete(int id) {
        courseRepository.deleteById(id);
    }
}
