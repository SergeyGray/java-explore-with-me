package ru.practicum.hit;

import ru.practicum.model.HitDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HitMapper {

    public static Hit toHit (HitDto hitDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new Hit(hitDto.getApp(), hitDto.getUri(), hitDto.getIp(),
                LocalDateTime.parse(hitDto.getTimestamp(),formatter));
    }
}
