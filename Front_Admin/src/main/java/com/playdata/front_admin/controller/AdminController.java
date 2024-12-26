package com.playdata.front_admin.controller;

import com.playdata.front_admin.dto.TableDTO;
import com.playdata.front_admin.entity.User;
import com.playdata.front_admin.service.ProfileImg;
import com.playdata.front_admin.service.TableService;
import com.playdata.front_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final TableService tableService;
    private final ProfileImg profileImg;

    @Autowired
    public AdminController(UserService userService, TableService tableService, ProfileImg profileImg) {
        this.userService = userService;
        this.tableService = tableService;
        this.profileImg = profileImg;
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "/user/login";
    }



    // 회원가입 페이지
    @GetMapping("/join")
    public String joinPage() {
        return "/join/register";
    }

    @PostMapping("/join")
    public String userRegister(@RequestParam String name, @RequestParam String nickname,
                               @RequestParam String email, @RequestParam String pass,
                               @RequestParam String confirmPass, Model model) {
        if (!pass.equals(confirmPass)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/join";
        }
        User newUser = new User();
        newUser.setName(name);
        newUser.setNickname(nickname);
        newUser.setEmail(email);
        newUser.setPassword(pass);
        return "redirect:/admin/wellcome";
    }



    @GetMapping("/wellcome")
    public String welcomePage(@PathVariable Long id, Model model) {
        Optional<User> user = userService.findById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "/join/wellcome";
    }


    // 회원 목록
    @GetMapping("/list")
    public String listUsers(@RequestParam(defaultValue = "0") String pageNo, Model model) {
        int page = Integer.parseInt(pageNo);
        int pageSize = 10;
        Pageable pageable = tableService.getPageable(page, pageSize);
        Page<User> userPage = userService.userPage(pageable);
        TableDTO tableDTO = tableService.getPageInfo(userPage);
        model.addAttribute("userList", userPage.getContent());
        model.addAttribute("currentPage", tableDTO.getCurrentPage());
        model.addAttribute("totalPages", tableDTO.getTotalPages());
        model.addAttribute("prevPage", tableDTO.getPrevPage());
        model.addAttribute("nextPage", tableDTO.getNextPage());
        model.addAttribute("startIndex", page * pageSize);
        return "/user/list";
    }

    // 회원 상세
    @GetMapping("/mypage/{id}")
    public String userDetailPage(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "/user/mypage";
        } else {
            model.addAttribute("error", "사용자를 찾을 수 없습니다.");
            return "/user/mypage_edit";
        }
    }

    // 회원 삭제
    @PostMapping("list/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model) {
        try {
            userService.deleteById(id); // 사용자 삭제
        } catch (Exception e) {
            model.addAttribute("error", "사용자를 삭제하는 중 문제가 발생했습니다.");
            return "redirect:/admin/list?error=true"; // 에러 처리 후 리다이렉트
        }
        return "redirect:/admin/list"; // 성공 시 목록 페이지로 리다이렉트
    }

    // 회원 수정 페이지
    @GetMapping("/mypage/{id}/edit")
    public String updateUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "/user/mypage_edit";
        } else {
            model.addAttribute("error", "사용자를 찾을 수 없습니다.");
            return "/user/mypage_edit";
        }
    }

    // 회원 정보 수정
    @PostMapping("/mypage/{id}/edit")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user,
                             @RequestParam(value = "image", required = false) MultipartFile image,
                             Model model) throws IOException {
        User existingUser = userService.findById(id).orElse(null);
        if (existingUser != null) {
            // 기존 이미지 삭제
            if (existingUser.getProfileImg() != null && !existingUser.getProfileImg().isEmpty()) {
                profileImg.deleteFile(existingUser.getProfileImg());
            }

            // 새 이미지 업로드
            if (image != null && !image.isEmpty()) {
                String imagePath = profileImg.updateFile(image, existingUser.getProfileImg());
                existingUser.setProfileImage(imagePath);
            }

            // 사용자 정보 업데이트
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAddr(user.getAddr());
            existingUser.setNickname(user.getNickname());
            existingUser.setBirthDate(user.getBirthDate());
            existingUser.setSex(user.getSex());
            existingUser.setPhone(user.getPhone());

            userService.save(existingUser);
            model.addAttribute("user", existingUser);

            return "redirect:/admin/mypage/" + id;  // 수정 완료 후 상세 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "사용자를 찾을 수 없습니다.");
            return "/user/mypage_edit";
        }
    }
}
