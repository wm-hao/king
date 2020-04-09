package share.king.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import share.king.dto.DailyQry;
import share.king.dto.Response;
import share.king.entity.Daily;
import share.king.service.interfaces.IDailySV;
import share.king.util.Common;
import share.king.util.GateWayUtil;

import java.util.List;

@RestController
@RequestMapping("/daily")
public class DailyController {

    @Autowired
    private IDailySV dailySV;

    @PostMapping("select")
    public Response select(@RequestBody DailyQry dailyQry) {
        if (dailyQry == null || dailyQry.getUserId() == null) {
            return GateWayUtil.returnFailResponse("入参不能为空");
        }
        Response response = GateWayUtil.returnSuccessResponse("查询成功");
        PageInfo<Daily> dailies = dailySV.qryDailyByPage(dailyQry);
        List<Daily> dailyList = dailies.getList();
        response.setRows(dailyList);
        response.setTotal(dailies.getTotal());
        return response;
    }

    @PostMapping("insert")
    public Response insert(@RequestBody Daily daily) {
        if (dailySV.insert(daily) == Common.StatusCode.SUCCESS.getCode()) {
            return GateWayUtil.returnSuccessResponse("保存成功");
        }
        return GateWayUtil.returnFailResponse("保存失败");
    }

    @PostMapping("update")
    public Response update(@RequestBody Daily daily) {
        if (dailySV.updateSelective(daily) == Common.StatusCode.SUCCESS.getCode()) {
            return GateWayUtil.returnSuccessResponse("更新成功");
        }
        return GateWayUtil.returnFailResponse("更新失败");
    }
}
