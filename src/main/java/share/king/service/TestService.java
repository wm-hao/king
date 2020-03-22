package share.king.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.king.dao.ShareEntityMapper;

@Service
public class TestService {
    @Autowired
    ShareEntityMapper shareEntityMapper;

    public ShareEntityMapper getShareEntityMapper() {
        return shareEntityMapper;
    }

    public void setShareEntityMapper(ShareEntityMapper shareEntityMapper) {
        this.shareEntityMapper = shareEntityMapper;
    }
}
