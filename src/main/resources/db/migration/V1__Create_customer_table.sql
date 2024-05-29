-- V1__Create_customer_table.sql

CREATE TABLE IF NOT EXISTS customer (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL UNIQUE,
    name VARCHAR(80) NOT NULL,
    document VARCHAR(20) NOT NULL UNIQUE,
    document_type VARCHAR(10) NOT NULL,
    email VARCHAR(64) NOT NULL,
    is_identified BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX IF NOT EXISTS idx_customer_document ON customer(document);
CREATE INDEX IF NOT EXISTS idx_customer_email ON customer(email);
