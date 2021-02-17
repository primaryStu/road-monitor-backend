create table `tb_device` (
    `user_id`   int(11)          NOT NULL,
    `device_id` varchar(250) NOT NULL,
    foreign key (`user_id`) references `tb_user`(`user_id`),
    primary key (`user_id`, `device_id`)
)
