package share.king.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import share.king.entity.Guess;
import share.king.entity.GuessExample;

public interface GuessMapper extends ExtGuessMapper {
    long countByExample(GuessExample example);

    int deleteByExample(GuessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Guess record);

    int insertSelective(Guess record);

    List<Guess> selectByExample(GuessExample example);

    Guess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Guess record, @Param("example") GuessExample example);

    int updateByExample(@Param("record") Guess record, @Param("example") GuessExample example);

    int updateByPrimaryKeySelective(Guess record);

    int updateByPrimaryKey(Guess record);
}