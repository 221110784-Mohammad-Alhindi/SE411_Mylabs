package edu.psu.se411.lab14.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.psu.se411.lab14.model.Course;
import edu.psu.se411.lab14.services.CourseService;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET: Retrieve all courses
    @GetMapping
    public ResponseEntity<Iterable<Course>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    // GET: Retrieve a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        return courseService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Create a new course
    @PostMapping
    public ResponseEntity<Course> create(
            @jakarta.validation.Valid @RequestBody Course course) {

        Course savedCourse = courseService.save(course);

        URI location = URI.create("/api/v1/courses/" + savedCourse.id());

        return ResponseEntity.created(location).body(savedCourse);
    }

    // PUT: Update an existing course
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestBody Course course) {

        if (!courseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Course updatedCourse = new Course(
                id,
                course.code(),
                course.name(),
                course.description(),
                course.credits()
        );

        courseService.save(updatedCourse);

        return ResponseEntity.noContent().build();
    }

    // DELETE: Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!courseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        courseService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}