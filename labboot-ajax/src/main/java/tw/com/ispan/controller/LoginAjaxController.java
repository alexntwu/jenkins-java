package tw.com.ispan.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.domain.CustomerBean;
import tw.com.ispan.service.CustomerService;


@RestController
@RequestMapping("/ajax/secure")
@CrossOrigin
public class LoginAjaxController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public String login(@RequestBody String json) {
        JSONObject responseJson = new JSONObject();

        //接收資料
        JSONObject obj = new JSONObject(json);
        String username = obj.isNull("username") ? null : obj.getString("username");
        String password = obj.isNull("password") ? null : obj.getString("password");

        //驗證資料
        if(username==null || username.length()==0 || password==null || password.length()==0) {
            responseJson.put("success", false);
            responseJson.put("message", "請輸入帳號/密碼以便執行登入");
            return responseJson.toString();
        }

        //呼叫企業邏輯
        CustomerBean bean = customerService.login(username, password);
        if(bean==null) {
            responseJson.put("success", false);
            responseJson.put("message", "登入失敗");
        } else {
            responseJson.put("success", true);
            responseJson.put("message", "登入成功");
        }

        return responseJson.toString();
    }
}
