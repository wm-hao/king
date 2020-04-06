create table trade_record
(
    id          int auto_increment
        primary key,
    name        varchar(100) null comment '股票名称',
    alias       varchar(10)  null comment '拼音前缀',
    code        varchar(200) null comment '股票代码',
    user_id     int          not null comment '所属用户',
    buy_time    timestamp    null comment '买入时间',
    buy_price   int          null comment '买入价格单位厘',
    buy_count   int          null comment '买入数量',
    sell_time   timestamp    null comment '卖出时间',
    sell_price  int          null comment '卖出价格单位厘',
    open_price  int          null comment '开盘价单位厘',
    close_price int          null comment '收盘价单位厘',
    high_price  int          null comment '最高价单位厘',
    low_price   int          null comment '最低价单位厘',
    create_date timestamp    null comment '创建时间',
    state       int          null comment '状态'
);
