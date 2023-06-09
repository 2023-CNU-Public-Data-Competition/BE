package com.example.cnualarm.Alarm;

import com.example.cnualarm.Dto.AlarmSetingDTO;
import com.example.cnualarm.Dto.AlarmsDTO;
import com.example.cnualarm.Entity.AlarmEntity;
import com.example.cnualarm.Entity.AlarmSettingEntity;
import com.example.cnualarm.Utils.EntityConverter;
import com.example.cnualarm.repository.AlarmRepository;
import com.example.cnualarm.repository.AlarmSettingRepository;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AlarmService {
    @Autowired
    AlarmRepository alarmRepository;

    @Autowired
    AlarmSettingRepository alarmSettingRepository;

    public List<AlarmsDTO> getAlarms(String id) {
        Collection<AlarmEntity> alarmEntities = alarmRepository.findByuserId(id);
        List<AlarmsDTO> alarmsDTOList = new ArrayList<>();
        for(AlarmEntity alarmEntity : alarmEntities) {
            AlarmsDTO alarmsDTO = EntityConverter.alarmsToDTO(alarmEntity);
            alarmsDTOList.add(alarmsDTO);
        }
        return alarmsDTOList;
    }

    public List getAlarmsSettings(String id, String alarm_type) {
        List<AlarmSettingEntity> Result = alarmSettingRepository.findByUserIdAndAlarmType(id, alarm_type);
        List return_list = new ArrayList();
        for(AlarmSettingEntity item : Result) {
            return_list.add(item.getAlarmName());
        }

        return return_list;
    }

    public String updateAlarmSetting(AlarmSetingDTO alarmSetingDTO) {
        String id = alarmSetingDTO.getId();
        String alarmType = alarmSetingDTO.getAlarmType();
        List<String> alarmNames = alarmSetingDTO.getList();

        System.out.println(id);
        System.out.println(alarmType);
        System.out.println(alarmNames);

        return "a";
    }
}
