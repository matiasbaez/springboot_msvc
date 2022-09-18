package org.matiasbaez.springcloud.msvc.courses.msvccourses.repositories;

import org.matiasbaez.springcloud.msvc.courses.msvccourses.models.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
