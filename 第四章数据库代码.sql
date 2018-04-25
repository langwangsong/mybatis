ALTER TABLE sys_user
MODIFY COLUMN user_email VARCHAR(50) NULL DEFAULT 'test@qq.com' COMMENT '邮箱' AFTER user_password;

