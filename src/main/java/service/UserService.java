package service;

import entity.User;
import exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {

    public UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 유저 저장
     * @param user
     * @return
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 유저 조회 (이메일)
     * @param email
     * @return
     */
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /***
     * 유저 조회 (id)
     * @param id
     * @return
     */
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * 유저 정보 수정
     * @param id, userDetails
     */
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found : " + id));

        BeanUtils.copyProperties(userDetails, user, "id"); // id 제외, 나머지 복사

        return userRepository.save(user);
    }

    /**
     * 유저 삭제
     * @param id
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }




}
