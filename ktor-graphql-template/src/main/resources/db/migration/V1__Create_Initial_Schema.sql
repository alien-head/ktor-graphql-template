-- Make sure we can use UUIDs first
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create the human table
CREATE TABLE humans (
    id uuid primary key,
    first_name varchar(16) not null,
    last_name varchar(16) not null
);
--
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE humans TO example_user;
