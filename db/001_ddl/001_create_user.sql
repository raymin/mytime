DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
  id              INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name            VARCHAR(100)    NOT NULL,
  name_disp       VARCHAR(100),
  nick_name       VARCHAR(100),
  mobile          VARCHAR(20),
  email           VARCHAR(200),
  email_disp      VARCHAR(200),
  pwd             VARCHAR(256)    NOT NULL,
  type            VARCHAR(10),
  src             VARCHAR(10),
  status          CHAR(10),
  sex             CHAR(1),
  birthday        VARCHAR(10),
  pic_url         VARCHAR(200),
  last_login_time TIMESTAMP,
  customer_ip     VARCHAR(100),
  client_ip       VARCHAR(100),
  created_at      TIMESTAMP       NOT NULL,
  created_by      VARCHAR(20)     NOT NULL,
  updated_at      TIMESTAMP,
  updated_by      VARCHAR(20)
)
  DEFAULT CHARSET =utf8;


CREATE UNIQUE INDEX idx_t_user_name ON t_user (name);
CREATE UNIQUE INDEX idx_t_user_mobile ON t_user (mobile);
CREATE UNIQUE INDEX idx_t_user_email ON t_user (email);