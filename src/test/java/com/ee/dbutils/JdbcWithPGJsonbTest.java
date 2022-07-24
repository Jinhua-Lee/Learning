package com.ee.dbutils;

import org.junit.jupiter.api.Test;

public class JdbcWithPGJsonbTest {

    @Test
    public void testSql() {
        String sql = """
                with recursive rec as (select id, org_type, name, parent_id, biz_set
                                       from org_node init
                                       where init.org_type = 1
                                         and init.name = '项目_1'
                                         and biz_set @> '1'
                                       union all
                                       select cur.id, cur.org_type, cur.name, cur.parent_id, cur.biz_set
                                       from org_node cur,
                                            rec records
                                       where records.id = cur.parent_id)
                select id, org_type, name, parent_id, biz_set
                from rec;""";
        SingleJdbcConnectionUtil.printMap(SingleJdbcConnectionUtil.executeStmtQuery(sql));
    }

    @Test
    public void testPsql() {
        String pstSql = """
                with recursive rec as (select id, org_type, name, parent_id, biz_set
                                       from org_node init
                                       where init.org_type = ?
                                         and init.name = ?
                                         and biz_set @> ?::text
                                       union all
                                       select cur.id, cur.org_type, cur.name, cur.parent_id, cur.biz_set
                                       from org_node cur,
                                            rec records
                                       where records.id = cur.parent_id)
                select id, org_type, name, parent_id, biz_set
                from rec;""";
        // 预编译语句不支持jsonb操作符，暂未确定，可能是会话相关的原因。
        SingleJdbcConnectionUtil.printMap(SingleJdbcConnectionUtil.executePstQuery(pstSql, 1, "项目_1", 1));
    }
}