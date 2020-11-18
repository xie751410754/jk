package com.xzq.module_base.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * PushingDto
 * Created by xzq on 2019/8/27.
 */
public class PushingDto {
    public int id;
    public String trainingTitle;
    public String trainingIntroduction;
    public String trainingContent;
    public String trainingImg;

    public static List<PushingDto> getTest() {
        List<PushingDto> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            PushingDto dto = new PushingDto();
            dto.id = i;
            dto.trainingTitle = "如何成为推手？ " + i;
            dto.trainingIntroduction = "如何成为推手如何成为推手如何成为推手如何成为推手如何成为推手如何成为推手";
            dto.trainingImg = "http://seopic.699pic.com/photo/50030/8611.jpg_wh1200.jpg";
            list.add(dto);
        }
        return list;
    }
}
