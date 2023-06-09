package com.example.cnualarm.Alarm;

import com.example.cnualarm.Dto.AlarmSetingDTO;
import com.example.cnualarm.Dto.AlarmsDTO;
import com.example.cnualarm.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlarmController {

    @Autowired
    AlarmService alarmService;

    //Test 위해 선언해둠
    @Autowired
    AlarmRepository alarmRepository;
    @GetMapping("test")
    public long test() {
        long a = alarmRepository.count();
        return a;
    }

    //TODO : RequestHeader로 token 받아서 고치기
    @GetMapping("/alarms")
    public List<AlarmsDTO> getAlarms(@RequestParam String id) {
        return alarmService.getAlarms(id);
    }

    //TODO : RequestHeader로 token 받아서 고치기
    @GetMapping("/alarm_setting")
    public List getAlarmSettings(@RequestParam String id, @RequestParam String alarm_type) {
        return alarmService.getAlarmsSettings(id, alarm_type);
    }

    //TODO : RequestHeader로 token 받아서 고치기
    @PutMapping("/alarm_setting")
    public ResponseEntity<String> updateAlarmSetting(@RequestBody AlarmSetingDTO alarmSetingDTO) {
        String a = alarmService.updateAlarmSetting(alarmSetingDTO);
        return ResponseEntity.ok("Request handled successfully.");
    }
}
