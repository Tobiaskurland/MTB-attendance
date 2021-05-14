package com.example.demo.Service;

import com.example.demo.Repository.ICourseRepo;
import com.example.demo.Repository.ILectureRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class LectureServiceImplTest
{
    @Mock
    ILectureRepo lectureRepo;

    @Mock
    ICourseRepo courseRepo;

    @InjectMocks
    LectureServiceImpl lectureService;

    @Test
    void findLecturesByCourseIdForWeek()
    {
    }
}