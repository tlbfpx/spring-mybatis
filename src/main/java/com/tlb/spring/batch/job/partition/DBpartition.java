package com.tlb.spring.batch.job.partition;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


public class DBpartition implements Partitioner {
    private static final String _MINRECORD = "_minRecord";
    private static final String _MAXRECORD = "_maxRecord";
    private static final String MIN_SELECT_PATTERN = "select min({0}) from {1}";
    private static final String MAX_SELECT_PATTERN = "select max({0}) from {1}";
    private String column;
    private String table;

    @Autowired
    @Qualifier("sqlSessionFactoryFrom")
    SqlSessionFactory sqlSessionFactoryFrom;

    public Map<String, ExecutionContext> partition(int gridSize) {

        Map<String, ExecutionContext> resultMap = new HashMap<String, ExecutionContext>();
        //int min = sqlSessionFactoryFrom.openSession().selectOne(MessageFormat.format(MIN_SELECT_PATTERN, new Object[]{column, table}));
        //int max = sqlSessionFactoryFrom.openSession().selectOne(MessageFormat.format(MAX_SELECT_PATTERN, new Object[]{column, table}));
        int min = sqlSessionFactoryFrom.openSession().selectOne("findMinUserId");
        int max = sqlSessionFactoryFrom.openSession().selectOne("findMaxUserId");

        int targetSize = (max - min) / gridSize + 1;
        int number = 0;
        int start = min;
        int end = start + targetSize - 1;

        while (start <= max) {
            ExecutionContext context = new ExecutionContext();
            if (end >= max) {
                end = max;
            }
            context.putInt(_MINRECORD, start);
            context.putInt(_MAXRECORD, end);
            start += targetSize;
            end += targetSize;
            resultMap.put("partition" + (number++), context);
        }
        return resultMap;
    }


    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }


}
