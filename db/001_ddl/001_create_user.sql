drop TABLE if exists t_user;
CREATE TABLE t_user (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    nick_name VARCHAR(100),
    pwd VARCHAR(256) NOT NULL,
    type VARCHAR(2),
    mobile VARCHAR(16),
    email VARCHAR(200),
    sex CHAR(1),
    birthday VARCHAR(10),
    pic_url VARCHAR(200),
    last_login_time TIMESTAMP,
    user_ip VARCHAR(100),
    client_ip VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP,
    updated_by  VARCHAR(20)
)default charset=utf8;
CREATE INDEX idx_t_user_name on t_user(name);
CREATE INDEX idx_t_user_mobile on t_user(mobile);