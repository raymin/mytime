delete from T_USER;
insert into T_USER(ID,NAME,NAME_DISP,NICK_NAME,PWD,MOBILE,EMAIL,EMAIL_DISP,CREATED_AT,CREATED_BY)
values(1, 'test','Test','nick','81DC9BDB52D04DC20036DBD8313ED055','13912345678','test@91time.com','Test@91time.com',NOW(),'system');
commit;