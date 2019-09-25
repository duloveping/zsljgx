drop table if exists deliver_goods_bill;
create table deliver_goods_bill
(
    id bigserial,
    creator_id bigint,
    create_time timestamp without time zone,
    editor_id bigint,
    edit_time timestamp without time zone,
    status boolean default true,
    flag boolean default true,
    provider_code character varying(100),
    provider_name character varying(500),
    materiel_code character varying(100),
    materiel_name character varying(500),
    materiel_spec character varying(500),
    batch_code character varying(100),
    produce_date date,
    work_shop_code character varying(100),
    work_shop_name character varying(500),
    work_shift_code character varying(100),
    work_shift_name character varying(500),
    product_code character varying(100),
    product_name character varying(500),
    product_spec character varying(500),
    factory_code character varying(100),
    factory_name character varying(500),
    primary key (id)
);
comment on table deliver_goods_bill is '送货单';
comment on column deliver_goods_bill.id is '流水号';
comment on column deliver_goods_bill.creator_id is '创建人ID';
comment on column deliver_goods_bill.create_time is '创建时间';
comment on column deliver_goods_bill.editor_id is '编辑人ID';
comment on column deliver_goods_bill.edit_time is '编辑时间';
comment on column deliver_goods_bill.status is '状态';
comment on column deliver_goods_bill.flag is '标记';
comment on column deliver_goods_bill.provider_code is '供应商编码';
comment on column deliver_goods_bill.materiel_code is '物料编码';
comment on column deliver_goods_bill.materiel_name is '物料名称（品名）';
comment on column deliver_goods_bill.materiel_spec is '物料规格';
comment on column deliver_goods_bill.batch_code is '批次号';
comment on column deliver_goods_bill.produce_date is '生产日期';
comment on column deliver_goods_bill.work_shop_code is '车间编码';
comment on column deliver_goods_bill.work_shop_name is '车间名称';
comment on column deliver_goods_bill.work_shift_code is '班次编码';
comment on column deliver_goods_bill.work_shift_name is '班次名称';
comment on column deliver_goods_bill.product_code is '产品编码';
comment on column deliver_goods_bill.product_name is '产品名称';
comment on column deliver_goods_bill.product_spec is '产品规格';
comment on column deliver_goods_bill.factory_code is '工厂编码';
comment on column deliver_goods_bill.factory_name is '工厂名称';