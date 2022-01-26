package io.hibernate.entity

import org.hibernate.annotations.ManyToAny

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "course")
class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id

    @Column(name = "title")
    String title

    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH])
    @JoinColumn(name="instructor_id")
    Instructor instructor


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="course_id")
    private List<Review> reviews

    Course() {
    }

    Course(String title) {
        this.title = title
    }


    void addReview(Review theReview) {
        if (reviews == null) {
            reviews = new ArrayList<>()
        }

        reviews.add(theReview)
    }


    @Override
    String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    List<Review> getReviews() {
        return reviews
    }

    void setReviews(List<Review> reviews) {
        this.reviews = reviews
    }
}
