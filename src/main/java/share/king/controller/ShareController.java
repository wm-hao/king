package share.king.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.king.entity.ShareEntity;
import share.king.service.interfaces.IShareSV;
import share.king.util.Common;

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

    @PostMapping("insert")
    public String insert(@RequestBody ShareEntity shareEntity) {
        if (shareSV.insert(shareEntity) == Common.StatusCode.SUCCESS.getCode()) {
            return Common.StatusCode.SUCCESS.getDesc();
        }
        return Common.StatusCode.FAIL.getDesc();
    }

}
