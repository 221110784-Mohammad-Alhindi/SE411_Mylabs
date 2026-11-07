package edu.psu.se411.lab14.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.psu.se411.lab14.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}