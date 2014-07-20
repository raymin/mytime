/*后面需要建成分区表*/
DROP TABLE IF EXISTS t_login_log;
CREATE TABLE t_login_log (
  id            INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  login_type    VARCHAR(10)     NOT NULL,
  login_account VARCHAR(200)    NOT NULL,
  login_pwd     VARCHAR(256)    NOT NULL,
  login_time    TIMESTAMP,
  customer_ip   VARCHAR(100),
  client_ip     VARCHAR(100),
  created_at    TIMESTAMP       NOT NULL,
  created_by    VARCHAR(20)     NOT NULL,
  updated_at    TIMESTAMP,
  updated_by    VARCHAR(20)
)
  DEFAULT CHARSET =utf8;

CREATE INDEX idx_t_login_log_tc ON t_login_log (login_time, login_account);
