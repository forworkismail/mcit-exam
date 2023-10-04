package com.example.demo.user;

import com.example.demo.department.DepartmentRepository;
import com.example.demo.user.enums.UserRole;
import com.example.demo.user.requests.CreateUserRequest;
import com.example.demo.user.responses.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTest() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("admin");
        mockUser.setRole(UserRole.ADMINISTRATORS);

        when(userRepository.findAll()).thenReturn(Arrays.asList(mockUser));

        List<UserResponse> users = userService.findAll();
        assertEquals(1, users.size());
        assertEquals("admin", users.get(0).username());
    }

    @Test
    void findByUsernameTest() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("worker");
        mockUser.setRole(UserRole.DEPARTMENT_MEMBERS);

        when(userRepository.findByUsername("worker")).thenReturn(Optional.of(mockUser));

        UserResponse userResponse = userService.findByUsername("worker");
        assertNotNull(userResponse);
        assertEquals("worker", userResponse.username());
    }

    @Test
    void saveTest() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("approver");
        mockUser.setPassword("abc");
        mockUser.setRole(UserRole.APPROVERS);

        when(userRepository.save(any())).thenReturn(mockUser);

        var request = new CreateUserRequest(
                "approver",
                UserRole.APPROVERS.toString(),
                "abc"
        );


        UserResponse userResponse = userService.save(request);
        assertNotNull(userResponse);
        assertEquals("approver", userResponse.username());
    }
}