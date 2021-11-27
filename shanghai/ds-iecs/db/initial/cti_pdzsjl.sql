CREATE TABLE "iecs_hangzhou"."cti_pdzsjl" (
                                              "id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
                                              "cjsj" int8,
                                              "czz" varchar(200) COLLATE "pg_catalog"."default",
                                              "gxsj" int8,
                                              "yxx" int4,
                                              "zjhm" varchar(50) COLLATE "pg_catalog"."default",
                                              "pddw" varchar(80) COLLATE "pg_catalog"."default",
                                              "dwmc" varchar(400) COLLATE "pg_catalog"."default",
                                              "zx" varchar(50) COLLATE "pg_catalog"."default",
                                              "pdsj" int8,
                                              "gh" varchar(200) COLLATE "pg_catalog"."default",
                                              "zssj" int8,
                                              CONSTRAINT "cti_pdzsjl_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "iecs_hangzhou"."cti_pdzsjl"
    OWNER TO "iecs_hangzhou";

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."id" IS '主键';

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."cjsj" IS '创建时间';

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."czz" IS '操作者';

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."gxsj" IS '更新时间';

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."yxx" IS '有效性';

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."zjhm" IS '主叫号码';

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."pddw" IS '排队单位id';

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."dwmc" IS '排队单位名称';

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."zx" IS '坐席号';

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."pdsj" IS '排队时间';

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."gh" IS '工号';

COMMENT ON COLUMN "iecs_hangzhou"."cti_pdzsjl"."zssj" IS '早释时间';

COMMENT ON TABLE "iecs_hangzhou"."cti_pdzsjl" IS '排队早释记录';