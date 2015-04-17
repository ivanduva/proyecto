# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table encomienda (
  id_encomienda             bigint not null,
  viaje_encomienda_id_servicio bigint not null,
  destinatario              varchar(255),
  direccion_destino         varchar(255),
  fecha_entrega             timestamp,
  estado                    varchar(11),
  remitente_id_persona      bigint,
  localidad_id_localidad    bigint,
  tarifa                    decimal(38),
  constraint ck_encomienda_estado check (estado in ('EN_CAMINO','EN_SUCURSAL','ENTREGADA','RETRASADA')),
  constraint pk_encomienda primary key (id_encomienda))
;

create table franja_horaria (
  id_franja_horaria         bigint not null,
  dia                       timestamp,
  hora_fin                  time,
  hora_inicio               time,
  constraint pk_franja_horaria primary key (id_franja_horaria))
;

create table lat_long (
  id_lat_long               bigint not null,
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
  dtype                     varchar(10) not null,
  id_persona                bigint not null,
  email                     varchar(255),
  fecha_nacimiento          timestamp,
  localidad_id_localidad    bigint,
  nombre                    varchar(255),
  telefono                  varchar(255),
  usuario_id_usuario        bigint,
  categoria                 varchar(255),
  puede_reservar            boolean,
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
  tipo                      varchar(22),
  constraint ck_punto_de_venta_tipo check (tipo in ('OFICINA_ADMINISTRATIVA','PUNTO_EXTERNO')),
  constraint pk_punto_de_venta primary key (id_punto_de_venta))
;

create table servicio (
  dtype                     varchar(10) not null,
  id_servicio               bigint not null,
  descripcion               varchar(255),
  nombre                    varchar(255),
  habilitado                boolean,
  constraint pk_servicio primary key (id_servicio))
;

create table usuario (
  id_usuario                bigint not null,
  nombre_usuario            varchar(255),
  tipo                      varchar(14),
  contrasena                varchar(255),
  constraint ck_usuario_tipo check (tipo in ('CLIENTE','GERENTE','VENDEDOR','EMPLEADO','MECANICO','ADMINISTRATIVO')),
  constraint pk_usuario primary key (id_usuario))
;

create table venta (
  id_venta                  bigint not null,
  punto_de_venta_id_punto_de_venta bigint not null,
  fecha                     timestamp,
  valor_final               decimal(38),
  cliente_id_persona        bigint,
  constraint pk_venta primary key (id_venta))
;

create sequence encomienda_seq;

create sequence franja_horaria_seq;

create sequence lat_long_seq;

create sequence localidad_seq;

create sequence persona_seq;

create sequence punto_de_venta_seq;

create sequence servicio_seq;

create sequence usuario_seq;

create sequence venta_seq;

alter table encomienda add constraint fk_encomienda_servicio_1 foreign key (viaje_encomienda_id_servicio) references servicio (id_servicio);
create index ix_encomienda_servicio_1 on encomienda (viaje_encomienda_id_servicio);
alter table encomienda add constraint fk_encomienda_remitente_2 foreign key (remitente_id_persona) references persona (id_persona);
create index ix_encomienda_remitente_2 on encomienda (remitente_id_persona);
alter table encomienda add constraint fk_encomienda_localidad_3 foreign key (localidad_id_localidad) references localidad (id_localidad);
create index ix_encomienda_localidad_3 on encomienda (localidad_id_localidad);
alter table localidad add constraint fk_localidad_ubicacion_4 foreign key (ubicacion_id_lat_long) references lat_long (id_lat_long);
create index ix_localidad_ubicacion_4 on localidad (ubicacion_id_lat_long);
alter table persona add constraint fk_persona_localidad_5 foreign key (localidad_id_localidad) references localidad (id_localidad);
create index ix_persona_localidad_5 on persona (localidad_id_localidad);
alter table persona add constraint fk_persona_usuario_6 foreign key (usuario_id_usuario) references usuario (id_usuario);
create index ix_persona_usuario_6 on persona (usuario_id_usuario);
alter table punto_de_venta add constraint fk_punto_de_venta_localidad_7 foreign key (localidad_id_localidad) references localidad (id_localidad);
create index ix_punto_de_venta_localidad_7 on punto_de_venta (localidad_id_localidad);
alter table punto_de_venta add constraint fk_punto_de_venta_usuario_8 foreign key (usuario_id_usuario) references usuario (id_usuario);
create index ix_punto_de_venta_usuario_8 on punto_de_venta (usuario_id_usuario);
alter table venta add constraint fk_venta_punto_de_venta_9 foreign key (punto_de_venta_id_punto_de_venta) references punto_de_venta (id_punto_de_venta);
create index ix_venta_punto_de_venta_9 on venta (punto_de_venta_id_punto_de_venta);
alter table venta add constraint fk_venta_cliente_10 foreign key (cliente_id_persona) references persona (id_persona);
create index ix_venta_cliente_10 on venta (cliente_id_persona);



# --- !Downs

drop table if exists encomienda cascade;

drop table if exists franja_horaria cascade;

drop table if exists lat_long cascade;

drop table if exists localidad cascade;

drop table if exists persona cascade;

drop table if exists punto_de_venta cascade;

drop table if exists servicio cascade;

drop table if exists usuario cascade;

drop table if exists venta cascade;

drop sequence if exists encomienda_seq;

drop sequence if exists franja_horaria_seq;

drop sequence if exists lat_long_seq;

drop sequence if exists localidad_seq;

drop sequence if exists persona_seq;

drop sequence if exists punto_de_venta_seq;

drop sequence if exists servicio_seq;

drop sequence if exists usuario_seq;

drop sequence if exists venta_seq;

