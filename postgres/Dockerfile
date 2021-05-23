FROM postgres:9.6.10-alpine

ADD local-security.sql /docker-entrypoint-initdb.d/
ADD create_db.sql /docker-entrypoint-initdb.d/

EXPOSE 5432

CMD ["postgres"]
