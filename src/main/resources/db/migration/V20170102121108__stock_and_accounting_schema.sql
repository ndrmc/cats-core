-- Migration file V20170102121108__stock_and_accounting_schema.sql created at --

/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     1/2/2017 12:07:29 PM                         */
/*==============================================================*/


/*==============================================================*/
/* Table: account                                               */
/*==============================================================*/
create table account (
  id                   serial not null,
  code                 varchar(20)          null,
  name                 varchar(200)         null,
  description          varchar(200)         null,

  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_account primary key (id)
);

/*==============================================================*/


/*==============================================================*/
/* Table: posting                                               */
/*==============================================================*/
create table posting (
  id                   serial               not null,
  posting_code         UUID                 not null,
  document_type        varchar(30)          null,
  posting_type         varchar(30)          null,
  document_id          int4                 null,
  reversed_posting_id  int4                 null,
  posting_date         timestamp with time zone                 null,


  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_posting primary key (id)
);


/* Table: posting_item                                   */
/*==============================================================*/
create table posting_item (
  id                   SERIAL                 not null,
  posting_item_code    uuid                 not null,
  posting_id           int4                 null,
  account_id           int4                 null,
  journal_id           int4                 null,
  donor_id             int4                 null,
  hub_id               int4                 null,
  warehouse_id         int4                 null,
  store_id             int4                 null,
  stack_id                int4                 null,
  project_id      int4                 null,
  batch_id             int4                 null,
  program_id           int4                 null,
  commodity_id         int4                 null,
  commodity_category_id int4                 null,
  quantity             decimal              null,
  operation_id         int4                 null,
  region_id            int4                 null,
  zone_id              int4                 null,
  woreda_id            int4                 null,
  fdp_id               int4                 null,


  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_posting_item primary key (id)
);

/*==============================================================*/
/* Table: dispatch                                              */
/*==============================================================*/
create table dispatch (
  id                   serial                 not null,
  gin                  varchar(100)         not null,
  requisition_no       varchar(100)         not null,
  operation_id         int4                 null,
  fdp_id               int4                 null,
  driver               varchar(100)         null,
  dispatched_date      timestamp with time zone                 null,
  created_by           int4                 null,
  dispatched_by        int4                 null,
  remark               TEXT                 null,
  hub_id               int4                 null,
  warehouse_id         int4                 null,
  transporter_id        int4                 null,

  weight_bridge_ticket_number       varchar(100)         not null,
  trailer_plate_number       varchar(100)         not null,
  truck_plate_number       varchar(100)         not null,


  draft               BOOL                 null,

  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_dispatch primary key (id)
);

/*==============================================================*/
/* Table: dispatch_item                                         */
/*==============================================================*/
create table dispatch_item (
  id                   serial not null,
  commodity_id         int4                 null,
  commodity_category_id int4                 null,
  dispatch_id          int4                 null,
  quantity             decimal               not null,
  project_id           int4                 not null,
  batch_id             int4                 null,
  description          text                 null,


  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_dispatch_item primary key (id)
);

/*==============================================================*/
/* Table: journal                                               */
/*==============================================================*/
create table journal (
  id                   serial not null,
  name                 varchar(200)         null,
  description          varchar(200)         null,
  code                 varchar(20)         null,

  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_journal primary key (id)
);

/*==============================================================*/
/* Table: operation                                             */
/*==============================================================*/
create table operation (
  id                   serial not null,
  program_id           int4                 not null,
  plan_id              int4                 null,
  name                 varchar(200)         not null,
  description          text                 null,
  year                 varchar(4)           not null,
  round                int4                 null,
  ration_id            int4                 null,
  operation_month      int4                 null,
  expected_start       timestamp with time zone                 null,
  planned_end          timestamp with time zone                 null,
  actual_start         timestamp with time zone                 null,
  actual_end           timestamp with time zone                 null,
  status               varchar(10)          null,

  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_operation primary key (id)
);

/*==============================================================*/
/* Table: operation_region                                      */
/*==============================================================*/
create table operation_region (
  id                   serial                 not null,
  operation_id         int4                   null,
  region_id            int4                 not null,

  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_operation_region primary key (id)
);


/*==============================================================*/
/* Table: receipt                                               */
/*==============================================================*/
create table receipt (
  id                   serial not null,
  grn_no                  varchar(200)         null,
  received_date         timestamp with time zone                 null,
  hub_id               int4                 null,
  warehouse_id         int4                 null,
  delivered_by         varchar(200)         null,
  supplier_id          int4                 null,
  transporter_id       INT4         null,
  plate_no             varchar(200)         null,
  trailer_plate_no     varchar(200)         null,
  weight_bridge_ticket_no varchar(200)         null,
  weight_before_unloading decimal              null,
  weight_after_unloading decimal              null,
  storekeeper_name     varchar(200)         null,
  waybill_no           varchar(200)         null,
  purchase_request_no  varchar(200)     null,
  purchase_order_no    varchar(200)         null,
  invoice_no           varchar(200)         null,
  commodity_source_id  int4                 null,
  program_id          int4                   null,
  draft               BOOL                 null,

  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_receipt primary key (id)
);

/*==============================================================*/
/* Table: receipt_line                                          */
/*==============================================================*/
create table receipt_line (
  id                   serial not null,
  receipt_id           int4                 null,
  commodity_id         int4                 null,
  commodity_category_id         int4                 null,
  project_id           int4                 not null,
  batch_id             int4                 null,
  amount               decimal              null,

  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_receipt_line primary key (id)
);

/*==============================================================*/
/* Table: unit_of_measure                                       */
/*==============================================================*/
create table unit_of_measure (
  id                   serial not null,
  uom_category_id      int4                 not null,
  uom_type             int4                 not null,
  ratio                decimal              not null,
  name                 varchar(200)         not null,
  code                 varchar(4)           null,

  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_unit_of_measure primary key (id)
);

/*==============================================================*/
/* Table: uom_category                                          */
/*==============================================================*/
create table uom_category (
  id                   serial not null,
  name                 varchar(200)         null,


  created_date         timestamp with time zone                 null,
  updated_date         timestamp with time zone                 null,

  constraint pk_uom_category primary key (id)
);

comment on table uom_category is
'Unit, weight, volume, length';

alter table posting_item
  add constraint fk_account__reference_journal foreign key (journal_id)
references journal (id)
on delete restrict on update restrict;

alter table posting_item
  add constraint fk_account__reference_posting foreign key (posting_id)
references posting (id)
on delete restrict on update restrict;

alter table posting_item
  add constraint fk_account__reference_account foreign key (account_id)
references account (id)
on delete restrict on update restrict;

alter table dispatch
  add constraint fk_dispatch_reference_operatio foreign key (operation_id)
references operation (id)
on delete restrict on update restrict;

alter table dispatch_item
  add constraint fk_dispatch_reference_dispatch foreign key (dispatch_id)
references dispatch (id);

alter table operation_region
  add constraint fk_operatio_reference_operatio foreign key (operation_id)
references operation (id)
on delete restrict on update restrict;

alter table receipt_line
  add constraint fk_receipt__ref_recip_receipt foreign key (receipt_id)
references receipt (id);

alter table unit_of_measure
  add constraint fk_unit_of__reference_uom_cate foreign key (uom_category_id)
references uom_category (id);
