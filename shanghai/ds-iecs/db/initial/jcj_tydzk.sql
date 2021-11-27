CREATE TABLE "iecs_hangzhou"."jcj_tydzk" (
                                             "id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
                                             "cjsj" int8,
                                             "czz" varchar(200) COLLATE "pg_catalog"."default",
                                             "gxsj" int8,
                                             "yxx" int4,
                                             "jq_tywysbm" varchar(40) COLLATE "pg_catalog"."default",
                                             "tydzkid" varchar(100) COLLATE "pg_catalog"."default",
                                             "ywfkbs" int4,
                                             "scdjjbs" int4,
                                             "ywfksj" int8,
                                             "scdjjsj" int8,
                                             "sfhxaj" int4,
                                             CONSTRAINT "jcj_tydzk_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "iecs_hangzhou"."jcj_tydzk"
    OWNER TO "iecs_hangzhou";

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."id" IS '主键';

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."cjsj" IS '创建时间';

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."czz" IS '操作者';

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."gxsj" IS '更新时间';

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."yxx" IS '有效性';

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."jq_tywysbm" IS '警情id';

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."tydzkid" IS '统一地址库id';

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."ywfkbs" IS '业务反馈是否成功 0失败 1成功';

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."scdjjbs" IS '上传待救济是否成功 0失败 1成功';

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."ywfksj" IS '业务反馈时间';

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."scdjjsj" IS '上传待救济时间';

COMMENT ON COLUMN "iecs_hangzhou"."jcj_tydzk"."sfhxaj" IS '是否回写至案件';

COMMENT ON TABLE "iecs_hangzhou"."jcj_tydzk" IS '统一地址库相关表';