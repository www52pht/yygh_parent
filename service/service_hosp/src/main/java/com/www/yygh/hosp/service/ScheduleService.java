package com.www.yygh.hosp.service;

import com.www.yygh.model.hosp.Schedule;
import com.www.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author www
 * @date 2022年01月10日 23:46
 */
public interface ScheduleService {
    void save(Map<String, Object> paramMap);

    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);
}
