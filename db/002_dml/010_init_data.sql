delete from T_USER;
insert into T_USER(ID,NAME,NAME_DISP,NICK_NAME,PWD,MOBILE,EMAIL,EMAIL_DISP,CREATED_AT,CREATED_BY)
values(1, 'test','Test','nick','E10ADC3949BA59ABBE56E057F20F883E','13912345678','test@91time.com','Test@91time.com',NOW(),'system');
commit;