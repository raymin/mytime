DROP TABLE IF EXISTS T_TERM_RELATIONSHIP;
CREATE TABLE T_TERM_RELATIONSHIP (
  ID             BIGINT(20) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  OBJECT_ID      BIGINT(20),
  TAXONOMY_ID    BIGINT(20),
  TERM_ORDER     INT(11),
  CREATED_AT     TIMESTAMP       NOT NULL,
  CREATED_BY     VARCHAR(20)     NOT NULL,
  UPDATED_AT     TIMESTAMP       NOT NULL,
  UPDATED_BY     VARCHAR(20)     NOT NULL
)
DEFAULT CHARSET =UTF8;

CREATE UNIQUE INDEX UK_TERM_RELATIONSHIP ON T_TERM_RELATIONSHIP (OBJECT_ID, TAXONOMY_ID);
CREATE UNIQUE INDEX IDX_TERM_RELATIONSHIP ON T_TERM_RELATIONSHIP (TAXONOMY_ID);
