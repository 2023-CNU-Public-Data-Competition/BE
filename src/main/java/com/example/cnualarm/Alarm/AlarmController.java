package com.example.cnualarm.Alarm;

import com.example.cnualarm.Dto.AlarmSetingDTO;
import com.example.cnualarm.Dto.AlarmsDTO;
import com.example.cnualarm.repository.AlarmRepository;
import com.example.cnualarm.security.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlarmController {

    @Autowired
    AlarmService alarmService;
    @Autowired
    Jwt jwt;

    //Test 위해 선언해둠
    @Autowired
    AlarmRepository alarmRepository;
    @GetMapping("test")
    public long test() {
        long a = alarmRepository.count();
        return a;
    }

    @GetMapping("/new-alarms")
    public List<AlarmsDTO> getNewAlarms(@RequestHeader("Authorization") String token) {
        Jwt.Claims claims =  jwt.verify(token);
        String user_id = claims.getUsername();
        return alarmService.getNewAlarms(user_id);
    }

    @GetMapping("/alarms")
    public List<AlarmsDTO> getAlarms(@RequestHeader("Authorization") String token) {
        Jwt.Claims claims =  jwt.verify(token);
        String user_id = claims.getUsername();
        return alarmService.getAlarms(user_id);
    }

    @GetMapping("/alarm_setting")
    public List getAlarmSettings(@RequestHeader("Authorization") String token, @RequestParam String alarm_type) {
        Jwt.Claims claims =  jwt.verify(token);
        String user_id = claims.getUsername();
        return alarmService.getAlarmsSettings(user_id, alarm_type);
    }



//    @PutMapping("/alarm_setting")
//    public ResponseEntity<String> updateAlarmSetting(@RequestBody AlarmSetingDTO alarmSetingDTO) {
//        String a = alarmService.updateAlarmSetting(alarmSetingDTO);
//        return ResponseEntity.ok("Request handled successfully.");
//    }
}
