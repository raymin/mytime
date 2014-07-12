delete from t_user;
insert into t_user(id,name,nick_name,pwd,mobile,email,created_at,created_by) values(1, 'test','nick','123','13912345678','test@91time.com',NOW(),'system');

commit;