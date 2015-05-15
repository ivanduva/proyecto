# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table encomienda (
  id_encomienda             bigint not null,
  venta_id_venta            bigint not null,
  destinatario              varchar(255),
  direccion_destino         varchar(255),
  fecha_entrega             timestamp,
  remitente_id_persona      bigint,
  localidad_id_localidad    bigint,
  tarifa                    bigint,
  constraint pk_encomienda primary key (id_encomienda))
;

create table estado_encomienda (
  id_estado_encomienda      bigint not null,
  encomienda_id_encomienda  bigint not null,
  nombre                    varchar(11),
  fecha                     timestamp,
  punto_de_venta_id_punto_de_venta bigint,
  constraint ck_estado_encomienda_nombre check (nombre in ('EN_CAMINO','EN_SUCURSAL','ENTREGADA','RETRASADA')),
  constraint pk_estado_encomienda primary key (id_estado_encomienda))
;

create table franja_horaria (
  id_franja_horaria         bigint not null,
  servicio_id_servicio      bigint not null,
  dia                       timestamp,
  hora_fin                  time,
  hora_inicio               time,
  constraint pk_franja_horaria primary key (id_franja_horaria))
;

create table lat_long (
  id_lat_long               bigint not null,
  latitud                   float,
  constraint pk_lat_long primary key (id_lat_long))
;

create table localidad (
  id_localidad              bigint not null,
  nombre                    varchar(255),
  codigo_postal             bigint,
  ubicacion_id_lat_long     bigint,
  constraint pk_localidad primary key (id_localidad))
;

create table persona (
  tipo                      varchar(31) not null,
  id_persona                bigint not null,
  email                     varchar(255),
  fecha_nacimiento          timestamp,
  localidad_id_localidad    bigint,
  nombre                    varchar(255),
  telefono                  varchar(255),
  usuario_id_usuario        bigint,
  cuit                      varchar(255),
  dni                       bigint,
  horario                   varchar(255),
  cesado                    boolean,
  categoria                 varchar(255),
  puede_reservar            varchar(255),
  puntos_viajero            integer,
  constraint pk_persona primary key (id_persona))
;

create table punto_de_venta (
  id_punto_de_venta         bigint not null,
  direccion                 varchar(255),
  email                     varchar(255),
  localidad_id_localidad    bigint,
  nombre                    varchar(255),
  nombre_responsable        varchar(255),
  telefono                  varchar(255),
  usuario_id_usuario        bigint,
  tipo_punto                varchar(22),
  constraint ck_punto_de_venta_tipo_punto check (tipo_punto in ('OFICINA ADMINISTRATIVA','PUNTO EXTERNO')),
  constraint pk_punto_de_venta primary key (id_punto_de_venta))
;

create table security_role (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_security_role primary key (id))
;

create table servicio (
  tipo                      varchar(31) not null,
  id_servicio               bigint not null,
  descripcion               varchar(255),
  nombre                    varchar(255),
  habilitado                boolean,
  constraint pk_servicio primary key (id_servicio))
;

create table user_permission (
  id                        bigint not null,
  permission_value          varchar(255),
  constraint pk_user_permission primary key (id))
;

create table usuario (
  id_usuario                bigint not null,
  nombre_usuario            varchar(256) not null,
  fecha_creacion            timestamp,
  sha_password              bytea not null,
  auth_token                varchar(255),
  constraint uq_usuario_nombre_usuario unique (nombre_usuario),
  constraint pk_usuario primary key (id_usuario))
;

create table venta (
  id_venta                  bigint not null,
  fecha                     timestamp,
  valor_final               bigint,
  finalizada                boolean,
  cliente_id_persona        bigint,
  punto_de_venta_id_punto_de_venta bigint,
  constraint pk_venta primary key (id_venta))
;


create table usuario_security_role (
  usuario_id_usuario             bigint not null,
  security_role_id               bigint not null,
  constraint pk_usuario_security_role primary key (usuario_id_usuario, security_role_id))
;

create table usuario_user_permission (
  usuario_id_usuario             bigint not null,
  user_permission_id             bigint not null,
  constraint pk_usuario_user_permission primary key (usuario_id_usuario, user_permission_id))
;
create sequence encomienda_seq;

create sequence estado_encomienda_seq;

create sequence franja_horaria_seq;

create sequence lat_long_seq;

create sequence localidad_seq;

create sequence persona_seq;

create sequence punto_de_venta_seq;

create sequence security_role_seq;

create sequence servicio_seq;

create sequence user_permission_seq;

create sequence usuario_seq;

create sequence venta_seq;

alter table encomienda add constraint fk_encomienda_venta_1 foreign key (venta_id_venta) references venta (id_venta);
create index ix_encomienda_venta_1 on encomienda (venta_id_venta);
alter table encomienda add constraint fk_encomienda_remitente_2 foreign key (remitente_id_persona) references persona (id_persona);
create index ix_encomienda_remitente_2 on encomienda (remitente_id_persona);
alter table encomienda add constraint fk_encomienda_localidad_3 foreign key (localidad_id_localidad) references localidad (id_localidad);
create index ix_encomienda_localidad_3 on encomienda (localidad_id_localidad);
alter table estado_encomienda add constraint fk_estado_encomienda_encomiend_4 foreign key (encomienda_id_encomienda) references encomienda (id_encomienda);
create index ix_estado_encomienda_encomiend_4 on estado_encomienda (encomienda_id_encomienda);
alter table estado_encomienda add constraint fk_estado_encomienda_puntoDeVe_5 foreign key (punto_de_venta_id_punto_de_venta) references punto_de_venta (id_punto_de_venta);
create index ix_estado_encomienda_puntoDeVe_5 on estado_encomienda (punto_de_venta_id_punto_de_venta);
alter table franja_horaria add constraint fk_franja_horaria_servicio_6 foreign key (servicio_id_servicio) references servicio (id_servicio);
create index ix_franja_horaria_servicio_6 on franja_horaria (servicio_id_servicio);
alter table localidad add constraint fk_localidad_ubicacion_7 foreign key (ubicacion_id_lat_long) references lat_long (id_lat_long);
create index ix_localidad_ubicacion_7 on localidad (ubicacion_id_lat_long);
alter table persona add constraint fk_persona_localidad_8 foreign key (localidad_id_localidad) references localidad (id_localidad);
create index ix_persona_localidad_8 on persona (localidad_id_localidad);
alter table persona add constraint fk_persona_usuario_9 foreign key (usuario_id_usuario) references usuario (id_usuario);
create index ix_persona_usuario_9 on persona (usuario_id_usuario);
alter table punto_de_venta add constraint fk_punto_de_venta_localidad_10 foreign key (localidad_id_localidad) references localidad (id_localidad);
create index ix_punto_de_venta_localidad_10 on punto_de_venta (localidad_id_localidad);
alter table punto_de_venta add constraint fk_punto_de_venta_usuario_11 foreign key (usuario_id_usuario) references usuario (id_usuario);
create index ix_punto_de_venta_usuario_11 on punto_de_venta (usuario_id_usuario);
alter table venta add constraint fk_venta_cliente_12 foreign key (cliente_id_persona) references persona (id_persona);
create index ix_venta_cliente_12 on venta (cliente_id_persona);
alter table venta add constraint fk_venta_puntoDeVenta_13 foreign key (punto_de_venta_id_punto_de_venta) references punto_de_venta (id_punto_de_venta);
create index ix_venta_puntoDeVenta_13 on venta (punto_de_venta_id_punto_de_venta);



alter table usuario_security_role add constraint fk_usuario_security_role_usua_01 foreign key (usuario_id_usuario) references usuario (id_usuario);

alter table usuario_security_role add constraint fk_usuario_security_role_secu_02 foreign key (security_role_id) references security_role (id);

alter table usuario_user_permission add constraint fk_usuario_user_permission_us_01 foreign key (usuario_id_usuario) references usuario (id_usuario);

alter table usuario_user_permission add constraint fk_usuario_user_permission_us_02 foreign key (user_permission_id) references user_permission (id);

# --- !Downs

drop table if exists encomienda cascade;

drop table if exists estado_encomienda cascade;

drop table if exists franja_horaria cascade;

drop table if exists lat_long cascade;

drop table if exists localidad cascade;

drop table if exists persona cascade;

drop table if exists punto_de_venta cascade;

drop table if exists security_role cascade;

drop table if exists servicio cascade;

drop table if exists user_permission cascade;

drop table if exists usuario cascade;

drop table if exists usuario_security_role cascade;

drop table if exists usuario_user_permission cascade;

drop table if exists venta cascade;

drop sequence if exists encomienda_seq;

drop sequence if exists estado_encomienda_seq;

drop sequence if exists franja_horaria_seq;

drop sequence if exists lat_long_seq;

drop sequence if exists localidad_seq;

drop sequence if exists persona_seq;

drop sequence if exists punto_de_venta_seq;

drop sequence if exists security_role_seq;

drop sequence if exists servicio_seq;

drop sequence if exists user_permission_seq;

drop sequence if exists usuario_seq;

drop sequence if exists venta_seq;

