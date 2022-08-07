package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.StudentResult;
import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    UserService userService;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public List<StudentResult> getAllResul() {
        return resultRepository.findAll();
    }

    public Optional<StudentResult> getResultById(Integer id) {
        return resultRepository.findById(id);
    }

    public Integer save(StudentResult s) {
        StudentResult save = resultRepository.save(s);
        return save.getId();
    }


    public void deleteById(Integer id) {
        resultRepository.deleteById(id);
    }

    public List<StudentResult> getResultUsingSubjectId(int assignedSubjectId) {
        List<StudentResult> studentResults = resultRepository.findAll();

        List<StudentResult> studentResultsAll = new ArrayList<>();

        for (StudentResult studentResult : studentResults) {
            if (studentResult.getSubjectId() == assignedSubjectId) {
                studentResultsAll.add(studentResult);
            }
        }
        return studentResultsAll;
    }

    public List<StudentResult> getResultUsingUserEmail(String email) {

        List<Subject> subjectToATeacher = userService.getAllAssignedSubjectToATeacher(email);
        List<StudentResult> studentResults = resultRepository.findAll();

        List<StudentResult> studentResultsAll = new ArrayList<>();

        for (StudentResult studentResult : studentResults) {
            for (Subject subject : subjectToATeacher) {
                if (Objects.equals(studentResult.getSubjectId(), subject.getId())) {
                    studentResultsAll.add(studentResult);
                }
            }
        }
        return studentResultsAll;
    }

    public List<StudentResult> getAllResulWithGrade() {
        List<StudentResult> studentResults = resultRepository.findAll();

        for (StudentResult result : studentResults) {

            Float avgCtMark = (result.getCt3Mark() + result.getCt2Mark() + result.getCt1Mark()) / 3f;
            result.setCtAverageMark(Float.valueOf(df.format(avgCtMark)));

            Float totalMarks = Float.valueOf(df.format(result.getFinalMark() + result.getMidMark() + avgCtMark));
            result.setTotalMark(totalMarks);


            Integer tot = Math.round(totalMarks);
            result.setFinalGrade(getGradesUsingMark(tot));

            resultRepository.save(getSingleResultById(result.getId()));
        }
        return resultRepository.findAll();
    }

    public StudentResult getSingleResultById(Integer id) {
        return resultRepository.findById(id).get();
    }


    public List<Float> getTotalMark() {
        List<StudentResult> studentResults = resultRepository.findAll();
        List<Float> totalMarks = new ArrayList<>();
        Float avgCtMark;
        for (StudentResult result : studentResults) {
            avgCtMark = Float.valueOf((result.getCt3Mark() + result.getCt2Mark() + result.getCt1Mark()) / 3f);
            totalMarks.add(Float.valueOf(df.format(result.getFinalMark() + result.getMidMark() + avgCtMark)));
        }
        return totalMarks;
    }

    public List<Float> getCtAvgMark() {
        List<StudentResult> studentResults = resultRepository.findAll();
        List<Float> avgCtMarks = new ArrayList<>();
        Float avgCtMark;
        for (StudentResult result : studentResults) {
            avgCtMark = Float.valueOf((result.getCt3Mark() + result.getCt2Mark() + result.getCt1Mark()) / 3f);
            avgCtMarks.add(Float.valueOf(df.format(avgCtMark)));
        }
        return avgCtMarks;
    }

    public String getGradesUsingMark(int marks) {

        String grade = "";

        if (marks >= 80 && marks <= 100) {
            grade = "Grade: A+, Point: 4.00, Remarks: Outstanding";
        } else if (marks >= 75 && marks <= 79) {
            grade = "Grade: A, Point: 3.75, Remarks: Excellent";
        } else if (marks >= 70 && marks <= 74) {
            grade = "Grade: A-, Point: 3.50, Remarks: Very Good";
        } else if (marks >= 65 && marks <= 69) {
            grade = "Grade: B+, Point: 3.25, Remarks: Good";
        } else if (marks >= 60 && marks <= 64) {
            grade = "Grade: B, Point: 3.00, Remarks: Satisfactory";
        } else if (marks >= 55 && marks <= 59) {
            grade = "Grade: B-, Point: 2.75, Remarks: Above Average";
        } else if (marks >= 50 && marks <= 54) {
            grade = "Grade: C+, Point: 2.50, Remarks: Average";
        } else if (marks >= 45 && marks <= 49) {
            grade = "Grade: C, Point: 2.25, Remarks: Bellow Average";
        } else if (marks >= 40 && marks <= 44) {
            grade = "Grade: D, Point: 2.00, Remarks: Pass";
        } else if (marks >= 0 && marks <= 39) {
            grade = "Grade: F, Point: 0.00, Remarks: Fail";
        }

        return grade;
    }


    public int totalSubject;
    public Float getTotalMarkOfAllSubject() {
        List<StudentResult> studentResults = getAllResulWithGrade();

        Float totalMarkOfAllSubject = 0f;

        for (StudentResult studentResult : studentResults) {
            totalMarkOfAllSubject = totalMarkOfAllSubject + studentResult.getTotalMark();
            totalSubject++;
        }
        return totalMarkOfAllSubject;
    }
}
