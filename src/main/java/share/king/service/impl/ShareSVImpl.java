package share.king.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.king.dao.ShareEntityMapper;
import share.king.entity.ShareEntity;
import share.king.service.interfaces.IShareSV;

import java.util.Date;
import java.util.List;

@Service
public class ShareSVImpl implements IShareSV {

    @Autowired
    private ShareEntityMapper shareEntityMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return shareEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ShareEntity record) {
        record.setCreateDate(new Date());
        return shareEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(ShareEntity record) {
        return insertSelective(record);
    }

    @Override
    public ShareEntity selectByPrimaryKey(Integer id) {
        return shareEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ShareEntity record) {
        return shareEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ShareEntity record) {
        return shareEntityMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ShareEntity> selectAll() {
        return shareEntityMapper.selectAll();
    }

    @Override
    public PageInfo<ShareEntity> selectByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShareEntity> shareEntities = shareEntityMapper.selectAll();
        return new PageInfo<>(shareEntities);
    }
}
