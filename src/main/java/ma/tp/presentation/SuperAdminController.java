package ma.tp.presentation;

import java.nio.file.attribute.PosixFilePermission;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ma.tp.domaine.RoleVo;
import ma.tp.domaine.UserVo;
import ma.tp.model.Role;
import ma.tp.model.User;
import ma.tp.service.IUserService;

@Controller
@RequestMapping("/superadmin")
public class SuperAdminController {

    @Autowired
    IUserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(path = { "", "/" })
    public ModelAndView root(@ModelAttribute("userVo") UserVo userVo) {
        ModelAndView mv = new ModelAndView();
        List<UserVo> userList = userService.findUserHasRoleAdminAndClient();
        List<RoleVo> roleList = userService.getAllRoles();
        mv.addObject("users", userList);            
        mv.addObject("rolelist", roleList);
        if (userVo == null)
            mv.addObject("userVo", userVo);
        mv.setViewName("/superAdmin/superadmin");
        return mv;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id, RedirectAttributes ra) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/superadmin");
        UserVo user = userService.findUserById(id);
        System.out.println();
        ra.addFlashAttribute("userVo", user);
        return mv;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newUser(RedirectAttributes ra) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/superadmin");
        UserVo u = new UserVo();
        u.setId(new Long("-1"));
        ra.addFlashAttribute("userVo", u);
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("userVo") UserVo uservo) {
        ModelAndView mv = new ModelAndView(); 
        userService.add(uservo);
        mv.setViewName("redirect:/superadmin");
        return mv;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("userVo") UserVo uservo) {
        ModelAndView mv = new ModelAndView();
        userService.update(uservo);
        mv.setViewName("redirect:/superadmin");
        return mv;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/superadmin");
        userService.delete(id);
        return mv;
    }

}