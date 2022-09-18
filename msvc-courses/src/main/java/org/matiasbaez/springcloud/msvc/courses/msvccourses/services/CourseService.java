package org.matiasbaez.springcloud.msvc.courses.msvccourses.services;

import org.matiasbaez.springcloud.msvc.courses.msvccourses.models.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> list();

    Optional<Course> getById(Long id);

    Course save(Course course);

    void delete(Long id);

}
