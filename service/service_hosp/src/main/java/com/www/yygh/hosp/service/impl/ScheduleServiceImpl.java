package com.www.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.www.yygh.hosp.mapper.xml.ScheduleMapper;
import com.www.yygh.hosp.repository.DepartmentRepository;
import com.www.yygh.hosp.repository.ScheduleRepository;
import com.www.yygh.hosp.service.ScheduleService;
import com.www.yygh.model.hosp.Schedule;
import org.bson.json.StrictCharacterStreamJsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author www
 * @date 2022年01月10日 23:47
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    /**
     * 上传排班接口
     *
     * @param paramMap
     */
    @Override
    public void save(Map<String, Object> paramMap) {
        //paramMap 转换成department对象
        String paramMapString = JSONObject.toJSONString(paramMap);
        Schedule schedule = JSONObject.parseObject(paramMapString, Schedule.class);

        //根据医院编号 和 排班编号查询
        Schedule scheduleExist = scheduleRepository.
                getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(), schedule.getHosScheduleId());

        //判断
        if (scheduleExist != null) {
            scheduleExist.setUpdateTime(new Date());
            scheduleExist.setIsDeleted(0);
            scheduleExist.setStatus(1);
            scheduleRepository.save(scheduleExist);
        } else {
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            schedule.setStatus(1);
            scheduleRepository.save(schedule);

        }

    }
}
