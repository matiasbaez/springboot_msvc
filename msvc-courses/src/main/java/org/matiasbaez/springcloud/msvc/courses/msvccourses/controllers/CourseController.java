package org.matiasbaez.springcloud.msvc.courses.msvccourses.controllers;

import org.matiasbaez.springcloud.msvc.courses.msvccourses.models.entity.Course;
import org.matiasbaez.springcloud.msvc.courses.msvccourses.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> list() {
        return ResponseEntity.ok(courseService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Course> optionalCourse = courseService.getById(id);
        if (optionalCourse.isPresent()) return ResponseEntity.ok(optionalCourse.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Course course, @PathVariable Long id) {
        Optional<Course> optionalCourse = courseService.getById(id);
        if (optionalCourse.isPresent()) {
            Course courseObj = optionalCourse.get();
            courseObj.setName(course.getName());

            return ResponseEntity.status(HttpStatus.OK).body(courseService.save(courseObj));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Course> optionalCourse = courseService.getById(id);
        if (optionalCourse.isPresent()) {
            courseService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
