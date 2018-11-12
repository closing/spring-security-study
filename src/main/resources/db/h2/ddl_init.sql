DROP TABLE IF EXISTS `oauth_client_details`;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
VALUES ('yczd-mini-client', 'yczd_api','{noop}password','read,write,trust', 'client_credentials', 'ROLE_CLIENT', 10800, 2592000);
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
VALUES ('read-write-client', 'product-api','{noop}password','read,write', 'client_credentials', 'ROLE_PRODUCT_ADMIN', 10800, 2592000);

drop table if exists authority;
CREATE TABLE authority (
  id  integer,
  authority varchar(255),
  primary key (id)
);

INSERT INTO authority  VALUES(1,'ROLE_OAUTH_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_ADMIN_PRODUCT');
INSERT INTO authority VALUES(3,'ROLE_RESOURCE_ADMIN');

drop table if exists credentials;
CREATE TABLE credentials (
  id  integer,
  enabled boolean not null,
  name varchar(255) not null,
  password varchar(255) not null,
  version integer,
  primary key (id)
);

INSERT INTO credentials VALUES(1,'1','oauth_admin','{noop}password','0');
INSERT INTO credentials VALUES(2,'1','resource_admin','{noop}password','0');
INSERT INTO credentials  VALUES(3,'1','user','{noop}password','0');

drop table if exists credentials_authorities;
CREATE TABLE credentials_authorities (
  credentials_id bigint not null,
  authorities_id bigint not null
);
INSERT INTO credentials_authorities VALUES (1, 1);
INSERT INTO credentials_authorities VALUES (2, 3);
INSERT INTO credentials_authorities VALUES (3, 2);

DROP TABLE IF EXISTS `oauth_client_token`;
create table oauth_client_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

DROP TABLE IF EXISTS `oauth_access_token`;
create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

DROP TABLE IF EXISTS `oauth_refresh_token`;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

DROP TABLE IF EXISTS `oauth_code`;
create table oauth_code (
  code VARCHAR(256), authentication BLOB
);

DROP TABLE IF EXISTS `oauth_approvals`;
create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);


DROP TABLE IF EXISTS `ClientDetails`;
-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);
