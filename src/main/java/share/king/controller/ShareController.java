package share.king.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.king.entity.ShareEntity;
import share.king.service.interfaces.IShareSV;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/share")
public class ShareController {

    @Autowired
    private IShareSV shareSV;

    @GetMapping("list")
    public List<ShareEntity> list() {
        return shareSV.selectAll();
    }

    @PostMapping("/insert")
    public String insert(ShareEntity shareEntity) {
        if (shareSV.insert(shareEntity) == 1) {
            return "插入成功";
        }
        return "插入失败";
    }

    @PostMapping(value = "insert/json", consumes = "application/json")
    public String insertJson(@RequestBody ShareEntity shareEntity) {
        if (shareSV.insert(shareEntity) == 1) {
            return "插入成功";
        }
        return "插入失败";
    }
}
