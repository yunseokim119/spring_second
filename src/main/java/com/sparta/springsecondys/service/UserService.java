package com.sparta.springsecondys.service;

import com.sparta.springsecondys.entity.Schedule;
import com.sparta.springsecondys.entity.User;
import com.sparta.springsecondys.entity.UserSchedule;
import com.sparta.springsecondys.repository.ScheduleRepository;
import com.sparta.springsecondys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        user.setCreatedDate(LocalDateTime.now());
        user.setModifiedDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setModifiedDate(LocalDateTime.now());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다. ID: " + id));
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("유저를 찾을 수 없습니다. ID: " + id);
        }
    }

    public Schedule assignUsersToSchedule(Long scheduleId, List<Long> userIds) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다. ID: " + scheduleId));

        for (Long userId : userIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다. ID: " + userId));

            UserSchedule userSchedule = new UserSchedule(user, schedule);
            schedule.getUserSchedules().add(userSchedule);
        }

        return scheduleRepository.save(schedule);
    }
}
