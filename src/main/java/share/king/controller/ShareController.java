package share.king.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.king.dto.Response;
import share.king.entity.ShareEntity;
import share.king.service.interfaces.IShareSV;
import share.king.util.Common;
import share.king.util.GateWayUtil;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/share")
public class ShareController {
    private static transient Log log = LogFactory.getLog(ShareController.class);
    private static final int PAGE_SIZE_MAX = 50;
    private static final int PAGE_SIZE_MIN = 1;
    private static final int PAGE_NUM_MIN = 0;

    @Autowired
    private IShareSV shareSV;

    @GetMapping("list")
    public List<ShareEntity> list() {
        return shareSV.selectAll();
    }

    @PostMapping("insert")
    public Response insert(@RequestBody ShareEntity shareEntity) {
        log.info("插入SHARE:" + shareEntity);
        if (shareSV.insert(shareEntity) == Common.StatusCode.SUCCESS.getCode()) {
            return GateWayUtil.returnSuccessResponse("保存成功");
        }
        return GateWayUtil.returnFailResponse("保存失败");
    }

    @GetMapping("list/page")
    public Response page(@RequestParam int pageNum, @RequestParam int pageSize) {
        if (pageNum < PAGE_NUM_MIN) {
            pageNum = PAGE_NUM_MIN;
        }
        if (pageSize < PAGE_SIZE_MIN) {
            pageSize = PAGE_SIZE_MIN;
        }
        if (pageSize > PAGE_SIZE_MAX) {
            pageSize = PAGE_SIZE_MAX;
        }
        PageInfo<ShareEntity> pageInfo = shareSV.selectByPage(pageNum * pageSize, pageSize);
        Response response = GateWayUtil.returnSuccessResponse("查询成功");
        response.setRows(pageInfo.getList());
        response.setTotal(pageInfo.getTotal());
        return response;
    }


}
