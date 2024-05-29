-- V2__Create_product_table.sql

CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL UNIQUE,
    name VARCHAR(80) NOT NULL,
    category VARCHAR(20) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    preparation_time_seconds BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX IF NOT EXISTS idx_product_category ON product(category);