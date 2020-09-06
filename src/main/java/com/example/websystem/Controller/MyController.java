package com.example.websystem.Controller;

import com.example.websystem.mapper.KnowledgeMapper;
import com.example.websystem.mapper.UserMapper;
import com.example.websystem.model.Knowledge;
import com.example.websystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private KnowledgeMapper knowledgeMapper;
    @GetMapping("/login")
    private String reg(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String,Object> map){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userMapper.login(username,password);
        if(user!=null){
            return "index";
        }else {
            map.put("msg1","用户名或密码输入错误，请重新输入！");
            return "login";
        }
    }
    @RequestMapping("/register")
    public String register(HttpServletRequest request,Map<String,Object> map){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User user1 = userMapper.getuser(username);
        if(user1!=null){
            map.put("msg2","该用户名已被注册，请重新输入");
            return "register";
        }else if ("".equals(password)){
            map.put("msg2","密码不能为空，请重新输入");
            return "register";
        }else{
            userMapper.adduser(user);
            return "login";
        }
    }
    @RequestMapping("/goreg")
    public String goregister(HttpServletRequest request,Map<String,Object> map){
        return "register";
    }
    @RequestMapping("/gotomanage")
    public String gotomanage(HttpServletRequest request,Map<String,Object> map){
        return "manage";
    }
    @RequestMapping("/gotoindex")
    public String gotoindex(HttpServletRequest request,Map<String,Object> map){
        return "index";
    }
    @RequestMapping("/goarray")
    public String gotoarray(HttpServletRequest request,Map<String,Object> map) {return "array";}
    @RequestMapping("/gostack_queue")
    public String gotostack_queue(HttpServletRequest request,Map<String,Object> map) {return "stack_queue";}
    @RequestMapping("/gotree")
    public String gototree(HttpServletRequest request,Map<String,Object> map) {return "tree";}
    @RequestMapping("/goLinear_linked_lists")
    public String gotoLinear_linked_lists(HttpServletRequest request,Map<String,Object> map) {return "Linear_linked_lists";}
    @RequestMapping("/gograph")
    public String gotograph(HttpServletRequest request,Map<String,Object> map) {return "graph";}
    @RequestMapping("/gosort")
    public String gotosort(HttpServletRequest request,Map<String,Object> map) {return "sort";}
    @RequestMapping("/searchknowledge")
    public  String searchknowledge(HttpServletRequest request,Map<String ,Object> map){
        String knowledgename = request.getParameter("knowledgename");
        Knowledge knowledge = knowledgeMapper.getknowledge(knowledgename);
        if(knowledge!=null)
        {
            map.put("msg5","知识点信息：" + knowledge);
            return "redirect:/manage";
        }else {
            map.put("msg5","该知识点不存在！");
            return "redirect:/manage";
        }
    }
    @RequestMapping("/addknowledge")
    public String addknowledge(HttpServletRequest request,Map<String,Object> map){
        String knowledgename = request.getParameter("knowledgename");
        String definition = request.getParameter("content");
        String feature = request.getParameter("feature");
        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledgename(knowledgename);
        knowledge.setDefinition(definition);
        knowledge.setFeature(feature);
        Knowledge knowledge1 = knowledgeMapper.getknowledge(knowledgename);
        if(knowledge1!=null){
            map.put("msg3","该知识点已存在，请重新输入.");
            return "redirect:/manage";
        }else {
            knowledgeMapper.addknowledge(knowledge);
            map.put("msg3","知识点添加成功！");
            return "redirect:/manage";
        }
    }
    @RequestMapping("/updateknowledge")
    public String updateknowledge(HttpServletRequest request,Map<String,Object> map){
        String knowledgename = request.getParameter("knowledgename");
        String definition = request.getParameter("definition");
        String feature = request.getParameter("feature");
        Knowledge knowledge = knowledgeMapper.getknowledge(knowledgename);
        if(knowledge!=null){
            knowledgeMapper.updateknowledge(knowledgename,definition,feature);
            map.put("msg4","知识点内容已经更新！");
            return "redirect:/manage";
        }else {
            map.put("msg4","该知识点不存在，请去添加该知识点！");
            return "redirect:/manage";
        }
    }
    @RequestMapping("/deleteknowledge")
    public  String deleteknowledge(HttpServletRequest request,Map<String ,Object> map){
        String knowledgename = request.getParameter("knowledgename");
        Knowledge knowledge = knowledgeMapper.getknowledge(knowledgename);
        if(knowledge!=null){
            knowledgeMapper.deleteknowledge(knowledgename);
            map.put("msg5","删除知识点成功！");
            return "redirect:/manage";
        }else {
            map.put("msg5","该知识点不存在！");
            return "redirect:/manage";
        }
    }
//       表格实现
     @RequestMapping(value = "manage",method = RequestMethod.GET)
     public String showlist(Model model){
        List<Knowledge> knowledges = knowledgeMapper.getAllKnowledge();
        model.addAttribute("knowledges",knowledges);
        model.addAttribute("count",knowledgeMapper.knowledgecount());
        return "manage";
    }
}
