 SELECT t_ecs_zx.zxid AS id,
        CASE
            WHEN (t_ecs_zx.sjc IS NOT NULL) THEN (round((date_part('epoch'::text, t_ecs_zx.sjc) * (1000)::double precision)) || ''::text)
            ELSE '0'::text
        END AS cjsj,
        CASE
            WHEN (t_ecs_zx.sjc IS NOT NULL) THEN (round((date_part('epoch'::text, t_ecs_zx.sjc) * (1000)::double precision)) || ''::text)
            ELSE '0'::text
        END AS gxsj,
    'NA'::text AS czz,
    1 AS yxx,
    t_ecs_zx.zxid,
    t_ecs_zx.th,
    t_ecs_zx.fjh,
    t_ecs_zx.yjdhhm,
    t_ecs_zx.ssdw,
    t_ecs_zx.ip,
    t_ecs_zx.jdlx21,
    t_ecs_zx.dlacd,
    t_ecs_zx.ftms,
    t_ecs_zx.zxlx,
    t_ecs_zx.jnjb,
    t_ecs_zx.room,
    t_ecs_zx.dlzt,
    t_ecs_zx.dlgh,
    t_ecs_zx.dccode,
    t_ecs_zx.dcpassword,
    t_ecs_zx.gxsj AS sjc,
        CASE
            WHEN (t_ecs_zx.latesttime IS NOT NULL) THEN (round((date_part('epoch'::text, t_ecs_zx.latesttime) * (1000)::double precision)) || ''::text)
            ELSE '0'::text
        END AS latesttime,
    t_ecs_zx.icpip,
    t_ecs_zx.speakerid,
    t_ecs_zx.wluserid,
    t_ecs_zx.wluserpassword,
    t_ecs_zx.wlserverinfo,
    t_ecs_zx.xh,
    t_ecs_zx.talkinggroup,
    t_ecs_zx.wldeviceid,
    t_ecs_zx.wlconferenceid,
    t_ecs_zx.elteproxymode,
    t_ecs_zx.wlserverid
   FROM dsportal_hangzhou.t_ecs_zx