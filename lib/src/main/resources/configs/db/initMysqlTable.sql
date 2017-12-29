CREATE TABLE IF NOT EXISTS user (
  user_id      BIGINT UNSIGNED NOT NULL         AUTO_INCREMENT
  COMMENT '用户Id(主键)',
  username     VARCHAR(20)     NOT NULL
  COMMENT '用户名',
  nickname     VARCHAR(50)
  COMMENT '昵称',
  password     VARCHAR(255)    NOT NULL
  COMMENT '密码',
  role         VARCHAR(10)     NOT NULL
  COMMENT '角色',
  tel          VARCHAR(11)
  COMMENT '联系电话',
  gmt_create   DATETIME                         DEFAULT NOW()
  COMMENT '数据创建时间',
  gmt_modified DATETIME                         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后数据修改时间',
  PRIMARY KEY (user_id),
  UNIQUE KEY (username)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 48641148789
  DEFAULT CHARSET = utf8
  COMMENT = '用户表';

CREATE TABLE IF NOT EXISTS book (
  book_id      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '图书Id(主键)',
  isbn         VARCHAR(13)     NOT NULL
  COMMENT '图书编码',
  title        VARCHAR(100)    NOT NULL
  COMMENT '标题',
  publisher    VARCHAR(100)    NOT NULL
  COMMENT '出版社',
  pubdate      VARCHAR(100)    NOT NULL
  COMMENT '出版日期',
  author       VARCHAR(100)    NOT NULL
  COMMENT '作者',
  cover_url    VARCHAR(1000)   NOT NULL
  COMMENT '封面图标',
  status       TINYINT UNSIGNED         DEFAULT 0
  COMMENT '空闲0,被申请1,借出2',
  classify     VARCHAR(100)
  COMMENT '分类',
  category     TEXT
  COMMENT '目录',
  bookrack_id  VARCHAR(20)
  COMMENT '书架号',
  gmt_create   DATETIME                 DEFAULT NOW()
  COMMENT '数据创建时间',
  gmt_modified DATETIME                 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后数据修改时间',
  PRIMARY KEY (book_id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 65123489514
  DEFAULT CHARSET = utf8
  COMMENT = '图书表';

CREATE TABLE IF NOT EXISTS borrow_order (
  order_id        BIGINT UNSIGNED NOT NULL         AUTO_INCREMENT
  COMMENT '订单Id(主键)',
  gmt_apply       DATETIME                         DEFAULT NOW()
  COMMENT '申请时间',
  gmt_borrow      DATETIME
  COMMENT '借出时间',
  gmt_return      DATETIME
  COMMENT '归还时间',
  status          TINYINT                          DEFAULT 0
  COMMENT '已取消-1,申请中0,已借出1,已归还2',
  user_id         BIGINT UNSIGNED NOT NULL
  COMMENT '借书用户Id',
  apply_admin_id  BIGINT UNSIGNED
  COMMENT '确认借出管理员Id',
  return_admin_id BIGINT UNSIGNED
  COMMENT '确认归还管理员Id',
  book_id         BIGINT UNSIGNED NOT NULL
  COMMENT '图书Id',
  gmt_create      DATETIME                         DEFAULT NOW()
  COMMENT '数据创建时间',
  gmt_modified    DATETIME                         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后数据修改时间',
  PRIMARY KEY (order_id),
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  FOREIGN KEY (apply_admin_id) REFERENCES user (user_id),
  FOREIGN KEY (return_admin_id) REFERENCES user (user_id),
  FOREIGN KEY (book_id) REFERENCES book (book_id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000000
  DEFAULT CHARSET = utf8
  COMMENT = '图书表';