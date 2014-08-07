DROP TABLE IF EXISTS T_POST_TAG;
CREATE TABLE T_POST_TAG (
  POST_TAG_ID    BIGINT(20) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  TAG_ID         BIGINT(20),
  POST_ID        BIGINT(20),
  CREATED_AT     TIMESTAMP       NOT NULL,
  CREATED_BY     VARCHAR(20)     NOT NULL,
  UPDATED_AT     TIMESTAMP       NOT NULL,
  UPDATED_BY     VARCHAR(20)     NOT NULL
)
DEFAULT CHARSET =UTF8;

CREATE UNIQUE INDEX IDX_POST_TAG ON T_POST_TAG (TAG_ID,POST_ID);
